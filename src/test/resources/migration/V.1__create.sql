CREATE TABLE schedule_message
(
    id                  int NOT NULL AUTO_INCREMENT,
    sendtime            timestamp,
    recipient           varchar(100),
    message             varchar(5000),
    message_service     varchar(50),
    status              varchar(50)
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
