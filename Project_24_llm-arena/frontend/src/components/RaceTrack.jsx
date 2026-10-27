import { motion } from "framer-motion";
import { ACCENTS } from "../constants";

// The streamed length is only a rough proxy for progress, so lanes cap at 92% until the model reports it has finished.
const progress = (run) => (run.status === "done" ? 100 : Math.min(92, 6 + run.text.length / 14));

export default function RaceTrack({ models, runs, phase }) {
  if (phase === "idle") return null;
  const finished = models
    .filter((m) => runs[m.id]?.rank)
    .sort((a, b) => runs[a.id].rank - runs[b.id].rank);

  return (
    <section className="race" aria-label="Live race">
      <div className="lanes">
        {models.map((m) => {
          const run = runs[m.id] ?? { text: "", status: "waiting" };
          return (
            <div className="lane" key={m.id} style={{ "--accent": ACCENTS[m.id] }}>
              <span className="lane-name">{m.label}</span>
              <div className="lane-rail">
                <motion.div
                  className={`lane-bar ${run.status}`}
                  initial={{ width: "0%" }}
                  animate={{ width: `${run.status === "error" ? 100 : progress(run)}%` }}
                  transition={{ type: "spring", stiffness: 60, damping: 18 }}
                />
              </div>
              <span className="lane-time">
                {run.latencyMs ? `${(run.latencyMs / 1000).toFixed(2)}s` : run.status === "error" ? "failed" : "…"}
              </span>
            </div>
          );
        })}
      </div>
      <div className="order" aria-live="polite">
        <strong>Finish order</strong>
        {finished.length === 0 ? (
          <span className="muted">waiting for the first model to finish</span>
        ) : (
          <ol>
            {finished.map((m, i) => (
              <li key={m.id} style={{ "--accent": ACCENTS[m.id] }}>
                {m.label}{i === 0 ? " (fastest)" : ""}
              </li>
            ))}
          </ol>
        )}
      </div>
    </section>
  );
}
