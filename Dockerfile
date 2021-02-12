
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=build/libs/PersonProfile-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} PersonProfile.jar

ENTRYPOINT ["java","-jar","PersonProfile.jar"]
