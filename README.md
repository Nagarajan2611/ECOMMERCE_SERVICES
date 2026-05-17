# E-Commerce Backend Application

## Project Description
This is a Spring Boot based E-Commerce backend application developed using Java, Spring Boot, Spring Security, JWT, Hibernate, and MySQL.

The project provides REST APIs for user authentication, product management, cart management, and order processing.

---

## Features

- User Registration
- User Login
- JWT Authentication
- Role Based Authorization
- Product Management
- Cart Management
- Order Management
- Validation
- Exception Handling
- REST APIs

---

## Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL
- Maven

---

## Project Structure

src/main/java
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── config
└── security

---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register User |
| POST | /auth/login | Login User |
| GET | /products | Get All Products |
| POST | /cart/add | Add Product To Cart |

---

## Security

- JWT Token Authentication
- Password Encryption
- Role Based Access

---

## Validation

The project uses validation annotations such as:

- @NotBlank
- @Email
- @Size

---

## Exception Handling

Global exception handling is implemented using:

- @RestControllerAdvice
- Custom Exceptions

---

## Database

Database used:
- MySQL

---

## Screenshots

### Swagger UI
![Swagger UI](screenshots/swagger-ui.png)

### Login API
![Login API](screenshots/login-api.png)

### Cart API
![Cart API](screenshots/cart-api.png)

---

## How To Run

1. Clone the repository

2. Open the project in IntelliJ IDEA

3. Configure MySQL database

4. Update application.properties

5. Run the application

---

## Author

Nagarajan