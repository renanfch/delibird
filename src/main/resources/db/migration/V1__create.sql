CREATE TABLE schedule_message
(
    id                  INT NOT NULL AUTO_INCREMENT,
    send_time            timestamp,
    recipient           varchar(100),
    message             varchar(5000),
    message_service     varchar(50),
    status              varchar(50)
);

INSERT INTO schedule_message(id, send_time, recipient, message, message_service, status)
values(1, CURRENT_TIMESTAMP,'email@email.com', 'message', 'EMAIL', 'SCHEDULED'),
      (2, CURRENT_TIMESTAMP,'email2@email.com', 'message2', 'EMAIL', 'SCHEDULED'),
      (3, CURRENT_TIMESTAMP,'email3@email.com', 'message3', 'EMAIL', 'SENT');
