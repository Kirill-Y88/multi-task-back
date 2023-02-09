FROM openjdk:11-jdk
MAINTAINER Kirill-Y88
COPY target/multi-task-back-0.0.1-SNAPSHOT.jar multi-task-back-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/multi-task-back-0.0.1-SNAPSHOT.jar"]