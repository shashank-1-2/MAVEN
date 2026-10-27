import { useCallback, useEffect, useRef, useState } from "react";
import { fetchModels, openCompareStream } from "../api";

const blank = () => ({ text: "", status: "waiting", rank: null, latencyMs: null, error: null });

export function useCompare() {
  const [models, setModels] = useState([]);
  const [loadError, setLoadError] = useState(null);
  const [runs, setRuns] = useState({});
  const [phase, setPhase] = useState("idle"); // idle | running | finished
  const closeRef = useRef(null);

  useEffect(() => {
    fetchModels().then(setModels).catch((e) => setLoadError(e.message));
    return () => closeRef.current?.();
  }, []);

  const patch = (id, fn) => setRuns((r) => ({ ...r, [id]: fn(r[id] ?? blank()) }));

  const settle = (message) =>
    setRuns((r) =>
      Object.fromEntries(
        Object.entries(r).map(([id, run]) =>
          run.status === "waiting" || run.status === "streaming"
            ? [id, { ...run, status: "error", error: message }]
            : [id, run]
        )
      )
    );

  const start = useCallback(
    (prompt) => {
      closeRef.current?.();
      setRuns(Object.fromEntries(models.map((m) => [m.id, blank()])));
      setPhase("running");
      closeRef.current = openCompareStream(prompt, {
        onToken: ({ id, text }) => patch(id, (r) => ({ ...r, status: "streaming", text: r.text + text })),
        onComplete: ({ id, latencyMs, rank }) => patch(id, (r) => ({ ...r, status: "done", latencyMs, rank })),
        onFailure: ({ id, message }) => patch(id, (r) => ({ ...r, status: "error", error: message })),
        onDone: () => setPhase("finished"),
        onConnectionError: () => {
          settle("Connection to the server was lost. Check that the backend is running.");
          setPhase("finished");
        },
      });
    },
    [models]
  );

  const stop = useCallback(() => {
    closeRef.current?.();
    settle("Stopped before this model finished.");
    setPhase("finished");
  }, []);

  return { models, loadError, runs, phase, start, stop };
}
