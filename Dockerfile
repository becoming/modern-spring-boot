# syntax=docker/dockerfile:experimental
FROM maven:3.6.1-jdk-11 AS build

WORKDIR /tmp

COPY pom.xml /tmp
RUN mvn  dependency:resolve-plugins  dependency:go-offline

COPY . /tmp/
RUN mvn package

FROM openjdk:11
COPY --from=build /tmp/target/app.jar app.jar

ENV JAVA_OPTS="-Xms512m -Xmx1024m"
ENTRYPOINT exec java -server -XshowSettings:vm -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:ParallelGCThreads=20 -XX:ConcGCThreads=5 -XX:InitiatingHeapOccupancyPercent=70 $JAVA_OPTS -jar app.jar