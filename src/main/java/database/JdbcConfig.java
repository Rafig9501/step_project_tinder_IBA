package database;

import java.sql.*;
import java.util.Optional;

import static utilities.constants.JdbcConstants.*;

public class JdbcConfig {

    public static Optional<Connection> getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return Optional.of(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}