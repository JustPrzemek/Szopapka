FROM openjdk:21-jdk

RUN groupadd -r app && useradd -r -g app app

USER app

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]