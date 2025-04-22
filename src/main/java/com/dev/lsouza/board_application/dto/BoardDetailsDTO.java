package com.dev.lsouza.board_application.dto;

import java.util.List;

public record BoardDetailsDTO(
        Long id,
        String name,
        List<BoardColumnDTO> columns
){
}
