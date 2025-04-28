# Business-to-Consumer (B2C) Payment Service

This project is a RESTful microservice built with **Spring Boot** for processing B2C payments through mobile money platforms such as **M-Pesa** and **Airtel Money** in Kenya. The service sends notifications via SMS to the recipient upon success or failure of the transaction.

## Features

- **Process B2C payments** via integration with mobile money APIs.
- **Send SMS notifications** using an SMS gateway (mocked in this implementation).
- **OAuth2 Authentication** for secure API access.
- **Logging and Error Handling** for observability and debugging.
- **Unit and Integration Tests** to ensure correctness.
- **Dockerized** for easy deployment.
- **In-memory database (H2)** used for persistence.

## Project Setup

### Prerequisites
- Java 11 or higher
- Maven or Gradle (Maven recommended)
- Docker (for containerization)
- An IDE like IntelliJ IDEA or Visual Studio Code

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/business-to-consumer-payment-service.git
   cd business-to-consumer-payment-service
