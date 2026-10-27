import { useState } from "react";
import { EXAMPLES } from "../constants";

const MAX = 4000;

export default function PromptConsole({ running, disabled, onSubmit, onStop }) {
  const [value, setValue] = useState("");
  const canSend = value.trim().length > 0 && !disabled;

  const submit = () => canSend && !running && onSubmit(value.trim());

  return (
    <section className="console" aria-label="Prompt">
      <label htmlFor="prompt" className="sr-only">Your prompt</label>
      <textarea
        id="prompt"
        value={value}
        maxLength={MAX}
        placeholder="Ask anything. Every model gets the same question at the same time."
        onChange={(e) => setValue(e.target.value)}
        onKeyDown={(e) => (e.metaKey || e.ctrlKey) && e.key === "Enter" && submit()}
        rows={3}
      />
      <div className="console-bar">
        <div className="chips">
          {EXAMPLES.map((ex) => (
            <button key={ex} type="button" className="chip" onClick={() => setValue(ex)} disabled={running}>
              {ex}
            </button>
          ))}
        </div>
        <span className="count">{value.length}/{MAX}</span>
        {running ? (
          <button type="button" className="btn btn-stop" onClick={onStop}>Stop race</button>
        ) : (
          <button type="button" className="btn btn-go" onClick={submit} disabled={!canSend}>
            Compare all models
          </button>
        )}
      </div>
    </section>
  );
}
