
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENV GOOGLE_APPLICATION_CREDENTIALS=/app/firebase.json

CMD echo $FIREBASE_SECRET | base64 --decode > /app/firebase.json && \
    java -jar app.jar
