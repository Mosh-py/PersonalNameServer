# Stage 1
FROM maven:3.9.11-eclipse-temurin-21-alpine
WORKDIR /app
COPY pom.xml pom.xml
COPY src ./src
RUN mvn -q -e -DskipTests dependency:resolve dependency:resolve-plugins
RUN mvn -q -e -DskipTests package
RUN cp target/*.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
