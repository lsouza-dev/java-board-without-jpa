package com.dev.lsouza.board_application.persistence.dao;

import com.dev.lsouza.board_application.dto.BoardColumnDTO;
import com.dev.lsouza.board_application.persistence.entity.BoardColumnEntity;
import com.dev.lsouza.board_application.persistence.entity.BoardColumnKindEnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BoardColumnDAO {
    private final Connection connection;

    public BoardColumnEntity insert(final BoardColumnEntity entity) throws SQLException {
        var sql = "INSERT INTO BOARDS_COLUMNS (name,`order`,kind,board_id) VALUES (?,?,?,?)";
        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            var i = 1;
            statement.setString(i++, entity.getName());
            statement.setInt(i++, entity.getOrder());
            statement.setString(i++, entity.getKind().name());
            statement.setLong(i, entity.getBoard().getId());
            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                entity.setId(generatedKeys.getLong(1));
        }
        return entity;
    }

    public List<BoardColumnEntity> findByBoardId(Long id) throws SQLException {
        var sql = "SELECT id,name,`order`,kind FROM BOARDS_COLUMNS WHERE board_id = ? ORDER BY `order`";
        var entities = new ArrayList<BoardColumnEntity>();

        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var entity = new BoardColumnEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setOrder(resultSet.getInt("order"));
                entity.setKind(BoardColumnKindEnum.findByName(resultSet.getString("kind")));
                entities.add(entity);
            }
        }
        return entities;
    }

    public List<BoardColumnDTO> findByBoardIdWithDetails(Long id) throws SQLException {
        var sql = """
                SELECT
                    bc.id,bc.name,,bc.kind,
                    COUNT(SELECT c.id
                    FROM CARDS c
                    WHERE c.board_column_id = bc.id) cards_amount
                FROM BOARDS_COLUMNS bc
                WHERE board_id = ?
                ORDER BY `bc.order`
                """;
        var dtos = new ArrayList<BoardColumnDTO>();

        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var entity = new BoardColumnDTO(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        BoardColumnKindEnum.findByName(resultSet.getString("kind")),
                        resultSet.getInt("cards_amount")
                );

                dtos.add(entity);
            }
        }
        return dtos;
    }
}