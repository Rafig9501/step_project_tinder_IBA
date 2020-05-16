package dao;

import database.commands.UserCommand;
import entity.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class UserDao implements DAO<User> {

    private final Connection connection;

    @SneakyThrows
    public UserDao(Optional<Connection> connection) {
        this.connection = connection.orElseThrow(RuntimeException::new);
        this.connection.setAutoCommit(false);
    }

    @SneakyThrows
    @Override
    public int create(User user) {
        PreparedStatement statement = connection.prepareStatement(UserCommand.CREATE.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPhotoUrl());
        statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        statement.execute();
        if (statement.getGeneratedKeys().next()) {
            connection.setAutoCommit(true);
            return statement.getGeneratedKeys().getInt(1);
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public Optional<User> get(String id) {
        PreparedStatement statement = connection.prepareStatement(UserCommand.GET.QUERY);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            return Optional.of(new User(
                    set.getString("id"),
                    set.getString("email"),
                    set.getString("name"),
                    set.getString("surname"),
                    set.getString("photo_url"),
                    ZonedDateTime.ofInstant(set.getTimestamp("last_login").toInstant(), ZoneId.of("UTC"))));
        }
        return Optional.empty();
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User getAll() {
        return null;
    }
}
