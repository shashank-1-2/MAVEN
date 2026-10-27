import { useMemo } from "react";
import Backdrop from "./components/Backdrop";
import PromptConsole from "./components/PromptConsole";
import RaceTrack from "./components/RaceTrack";
import ModelCard from "./components/ModelCard";
import { useCompare } from "./hooks/useCompare";

export default function App() {
  const { models, loadError, runs, phase, start, stop } = useCompare();
  const running = phase === "running";

  // Cards reorder themselves into finishing order as models complete.
  const ordered = useMemo(() => {
    const key = (m, i) => runs[m.id]?.rank ?? 100 + i;
    return models.map((m, i) => [m, key(m, i)]).sort((a, b) => a[1] - b[1]).map(([m]) => m);
  }, [models, runs]);

  return (
    <>
      <Backdrop />
      <main className="shell">
        <header className="hero">
          <h1>LLM Arena</h1>
          <p>One question, three models, answering live. See who finishes first and whose answer you like best.</p>
        </header>

        <PromptConsole running={running} disabled={models.length === 0} onSubmit={start} onStop={stop} />

        {loadError && (
          <div className="banner" role="alert">
            Can’t reach the backend ({loadError}). Start it on port 8080 and refresh.
          </div>
        )}

        <RaceTrack models={models} runs={runs} phase={phase} />

        <section className="grid" aria-label="Model answers">
          {ordered.map((m) => (
            <ModelCard
              key={m.id}
              model={m}
              run={runs[m.id] ?? { text: "", status: "idle", rank: null, latencyMs: null, error: null }}
            />
          ))}
        </section>
      </main>
    </>
  );
}
