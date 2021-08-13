package com.adnapstudios.adnapminigames.models.utils;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Vector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

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
            String connectionString = String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true", host, port, database);
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
                    "lobby_x FLOAT," +
                    "lobby_z FLOAT," +
                    "lobby_y FLOAT," +
                    "lobby_dir_x FLOAT," +
                    "lobby_dir_z FLOAT," +
                    "lobby_dir_y FLOAT," +
                    "spec_x FLOAT," +
                    "spec_y FLOAT," +
                    "spec_z FLOAT," +
                    "spec_dir_x FLOAT," +
                    "spec_dir_y FLOAT," +
                    "spec_dir_z FLOAT," +
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

            String teams = "CREATE TABLE IF NOT EXIST amg_teams (" +
                    "id INT NOT NULL," +
                    "game_id INT NOT NULL," +
                    "team_name VARCHAR(100) NOT NULL," +
                    "team_color VARCHAR(30) NOT NULL," +
                    "team_status VARCHAR(30) NOT NULL," +
                    "PRIMARY KEY (`id`)" +
                    ");";

            statement = connection.createStatement();
            statement.executeUpdate(teams);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Arena> getAllArenas() throws SQLException {
        String query = "SELECT * FROM `amg_arenas`";

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);

        ArrayList<Arena> arenas = new ArrayList<>();

        while (results.next()) {
            Arena arena = new Arena();
            arena.setId(results.getInt("id"));
            arena.setName(results.getString("name"));
            arena.setWorld(AdnapMiniGames.getPlugin(AdnapMiniGames.class)
                    .getServer().getWorld(results.getString("world")));

            double pos1X = results.getDouble("pos1_x");
            double pos1Y = results.getDouble("pos1_y");
            double pos1Z = results.getDouble("pos1_z");

            double pos2X = results.getDouble("pos2_x");
            double pos2Y = results.getDouble("pos2_y");
            double pos2Z = results.getDouble("pos2_z");

            double lobbyX = results.getDouble("lobby_x");
            double lobbyY = results.getDouble("lobby_y");
            double lobbyZ = results.getDouble("lobby_z");

            double lobbyDirX = results.getDouble("lobby_dir_x");
            double lobbyDirY = results.getDouble("lobby_dir_y");
            double lobbyDirZ = results.getDouble("lobby_dir_z");

            double specX = results.getDouble("spec_x");
            double specY = results.getDouble("spec_y");
            double specZ = results.getDouble("spec_z");

            double specDirX = results.getDouble("spec_dir_x");
            double specDirY = results.getDouble("spec_dir_y");
            double specDirZ = results.getDouble("spec_dir_z");

            Location pos1 = new Location(arena.getWorld(), pos1X, pos1Y, pos1Z);
            Location pos2 = new Location(arena.getWorld(), pos2X, pos2Y, pos2Z);

            Location lobby = new Location(arena.getWorld(), lobbyX, lobbyY, lobbyZ);
            Location spectator = new Location(arena.getWorld(), specX, specY, specZ);

            Vector lobbyDir = new Vector(lobbyDirX, lobbyDirY, lobbyDirZ);
            Vector specDir = new Vector(specDirX, specDirY, specDirZ);

            lobby.setDirection(lobbyDir);
            spectator.setDirection(specDir);

            arena.setPos1(pos1);
            arena.setPos2(pos2);
            arena.setLobby(lobby);
            arena.setSpectator(spectator);

            arenas.add(arena);
        }

        return arenas;
    }

    public Arena getArenaByName(String arenaName) throws SQLException {
        ArrayList<Arena> arenas = getAllArenas();

        if (arenas == null || arenas.size() == 0) return null;

        for (Arena arena : arenas) {
            if (arena.getName().equalsIgnoreCase(arenaName)) {
                return arena;
            }
        }

        return null;
    }

    public void createArena(Arena arena) throws SQLException {
        String query = String.format("INSERT INTO `amg_arenas` " +
                "(`id`, `name`, `world`, `pos1_x`, `pos1_y`, `pos1_z`, `pos2_x`, `pos2_y`, `pos2_z`, " +
                "`lobby_x`, `lobby_z`, `lobby_y`, `lobby_dir_x`, `lobby_dir_z`, `lobby_dir_y`, " +
                "`spec_x`, `spec_y`, `spec_z`, `spec_dir_x`, `spec_dir_y`, `spec_dir_z`) " +
                "VALUES (NULL, '%s', '%s', '%f', '%f', '%f', '%f', '%f', '%f', " +
                "NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);",
                arena.getName(),
                arena.getWorld().getName(),
                arena.getPos1().getX(),
                arena.getPos1().getY(),
                arena.getPos1().getZ(),
                arena.getPos2().getX(),
                arena.getPos2().getY(),
                arena.getPos2().getZ()
                );

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void setLobby(String arenaName, Location lobby) throws SQLException {
        Arena arena = getArenaByName(arenaName);

        String query = String.format("UPDATE `amg_arenas` " +
                "SET `lobby_x` = '%f', `lobby_z` = '%f', `lobby_y` = '%f', " +
                "`lobby_dir_x` = '%f', `lobby_dir_z` = '%f', `lobby_dir_y` = '%f' " +
                "WHERE `amg_arenas`.`id` = %d;",
                lobby.getX(),
                lobby.getY(),
                lobby.getZ(),
                lobby.getDirection().getX(),
                lobby.getDirection().getY(),
                lobby.getDirection().getZ(),
                arena.getId()
                );

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void setSpectator(String arenaName, Location spectator) throws SQLException {
        Arena arena = getArenaByName(arenaName);

        String query = String.format("UPDATE `amg_arenas` " +
                        "SET `spec_x` = '%f', `spec_z` = '%f', `spec_y` = '%f', " +
                        "`spec_dir_x` = '%f', `spec_dir_z` = '%f', `spec_dir_y` = '%f' " +
                        "WHERE `amg_arenas`.`id` = %d;",
                spectator.getX(),
                spectator.getY(),
                spectator.getZ(),
                spectator.getDirection().getX(),
                spectator.getDirection().getY(),
                spectator.getDirection().getZ(),
                arena.getId()
        );

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void setSpawn(Location spawn) throws SQLException {
        if (getSpawn() != null) {
            String query = "DELETE FROM `amg_config`";

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }

        String query = String.format("INSERT INTO `amg_config` " +
                "(`world`, `spawn_x`, `spawn_y`, `spawn_z`, `spawn_dir_x`, `spawn_dir_y`, `spawn_dir_z`) " +
                "VALUES ('%s', '%f', '%f', '%f', '%f', '%f', '%f');",
                Objects.requireNonNull(spawn.getWorld()).getName(),
                spawn.getX(),
                spawn.getY(),
                spawn.getZ(),
                spawn.getDirection().getX(),
                spawn.getDirection().getY(),
                spawn.getDirection().getZ()
                );

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public Location getSpawn() throws SQLException {
        String query = "SELECT * FROM `amg_config`";

        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            World world = AdnapMiniGames.getPlugin(AdnapMiniGames.class)
                    .getServer().getWorld(results.getString("world"));

            double spawnX = results.getDouble("spawn_x");
            double spawnY = results.getDouble("spawn_y");
            double spawnZ = results.getDouble("spawn_z");

            double spawnDirX = results.getDouble("spawn_dir_x");
            double spawnDirY = results.getDouble("spawn_dir_y");
            double spawnDirZ = results.getDouble("spawn_dir_z");

            Location spawn = new Location(world, spawnX, spawnY, spawnZ);
            Vector direction = new Vector(spawnDirX, spawnDirY, spawnDirZ);

            spawn.setDirection(direction);

            return spawn;
        }

        return null;
    }
}
