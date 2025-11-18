# Stage 1
FROM maven:3.9.11-eclipse-temurin-21-alpine AS Builder
WORKDIR /app
COPY pom.xml pom.xml
COPY src ./src
RUN mvn package -Dmaven.test.skip=true dependency:go-offline
# Stage 2
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

