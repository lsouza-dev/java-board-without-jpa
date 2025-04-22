package com.dev.lsouza.board_application.ui;

import com.dev.lsouza.board_application.persistence.config.ConnectionConfig;
import com.dev.lsouza.board_application.persistence.entity.BoardEntity;
import com.dev.lsouza.board_application.service.BoardQueryService;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

@AllArgsConstructor
public class BoardMenu {
    private final BoardEntity entity;
    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public void execute() {
        try {
            System.out.printf("Board: %s!\nSelecione a operação desenada:", entity.getName());
            var option = -1;
            while (true) {
                System.out.println("\n1 - Criar um Card");
                System.out.println("2 - Mover um Card");
                System.out.println("3 - Bloquear um Card");
                System.out.println("4 - Desbloquear um Card");
                System.out.println("5 - Cancelar um Card");
                System.out.println("6 - Visualizar Colunas");
                System.out.println("7 - Visualizar Colunas com Cards");
                System.out.println("8 - Visualizar Cards");
                System.out.println("9  - Voltar para o menu anterior");
                System.out.println("10 - Sair");
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> createCard();
                    case 2 -> moveCardToNextColumn();
                    case 3 -> blockCard();
                    case 4 -> unblockCard();
                    case 5 -> cancelCard();
                    case 6 -> showBoard();
                    case 7 -> showColumn();
                    case 8 -> showCard();
                    case 9 -> System.out.println("Voltando para o menu anterior...");
                    case 10 -> System.exit(0);
                    default -> System.out.println("Opção inválida, informe uma opção do menu.");
                }

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void createCard() {

    }

    private void moveCardToNextColumn() {

    }

    private void blockCard() {

    }

    private void unblockCard() {

    }

    private void cancelCard() {
    }

    private void showBoard() throws SQLException {
        try (var connection = ConnectionConfig.getConnection()) {
            var optional = new BoardQueryService(connection).showBoardDetails(entity.getId());
            optional.ifPresent(b -> {
                System.out.printf("Board [%s,%s]", b.id(), b.name());
                b.columns().forEach(c ->
                        System.out.printf("Column [%s] tipo: [%s] tem %s cards/n",
                                c.name(), c.kind(), c.cardsAmount()));
            });

        }
    }

    private void showColumn() {

    }

    private void showCard() {
    }
}
