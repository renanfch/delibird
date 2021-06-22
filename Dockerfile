FROM openjdk:11-jdk-slim
RUN mkdir data
VOLUME data
COPY target/delibird*.jar delibird.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/delibird.jar"]
