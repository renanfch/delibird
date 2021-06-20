FROM openjdk:11-jdk-slim
RUN mkdir data
VOLUME data
COPY build/delibird.jar delibird.jar

ENV DATABASE_URL jdbc:h2:nio:./data/delibird_db;MODE=MySQL
ENV DATABASE_USER root
ENV DATABASE_PASSWORD 123456
ENV RABBITMQ_HOST rabbitmq
ENV RABBITMQ_USER rabbitmqUser
ENV RABBITMQ_PASSWORD rabbitmqPass
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/delibird.jar"]
