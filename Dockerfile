#FROM eclipse-temurin:21-jdk

#WORKDIR /app

#COPY target/spring-cloud-app-0.0.1-SNAPSHOT.jar app.jar

#EXPOSE 8080

#ENTRYPOINT ["java","-jar","app.jar"]


# This Dockerfile creates a Spring Boot image
# using Java 21 runtime
# and runs the application on port 8080

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/spring-cloud-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]