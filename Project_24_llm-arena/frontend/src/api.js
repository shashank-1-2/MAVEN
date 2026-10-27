const BASE = import.meta.env.VITE_API_BASE ?? "";

export async function fetchModels() {
  const res = await fetch(`${BASE}/api/models`);
  if (!res.ok) throw new Error(`Backend responded ${res.status}`);
  return res.json();
}

/** Opens the SSE race. Returns a function that closes it. */
export function openCompareStream(prompt, h) {
  const es = new EventSource(`${BASE}/api/compare/stream?prompt=${encodeURIComponent(prompt)}`);
  let finished = false;
  const on = (name, fn) => es.addEventListener(name, (e) => fn(JSON.parse(e.data)));

  on("token", h.onToken);
  on("complete", h.onComplete);
  on("failure", h.onFailure);
  es.addEventListener("done", () => {
    finished = true;
    es.close();
    h.onDone();
  });
  es.onerror = () => {
    if (finished) return;
    finished = true;
    es.close();
    h.onConnectionError();
  };
  return () => {
    finished = true;
    es.close();
  };
}
