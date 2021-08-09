package com.adnapstudios.adnapminigames.models.utils;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;

public class DatabaseManager {
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    public void readConfig() {
        FileConfiguration config = AdnapMiniGames.getPlugin(AdnapMiniGames.class).getConfig();
        host = config.getString("database.host");
        port = config.getString("database.port");
        database = config.getString("database.database");
        username = config.getString("database.username");
        password = config.getString("database.password");
    }

    public void connect() throws SQLException {
        if (!isConnected()) {
            String connectionString = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
            connection = DriverManager.getConnection(connectionString, username, password);
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void createTables() {
        try {
            Statement statement;

            String arenas = "CREATE TABLE IF NOT EXISTS amg_arenas (" +
                    "id INT(100) NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255) CHARACTER SET ascii COLLATE ascii_bin NOT NULL," +
                    "world VARCHAR(255) CHARACTER SET ascii COLLATE ascii_bin NOT NULL," +
                    "teams_of INT(2) NOT NULL," +
                    "pos1_x FLOAT NOT NULL," +
                    "pos1_y FLOAT NOT NULL," +
                    "pos1_z FLOAT NOT NULL," +
                    "pos2_x FLOAT NOT NULL," +
                    "pos2_y FLOAT NOT NULL," +
                    "pos2_z FLOAT NOT NULL," +
                    "lobby_x FLOAT NOT NULL," +
                    "lobby_z FLOAT NOT NULL," +
                    "lobby_y FLOAT NOT NULL," +
                    "spec_x FLOAT NOT NULL," +
                    "spec_y FLOAT NOT NULL," +
                    "spec_z FLOAT NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(arenas);

            String games = "CREATE TABLE IF NOT EXISTS amg_games (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "arena_id INT NOT NULL," +
                    "game_type INT NOT NULL," +
                    "game_status INT NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(games);

            String players = "CREATE TABLE IF NOT EXISTS amg_players (" +
                    "uuid VARCHAR(255) NOT NULL," +
                    "username VARCHAR(255) NOT NULL," +
                    "game_id INT NOT NULL," +
                    "iron INT NOT NULL," +
                    "gold INT NOT NULL," +
                    "diamond INT NOT NULL," +
                    "emerald INT NOT NULL," +
                    "PRIMARY KEY (uuid)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(players);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
