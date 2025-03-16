FROM openjdk:21-jdk

RUN groupadd -r app && useradd -r -g app app

USER app

WORKDIR /app

COPY target/*.jar app.jar
COPY src/main/resources/szopapka-firebase.json /app/szopapka-firebase.json

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]