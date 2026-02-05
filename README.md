# SettleWise – Ledger Reconciliation Platform (WIP)

SettleWise is an internal ledger & reconciliation platform designed to
correlate payment events emitted independently by wallets, banks,
and merchants.

## Current Status
- External systems emit Kafka events
- Wallet, bank, and merchant producers implemented
- Events keyed by transactionId
- Supports realistic states like PENDING

## Tech Stack
- Java, Spring Boot
- Apache Kafka
- Docker

## Next
- Event ingestion service
- Deduplication & normalization
- Reconciliation logic
