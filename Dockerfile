# Use the official OpenJDK base image with Java 17
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/student-management-system-0.0.1-SNAPSHOT.jar /Student-Management-System-Tim/student-management-system-0.0.1-SNAPSHOT.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Define the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "student-management-system-0.0.1-SNAPSHOT.jar"]
