FROM openjdk:22-jdk
WORKDIR /app
COPY target/cart-service.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]