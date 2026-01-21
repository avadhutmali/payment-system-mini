# Mini Payment System (PayPay-inspired)

A backend payment system that simulates real-world digital transactions with a strong focus on **transactional correctness**, **ledger-based accounting**, and **backend system design**.

This project is inspired by large-scale FinTech systems and is built to demonstrate production-oriented backend engineering practices rather than just CRUD operations.

---

## Key Features

- QR-based payment flow (simulated)
- Ledger-driven accounting model (append-only ledger)
- Atomic transaction processing with rollback on failure
- Idempotent payment handling to prevent duplicate transactions
- Secure REST APIs built with Spring Boot
- React-based frontend for payments and transaction history
- ACID-compliant persistence using PostgreSQL
- Dockerized setup for consistent local and deployment environments

---

## Tech Stack

### Backend
- Java 17
- Spring Boot 3
- Spring Data JPA
- Spring Security (JWT-based authentication)
- PostgreSQL
- Swagger / OpenAPI

### Infrastructure
- Docker & Docker Compose

---

## System Design Overview

### Core Components
- **Account**: Represents user wallets and balances (stored in smallest currency unit)
- **Transaction**: Tracks payment lifecycle (INITIATED, SUCCESS, FAILED)
- **Ledger Entry**: Append-only debit/credit records for auditability and correctness

### Design Principles
- No floating-point arithmetic for money
- Ledger entries are immutable
- Balance updates are always wrapped in database transactions
- Clear separation of controller, service, and persistence layers

---

## Project Structure

```
paypay-mini/
├── backend/
│   ├── src/main/java/com/example/paypaymini
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── config/
│   └── src/main/resources/
│       └── application.properties
│
│
└── docker-compose.yml
```

---

## Running the Project (Local)

### Prerequisites
- Java 17
- Node.js 18+
- PostgreSQL
- Docker (optional)

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Swagger UI
```
http://localhost:8080/swagger-ui.html
```

---

## Running with Docker

```bash
docker-compose up --build
```

This starts:
- Backend service
- PostgreSQL database

---

## Testing Strategy

- Unit tests for service-layer business logic
- Repository tests using Testcontainers
- Focus on edge cases: insufficient balance, duplicate requests, partial failures

---
