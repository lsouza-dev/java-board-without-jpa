--liquibase formatted sql
--changeset lsouza:202504211652
--comment: card table create

 CREATE TABLE IF NOT EXISTS CARDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    board_column_id BIGINT NOT NULL,
    CONSTRAINT fk_boards_columns_cards
    FOREIGN KEY (board_column_id)
    REFERENCES BOARDS_COLUMNS(id)
    ON DELETE CASCADE
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

--rollback: DROP TABLE CARDS;