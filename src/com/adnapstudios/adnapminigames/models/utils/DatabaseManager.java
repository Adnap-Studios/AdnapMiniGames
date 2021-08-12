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
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(100) NOT NULL," +
                    "world VARCHAR(100) NOT NULL," +
                    "pos1_x FLOAT NOT NULL," +
                    "pos1_y FLOAT NOT NULL," +
                    "pos1_z FLOAT NOT NULL," +
                    "pos2_x FLOAT NOT NULL," +
                    "pos2_y FLOAT NOT NULL," +
                    "pos2_z FLOAT NOT NULL," +
                    "lobby_x FLOAT NOT NULL," +
                    "lobby_z FLOAT NOT NULL," +
                    "lobby_y FLOAT NOT NULL," +
                    "lobby_dir_x FLOAT NOT NULL," +
                    "lobby_dir_z FLOAT NOT NULL," +
                    "lobby_dir_y FLOAT NOT NULL," +
                    "spec_x FLOAT NOT NULL," +
                    "spec_y FLOAT NOT NULL," +
                    "spec_z FLOAT NOT NULL," +
                    "spec_dir_x FLOAT NOT NULL," +
                    "spec_dir_y FLOAT NOT NULL," +
                    "spec_dir_z FLOAT NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(arenas);

            String games = "CREATE TABLE IF NOT EXISTS amg_games (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "arena_id INT NOT NULL," +
                    "game_type VARCHAR(100) NOT NULL," +
                    "game_status VARCHAR(100) NOT NULL," +
                    "PRIMARY KEY (id)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(games);

            String players = "CREATE TABLE IF NOT EXISTS amg_players (" +
                    "uuid VARCHAR(100) NOT NULL," +
                    "username VARCHAR(100) NOT NULL," +
                    "game_id INT NOT NULL," +
                    "iron INT NOT NULL," +
                    "gold INT NOT NULL," +
                    "diamond INT NOT NULL," +
                    "emerald INT NOT NULL," +
                    "games INT NOT NULL," +
                    "wins INT NOT NULL," +
                    "loses INT NOT NULL," +
                    "kills INT NOT NULL," +
                    "deaths INT NOT NULL," +
                    "assists INT NOT NULL," +
                    "points INT NOT NULL," +
                    "PRIMARY KEY (uuid)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(players);

            String config = "CREATE TABLE IF NOT EXISTS NOT amg_config (" +
                    "world VARCHAR(100) NOT NULL," +
                    "spawn_x FLOAT NOT NULL," +
                    "spawn_y FLOAT NOT NULL," +
                    "spawn_z FLOAT NOT NULL," +
                    "spawn_dir_x FLOAT NOT NULL," +
                    "spawn_dir_y FLOAT NOT NULL," +
                    "spawn_dir_z FLOAT NOT NULL," +
                    "PRIMARY KEY (`world`,`spawn_x`,`spawn_y`,`spawn_z`,`spawn_dir_x`,`spawn_dir_y`,`spawn_dir_z`)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(config);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
