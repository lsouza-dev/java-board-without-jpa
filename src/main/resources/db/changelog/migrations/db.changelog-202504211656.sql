--liquibase formatted sql
--changeset lsouza:202504211656
--comment: blocks table create

 CREATE TABLE IF NOT EXISTS BLOCKS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    block_reason VARCHAR(255) NOT NULL,
    unblocked_at TIMESTAMP NULL,
    unblock_reason VARCHAR(255) NOT NULL,
    card_id BIGINT NOT NULL,
    CONSTRAINT fk_cards_blocks
    FOREIGN KEY (card_id)
    REFERENCES CARDS(id)
    ON DELETE CASCADE
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

--rollback: DROP TABLE BLOCKS;