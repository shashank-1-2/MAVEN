# LLM Arena

Spring AI + React app that sends one prompt to OpenAI, Anthropic and Ollama in parallel, streams every answer live, and ranks the models by finish time.

```
llm-arena/
├── backend/    Spring Boot 3.4 · Spring AI 1.0 · Java 21 (package com.telusko.SpringAiDemo)
├── frontend/   React 18 · Vite · Framer Motion
├── docker-compose.yml
└── .env.example
```

## Run locally
1. `ollama pull gemma2` (and keep Ollama running)
2. Backend: `export OPENAI_API_KEY=... ANTHROPIC_API_KEY=...` then `cd backend && mvn spring-boot:run`
3. Frontend: `cd frontend && npm install && npm run dev` → http://localhost:5173

## Deploy with Docker
```
cp .env.example .env   # add your keys
docker compose up --build -d
```
Open http://localhost. Nginx serves the React build and proxies `/api` (SSE-safe) to Spring.

## API
- `GET /api/models` – providers and model names
- `GET /api/compare/stream?prompt=...` – SSE events: `token`, `complete` (with latency and rank), `failure`, `done`

## Add another model
Add the Spring AI starter in `pom.xml`, inject its `ChatModel` in `ModelRegistry`, add one `register(...)` line, and add an accent color in `frontend/src/constants.js`.

## Before going public
Add authentication and rate limiting in front of `/api/compare/stream`. Every request spends your API credits.

## Backend layout
```
backend/src/main/java/com/telusko/SpringAiDemo/
├── SpringAiDemoApplication.java
├── CompareController.java      /api/models, /api/compare/stream (used by the React app)
├── OpenAIController.java       /api/openai/{message}
├── AnthropicController.java    /api/anthropic/{message}
├── OllamaController.java       /api/ollama/{message}
├── config/CorsConfig.java
├── service/ModelRegistry.java, CompareService.java
└── dto/Events.java
```
