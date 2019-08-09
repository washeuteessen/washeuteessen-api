FROM openjdk:8-jdk-alpine
VOLUME /index
COPY target/washeuteessen-api-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ARG JVM_ARGS
CMD exec java $JVM_ARGS -jar app.jar
