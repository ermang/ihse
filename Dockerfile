FROM maven:3.8.4-jdk-11-slim

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn package

ENTRYPOINT ["java", "-jar", "target/ihse-1.0-SNAPSHOT.jar"]
