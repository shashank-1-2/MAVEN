package com.telusko.SpringAiDemo.service;

import com.telusko.SpringAiDemo.dto.Events.Complete;
import com.telusko.SpringAiDemo.dto.Events.Failure;
import com.telusko.SpringAiDemo.dto.Events.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CompareService {

    private static final Logger log = LoggerFactory.getLogger(CompareService.class);

    private final ModelRegistry registry;
    private final Duration timeout;

    public CompareService(ModelRegistry registry, @Value("${app.timeout-seconds}") long timeoutSeconds) {
        this.registry = registry;
        this.timeout = Duration.ofSeconds(timeoutSeconds);
    }

    /** Streams every model in parallel; `rank` is the order in which models finish. */
    public Flux<ServerSentEvent<Object>> compare(String prompt) {
        AtomicInteger finishOrder = new AtomicInteger();
        var lanes = registry.all().values().stream()
                .map(e -> lane(e.info().id(), e.client(), prompt, finishOrder))
                .toList();
        return Flux.merge(lanes).concatWith(Mono.just(event("done", Map.of())));
    }

    private Flux<ServerSentEvent<Object>> lane(String id, ChatClient client, String prompt, AtomicInteger finishOrder) {
        long started = System.nanoTime();
        return client.prompt().user(prompt).stream().content()
                .map(chunk -> event("token", new Token(id, chunk)))
                .timeout(timeout)
                .concatWith(Mono.fromSupplier(() -> event("complete",
                        new Complete(id, (System.nanoTime() - started) / 1_000_000, finishOrder.incrementAndGet()))))
                .onErrorResume(ex -> {
                    log.warn("Model '{}' failed: {}", id, ex.toString());
                    return Flux.just(event("failure", new Failure(id, friendly(ex))));
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static String friendly(Throwable ex) {
        String m = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        return m.length() > 220 ? m.substring(0, 220) + "…" : m;
    }

    private static ServerSentEvent<Object> event(String name, Object data) {
        return ServerSentEvent.builder(data).event(name).build();
    }
}
