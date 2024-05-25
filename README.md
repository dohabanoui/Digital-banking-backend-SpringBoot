# Bank Account Management Application
This application allows the management of bank accounts belonging to different clients. Each account can undergo multiple DEBIT or CREDIT operations. There are two types of accounts: Current Accounts and Savings Accounts. The application is built using Spring Boot for the backend and Angular for the frontend. It also includes a security layer based on Spring Security and JSON Web Token (JWT). For this repository, we will focus only on the backend part, but there is a corresponding frontend repository available for the frontend.
## Features

### Client Management:
- Input, addition, deletion, editing, and search of clients.

### Account Management:
- Addition of accounts, search, and administration of accounts.
- Two types of accounts: Current Accounts and Savings Accounts.

### Transaction Management:
- Recording DEBIT and CREDIT type operations for each account.

### Authentication and Security:
- User and password management.
- Authentication based on Spring Security and JWT.
- Recording Actions: For each client, account, and recorded operation, record the identifier of the authenticated user who performed the operation.

## Dependencies

Important Dependencies Used in This Project

1. spring-boot-starter-data-jpa:
   - Integration of JPA (Java Persistence API) for data access and entity management.

2. spring-boot-starter-web:
   - Provides necessary functionalities for developing web applications with Spring Boot, including the embedded web server.

3. mysql-connector-java:
   - Enables connection to a MySQL database using the JDBC connector.

4. lombok:
   - Simplifies development by automatically generating boilerplate code such as accessors and constructors.

5. springdoc-openapi-ui:
   - Automatically generates API documentation based on OpenAPI (formerly Swagger) and provides a user interface to explore and test the API.

6. spring-boot-starter-test:
   - Provides tools and libraries for writing unit and integration tests in Spring Boot applications.

7. hibernate-validator:
   - Enables validation of constraints on business objects using Hibernate's validation annotations.

8. OAuth2 Resource Server:
   - Used for authentication and security purposes.

These dependencies are utilized to facilitate the development of a Spring Boot application with features such as data access, web development, API documentation, and data validation.


## Swagger Documentation

To view and test the methods, Swagger can be utilized. Access Swagger documentation via the following link:
(http://localhost:8085/swagger-ui.html)

## Project Structure

### Entities:
Entities represent the business objects of the application and are used to represent database tables. They contain attributes and relationships that describe the application's data.

### Repositories:
Repositories are used for data access. They provide methods for performing CRUD (Create, Read, Update, Delete) operations on entities, making interaction with the database easier.

### DTOs:
DTOs are used to transfer data between different layers of the application. They allow for structuring and transferring only the necessary data, thus improving performance and security.

### Mappers:
Mappers are used to convert objects between different representations, such as between entities and DTOs. They facilitate data transformation by performing appropriate mapping operations.

### Web:
The Web layer provides components such as RestControllers that expose the application's functionalities via REST APIs. It handles incoming HTTP requests and returns appropriate responses.

### Service:
The service layer contains the business logic of the application. It handles complex operations, performs validations, coordinates interaction between different entities, and manages transactions.

### Exception:
Exceptions are used to handle errors and exceptional situations in the application. They allow for handling failure cases, validation errors, or any other situation that requires special handling.




