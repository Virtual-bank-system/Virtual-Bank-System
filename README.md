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


## Postman Collections  

To simplify API testing, ready-made Postman collections are provided in the  
[`postman-collections`](https://github.com/Virtual-bank-system/Virtual-Bank-System/tree/master/postmanCollections) folder.  folder.  


### Available Collections  

- [**User Service.postman_collection.json**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/User%20Service.postman_collection.json) → User Service endpoints  
- [**Account Service.postman_collection.json**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/Account%20Service.postman_collection.json) → Account Service endpoints  
- [**Transaction Service.postman_collection.json**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/Transaction%20Service.postman_collection.json) → Transaction Service endpoints  
- [**BFF Service.postman_collection.json**](https://github.com/Virtual-bank-system/Virtual-Bank-System/blob/master/postmanCollections/BFF%20Service.postman_collection.json) → BFF Service endpoints  

---

## Import Instructions  

1. Open **[Postman](https://www.postman.com/web)**.  
2. Click **Import** → select the `.json` file(s) from the `postman-collections/` folder.  
3. The collections will appear **grouped by service**.  
4. Each collection includes example requests with placeholder IDs.  
   - Replace these with actual IDs once your services are running.  
