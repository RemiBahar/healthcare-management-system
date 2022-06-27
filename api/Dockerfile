FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patient-api-0.1.0.jar
ENTRYPOINT ["java","-jar","/patient-api-0.1.0.jar"]