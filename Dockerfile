FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 9002

ENTRYPOINT ["java","-jar", "/app/user-service-0.0.1-SNAPSHOT.jar"]