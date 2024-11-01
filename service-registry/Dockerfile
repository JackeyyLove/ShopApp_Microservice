# Build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17

# Define the JAR_FILE argument with a default value
ARG JAR_FILE=target/*.jar

# Copy the built JAR from the previous stage
COPY --from=build /app/${JAR_FILE} app.jar

# Copy the OpenTelemetry Java agent
COPY opentelemetry-javaagent.jar opentelemetry-javaagent.jar

# Specify the ENTRYPOINT for the application
ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar", "-Dotel.service.name=SERVICE-REGISTRY", "-jar", "/app.jar"]

# Expose the necessary port
EXPOSE 8761
