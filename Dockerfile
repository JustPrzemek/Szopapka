
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV GOOGLE_APPLICATION_CREDENTIALS=/app/firebase.json

ENTRYPOINT ["java", "-jar", "app.jar"]
