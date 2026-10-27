import { useEffect, useRef, useState } from "react";
import { motion } from "framer-motion";
import ReactMarkdown from "react-markdown";
import { ACCENTS } from "../constants";

export default function ModelCard({ model, run }) {
  const bodyRef = useRef(null);
  const [copied, setCopied] = useState(false);
  const { status, text, rank, latencyMs, error } = run;

  useEffect(() => {
    if (status === "streaming" && bodyRef.current) bodyRef.current.scrollTop = bodyRef.current.scrollHeight;
  }, [text, status]);

  const copy = async () => {
    await navigator.clipboard.writeText(text);
    setCopied(true);
    setTimeout(() => setCopied(false), 1400);
  };

  return (
    <motion.article
      layout
      transition={{ type: "spring", stiffness: 220, damping: 26 }}
      className={`card ${status} ${rank === 1 ? "winner" : ""}`}
      style={{ "--accent": ACCENTS[model.id] ?? "#a78bfa" }}
    >
      <header className="card-head">
        <div>
          <h2>{model.label}</h2>
          <span className="model-chip">{model.model}</span>
        </div>
        {rank && (
          <span className="rank" title={`Finished ${rank === 1 ? "first" : `#${rank}`}`}>
            {rank === 1 ? "Fastest" : `#${rank}`}
          </span>
        )}
      </header>

      <div className="card-body" ref={bodyRef} aria-live="off">
        {status === "idle" && <p className="idle">This answer will appear here as soon as you send a prompt.</p>}
        {status === "waiting" && (
          <div className="skeleton" aria-label="Waiting for first token">
            <i /><i /><i style={{ width: "62%" }} />
          </div>
        )}
        {status === "error" && (
          <div className="error">
            <strong>This model didn’t answer.</strong>
            <p>{error}</p>
          </div>
        )}
        {text && (
          <div className="md">
            <ReactMarkdown>{text}</ReactMarkdown>
            {status === "streaming" && <span className="caret" />}
          </div>
        )}
      </div>

      <footer className="card-foot">
        <span>{latencyMs ? `${(latencyMs / 1000).toFixed(2)}s total` : status === "streaming" ? "Writing…" : " "}</span>
        {text && <button type="button" className="link-btn" onClick={copy}>{copied ? "Copied" : "Copy answer"}</button>}
      </footer>
    </motion.article>
  );
}
