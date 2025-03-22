FROM openjdk:21-jdk-slim AS builder
WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

CMD ["java", "-jar", "app.jar"]
