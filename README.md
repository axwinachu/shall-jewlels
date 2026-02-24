# ShaaJewels — Event-Driven Microservices Jewelry Platform

## 📌 Overview

ShaaJewels is a production-style microservices backend system designed to simulate a scalable jewelry e-commerce platform.
The project demonstrates modern backend engineering practices including API Gateway routing, service discovery, asynchronous communication using Kafka, secure authentication, and AI integration.

The architecture follows real industry patterns used in distributed systems and cloud-native applications.

---

## 🚀 Key Features

* ✅ Microservices-based architecture
* ✅ API Gateway centralized routing
* ✅ Service discovery using Eureka
* ✅ Event-driven communication with Apache Kafka
* ✅ Asynchronous Email Notification Service
* ✅ Order, Cart, Product, and User management
* ✅ Secure authentication (JWT/OAuth2 ready)
* ✅ AI integration using Spring AI
* ✅ Monitoring & observability ready

---

## 🏗️ System Architecture

```
                         ┌────────────────────┐
                         │       Client       │
                         └─────────┬──────────┘
                                   │
                              API Gateway
                                   │
 ┌──────────────┬──────────────┬──────────────┬──────────────┐
 │              │              │              │              │
User Service  Product Service Cart Service  Order Service  AI Service
                                                     │
                                                     │
                                               Kafka Event Bus
                                                     │
                                               Email Service
```

All services are registered dynamically with **Eureka Discovery Server**.

---

## 🧩 Microservices Description

### 🔐 User Service

* User registration & authentication
* JWT token handling
* Secure user management APIs

### 🛍️ Product Service

* Jewelry catalog management
* Product listing & inventory handling

### 🛒 Cart Service

* Add/remove products
* User-specific cart management

### 📦 Order Service

* Order placement & tracking
* Publishes order events to Kafka
* Triggers asynchronous workflows

### 📧 Email Service

* Kafka consumer service
* Receives order events
* Sends order confirmation notifications asynchronously

### 🌐 API Gateway

* Single entry point for all requests
* Route management
* Authentication validation
* Load-balanced service calls

### 🔎 Eureka Discovery Server

* Service registration & discovery
* Dynamic service communication

### 🤖 AI Service

* Integrated using Spring AI
* Generates intelligent responses
* Provider-sOpenAI 

---

## ⚡ Event-Driven Communication (Kafka)

ShaJewels uses Kafka for **asynchronous service communication**.

### Order Processing Flow

```
User Places Order
        ↓
Order Service saves order
        ↓
Order Service publishes event → Kafka Topic (order-created)
        ↓
Email Service consumes event
        ↓
Confirmation Email Sent
```

### Benefits

* Non-blocking operations
* Service decoupling
* Improved scalability
* Fault tolerance
* Retry capability

---

## 🧰 Technology Stack

### Backend

* Java 21
* Spring Boot 3.x
* Spring Cloud
* Spring Security
* Spring AI

### Microservices Infrastructure

* Spring Cloud Gateway
* Eureka Service Discovery
* Apache Kafka

### Database

* MySQL
* Spring Data JPA

### Messaging

* Kafka Producer & Consumer

### Observability

* Spring Boot Actuator
* Micrometer
* Prometheus & Grafana compatible

---

## 📂 Project Structure

```
shajewels/
│
├── api-gateway/
├── discovery-server/
├── user-service/
├── product-service/
├── cart-service/
├── order-service/
├── email-service/
└── eureka--server
```

---

## ⚙️ Configuration

### Environment Variables

```
DB_USERNAME=root
DB_PASSWORD=password
KAFKA_SERVER=localhost:9092
OPENAI_API_KEY=your_key
```

---

### Example application.yml

```yaml
spring:
  application:
    name: order-service

  datasource:
    url: jdbc:mysql://localhost:3306/shajewels
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  kafka:
    bootstrap-servers: ${KAFKA_SERVER}
```

---

## ▶️ Running the Project

### 1️⃣ Start Infrastructure

* MySQL Database
* Kafka & Zookeeper

### 2️⃣ Start Services (Order Matters)

1. eureka-server
2. api-gateway
3. user-service
4. product-service
5. cart-service
6. order-service
7. email-service

Run each service:

```
mvn spring-boot:run
```

---

## 🔐 Security Flow

```
Client Login
     ↓
User Service Authentication
     ↓
JWT Token Generated
     ↓
API Gateway validates token
     ↓
Request routed to microservices
```

---

## 🤖 AI Integration Example

```
GET /ai/ask?q=Recommend wedding jewelry
```

AI Service processes the request and returns an intelligent response using Spring AI.

---

## 📈 Monitoring & Health

Actuator endpoints:

```
/actuator/health
/actuator/metrics
/actuator/prometheus
```

Ready for Prometheus + Grafana dashboards.

---

## 📦 Future Enhancements

* Kubernetes deployment
* Distributed tracing (OpenTelemetry)
* Redis caching layer
* Payment gateway integration
* Recommendation engine using AI
* Saga pattern for distributed transactions

---

## 👨‍💻 Author

**Aswin R**
Java Backend Developer | Microservices & Distributed Systems Enthusiast

---

## ⭐ Project Objective

ShaaJewels demonstrates how a traditional commerce application can evolve into a **cloud-ready, event-driven, AI-enabled microservices platform** following modern industry best practices.

---
