package com.telusko.SpringAiDemo.dto;

/** Payloads sent to the browser over SSE. */
public final class Events {
    private Events() {}

    public record ModelInfo(String id, String label, String model) {}
    public record Token(String id, String text) {}
    public record Complete(String id, long latencyMs, int rank) {}
    public record Failure(String id, String message) {}
}
