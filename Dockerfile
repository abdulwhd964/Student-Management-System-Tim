# Use Maven image as a builder
FROM maven:3.8.4-openjdk-17 AS builder

# Copy the Maven project and build it
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:17

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/student-management-system-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} student-management-system-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","student-management-system-0.0.1-SNAPSHOT.jar"]