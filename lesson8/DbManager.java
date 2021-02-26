package lesson8;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbManager {
    private static Connection conn;
    private static DbManager instance = null;

    //подключение базы данных
    private Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:lesson8.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //создаёт синглтон
    public static synchronized DbManager getInstance() {
        if (instance == null)
            instance = new DbManager();
        return instance;
    }

    //создаёт таблицу
    public void createTableDB() {
        String table = "CREATE TABLE IF NOT EXISTS weather (\n"
                + "'id' INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "'city' TEXT NOT NULL,\n"
                + "'local_date' TEXT NOT NULL,\n"
                + "'weather_text' TEXT NOT NULL,\n"
                + "'temperature' REAL NOT NULL\n"
                + ");";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(table);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //вставляет данные в ДБ
    public void insertDB(WeatherResponse response) {
        String sql = "INSERT INTO weather (city, local_date, weather_text, temperature) VALUES (?,?,?,?);";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, response.getCity());
            pstmt.setObject(2, response.getLocalDate());
            pstmt.setObject(3, response.getWeatherText());
            pstmt.setObject(4, response.getTemperature());
            pstmt.addBatch();
            pstmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //чтение данных из таблицы и запись в коллекцию
    public List<WeatherResponse> getAllTableDB() {
        String sql = "SELECT city, local_date, weather_text, temperature FROM weather;";
        try (Connection conn = this.connect();
             Statement statement = conn.createStatement()) {
            List<WeatherResponse> responses = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                responses.add(new WeatherResponse(resultSet.getString("city"),
                        resultSet.getString("local_date"),
                        resultSet.getString("weather_text"),
                        resultSet.getDouble("temperature")));
            }
            return responses;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    //чтение из таблицы
    public void getAll() {
        String sql = "SELECT city, local_date, weather_text, temperature FROM weather;";
        try (Connection conn = this.connect();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("city") + " ");
                System.out.print(resultSet.getString("local_date") + " ");
                System.out.print(resultSet.getString("weather_text") + " ");
                System.out.print(resultSet.getDouble("temperature") + " ");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //удаление из таблицы по id
    public void deleteIdDb(int id) {
        String sql = "DELETE FROM weather WHERE id = ?;";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
