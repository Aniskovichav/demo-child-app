FROM openjdk:21-jdk
WORKDIR /app
COPY target/child-service-*.jar child-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/child-service.jar"]