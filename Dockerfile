FROM openjdk:17-jdk-slim

ARG JAR_FILE=/build/libs/linker-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /linker.jar

ENTRYPOINT ["java","-jar", "/linker.jar"]

# "-Dspring.profiles.active=prod",