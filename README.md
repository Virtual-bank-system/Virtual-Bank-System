# Virtual Bank System  

A **virtual banking platform** built on **microservices** with Java Spring Boot.  
It uses a **Backend for Frontend (BFF)** and **WSO2 API Gateway** to provide secure and scalable banking services.  

## Core Architecture  

- **Microservices:** Users, Accounts, Transactions, and Logging.  
- **Backend for Frontend (BFF):** Simplifies frontend by aggregating backend calls.  
- **WSO2 API Gateway:** Central entry for routing, security, and rate limiting.  
- **Logging:** Kafka-based centralized monitoring and auditing.  

## Microservices  

### 1️⃣ User Service (Port: 8081)  
Handles registration, authentication, and profiles.  
- `POST /users/register` → Register user  
- `POST /users/login` → Login  
- `GET /users/{userId}/profile` → User profile  

### 2️⃣ Account Service (Port: 8084)  
Manages bank accounts.  
- `POST /accounts` → Create account  
- `GET /accounts/{accountId}` → Get account details  
- `GET /users/{userId}/accounts` → List accounts  
- `PUT /accounts/transfer` → Transfer between accounts  

### 3️⃣ Transaction Service (Port: 8080)  
Handles deposits, withdrawals, and transfers.  
- `GET /accounts/{accountId}/transactions` → Transaction history  
- `POST /transactions/transfer/initiation` → Initiate transfer  
- `POST /transactions/transfer/execution` → Execute transfer  

### 4️⃣ BFF (Port: 8083)  
Aggregates data for dashboards.  
- `GET /bff/dashboard/{userId}` → Dashboard data  

### 5️⃣ Logging Service (Port: 8082)  
Consumes Kafka messages and stores request/response logs for monitoring.  

## Technology Stack  

- **Language:** Java 21  
- **Framework:** Spring Boot  
- **Build Tool:** Maven  
- **API Gateway:** WSO2 API Manager  
- **Database:** MySQL / PostgreSQL  
- **Message Broker:** Apache Kafka  
- **API Documentation:** OpenAPI (Swagger)  
- **API Testing:** Postman  
- **Containerization:** Docker & Docker Compose  

## Setup Steps

### Database Setup
- Ensure you have a running instance of **MySQL** (or your chosen database).
- Create databases for each microservice:

```sql
CREATE DATABASE user_service_db;
CREATE DATABASE account_service_db;
CREATE DATABASE transaction_service_db;
CREATE DATABASE logging_service_db;
CREATE DATABASE bff_service_db;
```
### Service Configuration  
Each microservice has its own `application.properties` file.  

### Service Mapping  
The table below lists default **ports** and **databases** for each service: 

| Service            | Port  | Database                  |
|---------------------|-------|---------------------------|
| User Service        | 8081  | user_service_db           |
| Account Service     | 8084  | account_service_db        |
| Transaction Service | 8080  | transaction_service_db    |
| BFF Service         | 8083  | bff_service_db            |
| Logging Service     | 8082  | logging_service_db        |

```properties
# Service Identity
spring.application.name=ServiceName   # Unique per service
server.port=PORT_NUMBER

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/DATABASE_NAME
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

# Kafka (common)
spring.kafka.bootstrap-servers=localhost:29092

# Producer (for services that publish messages)
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.producer.properties.spring.json.add.type.headers=false

# Consumer (only needed for services that consume Kafka messages, e.g., Logging Service)

spring.kafka.consumer.group-id=service-group
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
spring.kafka.consumer.properties.spring.deserializer.value.delegate.class=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.value.default.type=apis.resources.LogMessage
spring.kafka.consumer.properties.spring.json.use.type.headers=false
spring.kafka.consumer.properties.spring.json.trusted.packages=apis.resources

# Kafka Topic
logging.kafka.topic=log-topic

# JSON Formatting
spring.jackson.serialization.indent_output=true
```


## Postman Collections  

To simplify API testing, ready-made Postman collections are provided in the  
[`postman-collections`](https://github.com/Virtual-bank-system/Virtual-Bank-System/tree/master/postmanCollections) folder.   


### Available Collections  

- [**User Service.postman_collection.yaml**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/user-service.yaml) → User Service endpoints  
- [**Account Service.postman_collection.yaml**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/account-service.yaml) → Account Service endpoints  
- [**Transaction Service.postman_collection.yaml**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/transaction-service.yaml) → Transaction Service endpoints  
- [**BFF Service.postman_collection.yaml**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/bff-service.yaml) → BFF Service endpoints  

---

## Import Instructions  

1. Open **[Postman](https://www.postman.com/web)**.  
2. Click **Import** → select the `.yaml` file(s) from the `postman-collections/` folder.  
3. The collections will appear **grouped by service**.  
4. Each collection includes example requests with placeholder IDs.  
   - Replace these with actual IDs once your services are running.  


