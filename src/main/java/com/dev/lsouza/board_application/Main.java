package com.dev.lsouza.board_application;

import com.dev.lsouza.board_application.persistence.config.ConnectionConfig;
import com.dev.lsouza.board_application.persistence.migration.MigrationStrategy;
import com.dev.lsouza.board_application.ui.MainMenu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException{
		SpringApplication.run(Main.class, args);
		try(var connection = ConnectionConfig.getConnection()){
            new MigrationStrategy(connection);
            MigrationStrategy.executeMigration();
		}
		new MainMenu().execute();
    }

}
