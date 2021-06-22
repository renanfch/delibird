package com.renanfch.delibird.adapter.entrypoint.schedule;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

public class RabbitMqContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final static GenericContainer rabbit;

    static {
        rabbit = new GenericContainer("rabbitmq:3-management")
                .withExposedPorts(5672, 15672);
        rabbit.start();
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        final var values = TestPropertyValues.of(
                "spring.rabbitmq.host=" + rabbit.getContainerIpAddress(),
                "spring.rabbitmq.port=" + rabbit.getMappedPort(5672)
        );
        values.applyTo(configurableApplicationContext);
    }

}
