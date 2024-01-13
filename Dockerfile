FROM openjdk:17-jdk-slim

ARG JAR_FILE=/core-api/build/libs/linker.jar

COPY ${JAR_FILE} /linker.jar

ENTRYPOINT ["java","-jar", "/linker.jar"]

# "-Dspring.profiles.active=prod",