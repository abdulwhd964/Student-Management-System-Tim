# Student Management System

This repository contains the source code for Student Management System. where student can be created, updated , deleted and read all students

## Features

### 1. CRUD Operations

- **Create:** Creating an student : http://localhost:8080/api/students , Method: POST
- **Update:** Updating an student : http://localhost:8080/api/students/1 , Method: PUT
- **Read All Student:** Get All student : http://localhost:8080/api/students , Method: GET
- **Read Student:** Get student by id: http://localhost:8080/api/students/1 , Method: GET
- **Delete Student:** Delete Student by id : http://localhost:8080/api/students/1 , Method: Delete 

### 2. Validation

- name: should not be empty
- age: age should not be empty and age should be greater than or equal to 15 
- dateOfbirth: DateOfBirth should not be empty and should be greater than 15 years
- email: email should be valid format and should not be empty

### 3. Logging

- Logger has been handled by SLF4J and also implemenented ELK Stack to view the logs
- logstash.conf is available able under resource folder
- Use this: if you have ELK Setup : Kibana URL: http://localhost:5601
- Kibana Logs Image ![Alt text](https://github-production-user-asset-6210df.s3.amazonaws.com/61117499/313422562-44b4712a-5e44-435e-99cc-5dd1be71d6ef.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240316%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240316T180519Z&X-Amz-Expires=300&X-Amz-Signature=3795d5f81df33eff92acadc12da26cd69f592fdd7d7ca052fb125af047790335&X-Amz-SignedHeaders=host&actor_id=61117499&key_id=0&repo_id=764009502)

### 4. Transactional

- @Transactional has been handled incase of exception it will rollback the transaction

### 5. Swagger Documentation

- Access the Swagger documentation for the API at http://localhost:8080/api/swagger-ui.html

### 6. JWT Token

- Jwt token is handled to authenticate the user to access the resource.

### 7. Integrated with React

- Integrated with React with the localhost:3000 and allow the react to communicate with springboot application
- FrontEnd Code is also deployed with render you can access at https://student-management-tim-ui.onrender.com

### 8. Caching

- Spring caching has been implemenated to improve the performance by storing the results

### 9. Builder Pattern

- The builder pattern allows you to create a fluent API for constructing objects. This results in more readable 
and expressive code when creating response objects, making it easier to understand the intent of the code.

### 10. Controller Tests

- Test case has been written for all the api and also failure case been implemented

### 11. Request Logging

- using CommonsRequestLoggingFilter, the application has been designed to print all the incoming requests

### 12. AOP with Before and After

- AOP has been implemenented to check the execution for each api (@Before and @After)

### 13. Exception Handler

- We have implemented a global exception handler using @ControllerAdvice and @ExceptionHandler annotations to handle exceptions thrown by controllers across the application.

### 14. Inmemory Database

- H2 Database URL: http://localhost:8080/api/h2-console 
- username: sa
- password: password

### 15. Deployment with Docker

- cd your-project-directory
  - Build Docker Image: Navigate to the project directory and build the Docker image using the provided Dockerfile. <br>
  - Build Command: 
    - docker build -t docker buildx build -t student-management-tim .
  - Run Docker Container: Once the image is built, you can run it using Docker.<br>
  - Run Command:
    - docker run -p 8080:8080 student-management-tim .

### 16. Render Deployment
- This application has been deployed with Render using Docker Image, you can access this application via https://student-management-system-tim.onrender.com/api/swagger-ui/index.html

## Setup

- Java 17 is required to run the application
- Git clone
- mvn clean install
- Make sure you have lombok in Eclipse IDE or you can use IntelliJ
- navigate to the main, Run as Java application
- application starts with the default port : 8080

## Dependencies

- spring-web
- lombok
- jwt
- h2
- data-jpa
- actuator
- devtools
- mapstruct
- junit
- mockito
- commons-lang
- swagger
- aop
- Java 17


## License

This project is licensed under the Abdul Wahid - see the [LICENSE](LICENSE) file for details.
