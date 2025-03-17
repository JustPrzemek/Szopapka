FROM openjdk:21-jdk

RUN groupadd -r app && useradd -r -g app app

WORKDIR /app

COPY target/*.jar app.jar

USER app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]