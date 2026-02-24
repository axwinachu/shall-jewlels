FROM openjdk:22-jdk

WORKDIR /app

COPY target/user-service.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java","-jar","app.jar"]
