FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inditex-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/inditex-api.jar"]
