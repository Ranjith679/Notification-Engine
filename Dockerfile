# start the image with Java 17 JRE
FROM eclipse-temurin:17-jre

# Application directory inside the container
WORKDIR /app

# Copy the built Spring Boot JAR
COPY target/*.jar app.jar

# Spring Boot runs on port 8080
EXPOSE 8080

# run this command when container starts to start spring boot app
ENTRYPOINT ["java", "-jar", "app.jar"]