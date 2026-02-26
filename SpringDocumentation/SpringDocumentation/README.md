📚 Spring Documentation AI Assistant
Shell-Based Local RAG System using Spring AI + Ollama + PGVector

An interactive CLI-based AI assistant that enables semantic search and question-answering over the official Spring Boot documentation using a fully local Retrieval-Augmented Generation (RAG) architecture.

Built with Spring Boot, Spring AI, Ollama, and PostgreSQL (PGVector) — no external AI APIs required.

🚀 Features

✅ Fully local LLM inference using Ollama

✅ Semantic search over 1000+ page Spring Boot documentation

✅ Vector storage using PostgreSQL + PGVector

✅ HNSW indexing for fast similarity search

✅ Cosine similarity distance metric

✅ Automatic PDF ingestion at startup

✅ Token-based chunking for better retrieval quality

✅ Interactive CLI using Spring Shell

✅ Optimized for 16GB RAM development environments

🏗 Architecture
Spring Boot PDF
        ↓
PagePdfDocumentReader
        ↓
TokenTextSplitter
        ↓
Embedding Model (Ollama - nomic-embed)
        ↓
PGVector (HNSW Index)
        ↓
Retriever
        ↓
LLM (Ollama - qwen2.5)
        ↓
Spring Shell CLI Response
🛠 Tech Stack

Java 21

Spring Boot

Spring AI

Spring Shell

Ollama (Local LLM Runtime)

PostgreSQL

PGVector Extension

HNSW Indexing

🧠 Models Used
Chat Model

qwen2.5:1.5b

Lightweight, efficient for local inference

Embedding Model

nomic-embed-fast

768-dimensional embeddings

Optimized for semantic similarity search

⚙️ Setup Instructions
1️⃣ Install Ollama
https://ollama.com/download

Pull required models:

ollama pull qwen2.5:1.5b
ollama pull nomic-embed-fast
2️⃣ Setup PostgreSQL + PGVector

Enable pgvector extension:

CREATE EXTENSION IF NOT EXISTS vector;
3️⃣ Configure application.yml
spring:
  shell:
    interactive:
      enabled: true

  datasource:
    url: jdbc:postgresql://localhost:5432/sbdocs
    username: admin
    password: password

  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: qwen2.5:1.5b
      embedding:
        model: nomic-embed-fast

    vectorstore:
      pgvector:
        initialize-schema: true
        index-type: HNSW
        distance-type: COSINE_DISTANCE
        dimensions: 768
4️⃣ Fix Windows JLine Terminal Issue

Add VM option:

-Djline.terminal=jni

This forces native Windows terminal provider.

5️⃣ Run Application
mvn clean install
mvn spring-boot:run
🖥 Example Usage

Once started:

shell:> ask "What is Spring Boot auto-configuration?"

The assistant retrieves relevant documentation chunks and generates a contextual answer.

📈 Performance Characteristics

Sub-second vector retrieval using HNSW

Memory usage optimized for 16GB RAM systems

Fully offline inference (no API latency)

Suitable for large technical documentation corpora

🔎 Key Engineering Highlights

Designed end-to-end RAG pipeline

Implemented PDF ingestion + token chunking

Tuned embedding dimensions to match PGVector schema

Debugged and resolved JLine terminal provider conflicts on Windows

Migrated from external Gemini APIs to fully local Ollama stack

Optimized index type and similarity metric for documentation retrieval

🧪 Future Improvements

Streaming LLM responses

Hybrid search (BM25 + Vector)

Web-based UI interface

Multi-document support

Response citation highlighting

Dockerized deployment

🎯 Why This Project?

This project demonstrates:

Practical LLM integration in backend systems

Vector database engineering

Performance optimization

System architecture thinking

Real-world RAG implementation using Java ecosystem
