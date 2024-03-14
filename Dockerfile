FROM openjdk:17

ARG JAR_FILE=target/student-management-system-0.0.1-SNAPSHOT.jar

COPY target/student-management-system-0.0.1-SNAPSHOT.jar student-management-system-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} student-management-system-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","student-management-system-0.0.1-SNAPSHOT.jar"]