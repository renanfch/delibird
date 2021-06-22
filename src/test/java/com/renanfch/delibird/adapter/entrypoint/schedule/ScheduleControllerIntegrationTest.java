package com.renanfch.delibird.adapter.entrypoint.schedule;

import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = RabbitMqContextInitializer.class)
class ScheduleControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "";
    }

    @Nested
    class register {

        @Test
        @DisplayName("Should register schedule with success")
        @SneakyThrows
        void shouldRegisterScheduleWithSuccess() {
            RestAssured
                    .given()
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(new JSONObject()
                            .put("sendTime", "2022-06-21T12:35:45.345")
                            .put("recipient", "email@email.com")
                            .put("messageService", "EMAIL")
                            .put("message", "message")
                            .toString())
                    .when()
                    .post("/schedule")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("sendTime", equalTo("2022-06-21T12:35"))
                    .body("recipient", equalTo("email@email.com"))
                    .body("messageService", equalTo("EMAIL"))
                    .body("message", equalTo("message"))
                    .body("scheduleStatusEnum", equalTo("SCHEDULED"));
        }

        @Test
        @DisplayName("Should return 400 when argument email invalid")
        @SneakyThrows
        void shouldReturn400WhenArgumentEmailInvalid() {
            RestAssured
                    .given()
                    .contentType(APPLICATION_JSON_VALUE)
                    .body(new JSONObject()
                            .put("sendTime", "2022-06-21T12:35:45.345")
                            .put("recipient", "email")
                            .put("messageService", "EMAIL")
                            .put("message", "message")
                            .toString())
                    .when()
                    .post("/schedule")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    class searchById {

        @Test
        @DisplayName("Should return schedule message with success")
        @SneakyThrows
        void shouldReturnScheduleMessageWithSuccess() {
            RestAssured
                    .given()
                    .pathParam("id", 1)
                    .when()
                    .get("/schedule/{id}")
                    .then()
                    .statusCode(HttpStatus.OK.value())
                    .body("id", equalTo(1))
                    .body("recipient", equalTo("email@email.com"))
                    .body("messageService", equalTo("EMAIL"))
                    .body("message", equalTo("message"))
                    .body("scheduleStatusEnum", equalTo("SCHEDULED"));
        }

        @Test
        @DisplayName("Should return 404 when not found schedule")
        @SneakyThrows
        void shouldReturn404WhenNotFoundSchedule() {
            RestAssured
                    .given()
                    .pathParam("id", 999)
                    .when()
                    .get("/schedule/{id}")
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
        }
    }

    @Nested
    class delete {

        @Test
        @DisplayName("Should delete schedule message with success")
        @SneakyThrows
        void shouldDeleteScheduleMessageWithSuccess() {
            RestAssured
                    .given()
                    .pathParam("id", 2)
                    .when()
                    .delete("/schedule/{id}")
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
        }

        @Test
        @DisplayName("Should return 404 when not found schedule")
        @SneakyThrows
        void shouldReturn404WhenNotFoundSchedule() {
            RestAssured
                    .given()
                    .pathParam("id", 999)
                    .when()
                    .delete("/schedule/{id}")
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());
        }

        @Test
        @DisplayName("Should return 404 when schedule is sent")
        @SneakyThrows
        void shouldReturn404WhenScheduleSent() {
            RestAssured
                    .given()
                    .pathParam("id", 3)
                    .when()
                    .delete("/schedule/{id}")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

    }

}
