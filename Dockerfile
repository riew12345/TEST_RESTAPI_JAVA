FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY testrestapi/target/testrestapi-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app.jar"]
