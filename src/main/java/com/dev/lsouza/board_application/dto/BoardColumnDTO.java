package com.dev.lsouza.board_application.dto;

import com.dev.lsouza.board_application.persistence.entity.BoardColumnKindEnum;

public record BoardColumnDTO(
        Long id,
        String name,
        BoardColumnKindEnum kind,
        int cardsAmount) {
}
