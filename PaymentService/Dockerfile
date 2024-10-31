FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} payment.jar

ENTRYPOINT ["java", "-jar", "/payment.jar"]

EXPOSE 8081