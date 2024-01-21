FROM --platform=linux/amd64 openjdk:17.0.1-jdk-slim

RUN apt-get -y update && apt -y install wget && apt -y install unzip && apt -y install curl && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt -y install ./google-chrome-stable_current_amd64.deb && \
    wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/` \
    curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip chromedriver -d /usr/bin

ARG JAR_FILE=/core-api/build/libs/linker.jar

COPY ${JAR_FILE} /linker.jar

ENTRYPOINT ["java","-jar", "/linker.jar"]

# "-Dspring.profiles.active=prod",