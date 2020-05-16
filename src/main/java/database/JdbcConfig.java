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

//   public static int connection(String query) {
//
//        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
//            connection.setAutoCommit(false);
//            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            statement.executeQuery();
//            ResultSet set = statement.getGeneratedKeys();
//            if (set.next()) {
//                int id = set.getInt(1);
//                connection.setAutoCommit(true);
//                return id;
//            }
//            return -1;
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//            return -1;
//        }
//    }