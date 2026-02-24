FROM openjdk:22-jdk

WORKDIR /app

COPY target/api-gateway.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
