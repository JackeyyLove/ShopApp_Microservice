FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} product.jar

ENTRYPOINT ["java", "-jar", "/product.jar"]

EXPOSE 8080