# SettleWise – Ledger Reconciliation Platform

SettleWise is an **internal financial ledger and reconciliation platform** designed to help teams understand **where money actually is** when payments flow through multiple asynchronous systems.

In real-world payment systems, wallets, banks, and merchants record the same transaction independently.
Because these systems are **event-driven and asynchronous**, records can arrive late, out of order, or duplicated — leading to mismatches, pending settlements, and money stuck in limbo.

SettleWise is built to model, ingest, and reconcile these signals reliably.

---

## 🚀 Project Status

**Current (Completed)**
- External systems layer implemented
- Wallet, Bank, and Merchant services emit Kafka events
- Separate Kafka topics per system
- Events keyed by `transactionId`
- Supports realistic payment states (e.g. `PENDING`, `DEBITED`, `FAILED`)

**In Progress / Next**
- Event ingestion service
- Event deduplication using `eventId`
- Canonical payment event model
- Immutable double-entry ledger
- Reconciliation engine
- Operational dashboard

---

## 🧱 System Architecture (High Level)

```
[ Wallet Service ] ─┐
[ Bank Service   ] ─┼─> Kafka Topics ──> Event Ingestion ──> Ledger & Reconciliation
[ Merchant Service] ─┘
```

Each external system publishes **independent events**.
SettleWise ingests and correlates them to explain inconsistencies instead of assuming perfect flows.

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot
- **Messaging:** Apache Kafka
- **Serialization:** JSON
- **Infra (Local):** Docker, Docker Compose
- **Future:** PostgreSQL, WebSockets, React (Ops Dashboard)

---

## 📂 Repository Structure

```
fintech-project/
├── payment-event-simulator/     # External systems (Kafka producers)
├── event-ingestion/             # (Upcoming) Kafka consumers & normalization
├── README.md
└── .gitignore
```

---

## ▶️ Running the External Systems (Local)

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Maven

### Start Kafka
```bash
docker-compose up
```

### Run the producer service
```bash
cd payment-event-simulator
mvn spring-boot:run
```

### Example APIs
- `POST /wallet/payment`
- `POST /bank/payment`
- `POST /merchant/payment`

Each endpoint publishes an event to its respective Kafka topic.

---

## 🎯 Design Principles

- Event-first, not database-first
- Expect inconsistencies instead of treating them as edge cases
- Immutability for auditability
- Idempotency via `eventId`
- Ordering guarantees via Kafka keying

---

## 📌 Non-Goals

- This is **not** a payment gateway
- This is **not** a wallet or bank system
- This focuses on **internal visibility and reconciliation**, not transaction processing

---

## 🧠 Why This Project

This project is part of a deeper exploration into:
- Distributed systems
- Event-driven architecture
- Fintech backend design
- Real-world failure modes in payments

Built as a **learning-in-public** project with production-inspired design choices.

---

## 📬 Author

**Hardeepsinh Parmar**  
Backend Engineer | Fintech & Distributed Systems  
GitHub: https://github.com/hardeep652