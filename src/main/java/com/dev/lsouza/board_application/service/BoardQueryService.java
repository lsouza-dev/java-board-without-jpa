package com.dev.lsouza.board_application.service;

import com.dev.lsouza.board_application.persistence.dao.BoardColumnDAO;
import com.dev.lsouza.board_application.persistence.dao.BoardDAO;
import com.dev.lsouza.board_application.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardQueryService {
    private final Connection connection;

    public Optional<BoardEntity> findById(final Long id) throws SQLException{
        var dao = new BoardDAO(connection);
        var boardColumnDao = new BoardColumnDAO(connection);
        var optional = dao.findById(id);
        if(optional.isPresent()){
            var entity = optional.get();
            entity.setBoardColumns(boardColumnDao.findByBoardId(id));
            return  Optional.of(entity);
        }
        return  Optional.empty();
    }
}
