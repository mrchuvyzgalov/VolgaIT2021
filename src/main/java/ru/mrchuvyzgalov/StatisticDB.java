package ru.mrchuvyzgalov;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Map;

public class StatisticDB {
    private String URL;
    private String USERNAME;
    private String PASSWORD;

    public StatisticDB(@NotNull String URL, @NotNull String USERNAME, @NotNull String PASSWORD) {
        this.URL = "jdbc:mysql:" + URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public void saveStatistic(Statistic statistic) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            try(Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE statistics (" +
                        "id INTEGER PRIMARY KEY," +
                        "word VARCHAR(255)," +
                        "word_count INTEGER)");
            }

            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO statistics VALUE(?, ?, ?)")) {
                int id = 1;
                for (Map.Entry<String, Integer> entity : statistic) {
                    statement.setInt(1, id++);
                    statement.setString(2, entity.getKey());
                    statement.setInt(3, entity.getValue());
                    statement.execute();
                }
            }
        }
    }
}
