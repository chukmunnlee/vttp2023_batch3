FROM maven:3-eclipse-temurin-20 AS builder

WORKDIR /app

COPY mvnw .
COPY pom.xml .
COPY src src

RUN mvn clean package -Dmaven.test.skip=true

# /app/target/dovbear-0.0.1-SNAPSHOT.jar

FROM openjdk:20-slim

WORKDIR /app

COPY --from=builder /app/target/dovbear-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=3000

EXPOSE ${PORT}

ENTRYPOINT java -jar app.jar