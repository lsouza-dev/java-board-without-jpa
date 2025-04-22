--liquibase formatted sql
--changeset lsouza:202504211540
--comment: board table create

 CREATE TABLE IF NOT EXISTS BOARDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

--rollback: DROP TABLE BOARDS