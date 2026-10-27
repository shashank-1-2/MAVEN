package com.telusko.SpringAiDemo.service;

import com.telusko.SpringAiDemo.dto.Events.ModelInfo;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** One ChatClient per provider. Adding a provider = adding one register(...) line. */
@Component
public class ModelRegistry {

    public record Entry(ModelInfo info, ChatClient client) {}

    private static final String SYSTEM = "You are a clear, well-structured assistant. Use Markdown. Keep answers focused.";

    private final Map<String, Entry> entries = new LinkedHashMap<>();

    public ModelRegistry(OpenAiChatModel openAi,
                         AnthropicChatModel anthropic,
                         OllamaChatModel ollama,
                         @Value("${spring.ai.openai.chat.options.model}") String openAiModel,
                         @Value("${spring.ai.anthropic.chat.options.model}") String anthropicModel,
                         @Value("${spring.ai.ollama.chat.options.model}") String ollamaModel) {
        register("openai", "OpenAI", openAiModel, openAi);
        register("anthropic", "Anthropic", anthropicModel, anthropic);
        register("ollama", "Ollama", ollamaModel, ollama);
    }

    private void register(String id, String label, String model, ChatModel chatModel) {
        ChatClient client = ChatClient.builder(chatModel).defaultSystem(SYSTEM).build();
        entries.put(id, new Entry(new ModelInfo(id, label, model), client));
    }

    public List<ModelInfo> infos() {
        return entries.values().stream().map(Entry::info).toList();
    }

    public Map<String, Entry> all() {
        return entries;
    }
}
