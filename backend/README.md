## About

This project is a hands-on practice SDK designed for a complete end-to-end e-commerce application.

## Tech Stack

- **Development** - Spring Boot
- **Datasource** - PostgreSQL
- **Caching** - Redis
- **PubSub** - Kafka for pub-sub
- **Temporal** - Workflow orchestration
- **Debezium** - Change data capture.
- **Prometheus** - Monitoring and alerting.
- **Grafana** - Visualization.
- **Open Policy Agent (OPA)** - Authorization.

## Business services

### Auth Service

#### Login API

- Generates a **JWT token** for the user based on the provided username and password.
- The token is signed using a symmetric key.
- Token contains **user uuid as subject** and **user role as a claim**.

---

### Catalog Service

- Uses **CDC debezium connector** to sync data from Postgres to Redis via Kafka using Spring Integration.

#### Get Product using ID API

- Returns the product details for a given product ID.
- Fetch product data from Cache and stock data from Postgres database.

---

### Order Service

#### Create Order API

- Creates a new order for a user.
- Order workflow is implemented using **Temporal**.

## Utility Services

### Eureka Service

- Service discovery for all microservices in the application.

### Gateway Service

- Acts as a reverse proxy to route requests to the appropriate microservices.
- Every business service has context path.