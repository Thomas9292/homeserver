#FROM arm32v7/adoptopenjdk
FROM openjdk:8
RUN addgroup spring
RUN adduser spring --ingroup spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080