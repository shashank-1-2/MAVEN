package com.telusko.SpringAiDemo.controller;

import com.telusko.SpringAiDemo.dto.Events.ModelInfo;
import com.telusko.SpringAiDemo.service.CompareService;
import com.telusko.SpringAiDemo.service.ModelRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompareController {

    private static final int MAX_PROMPT = 4000;

    private final CompareService service;
    private final ModelRegistry registry;

    public CompareController(CompareService service, ModelRegistry registry) {
        this.service = service;
        this.registry = registry;
    }

    @GetMapping("/models")
    public List<ModelInfo> models() {
        return registry.infos();
    }

    @GetMapping(value = "/compare/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Object>> stream(@RequestParam String prompt) {
        String p = prompt == null ? "" : prompt.strip();
        if (p.isEmpty() || p.length() > MAX_PROMPT) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prompt must be 1-" + MAX_PROMPT + " characters");
        }
        return service.compare(p);
    }
}
