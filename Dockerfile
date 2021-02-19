FROM maven:3.6.3-jdk-11 AS maven_build

COPY . /tmp/
WORKDIR /tmp/

RUN mvn clean package -DskipTests

FROM openjdk:11.0.7-jre-slim-buster

ENV APP_NAME proposal
MAINTAINER diogo.domingues@zup.com.br

COPY --from=maven_build tmp/target/${APP_NAME}.jar /data/${APP_NAME}.jar
CMD java -jar -Dspring.profiles.active=prod /data/${APP_NAME}.jar