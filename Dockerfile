FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar student-management-system-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/student-management-system-0.0.1-SNAPSHOT.jar"]