--liquibase formatted sql
--changeset lsouza:202504211647
--comment: boards_column table create

CREATE TABLE IF NOT EXISTS BOARDS_COLUMNS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `order` INT NOT NULL,
    kind VARCHAR(7) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT fk_boards_boards_columns
    FOREIGN KEY (board_id)
    REFERENCES BOARDS(id)
    ON DELETE CASCADE,
    CONSTRAINT uk_id_order UNIQUE KEY unique_board_id_order (board_id, `order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--rollback: DROP TABLE BOARDS_COLUMNS;