package dao;

import entity.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.commands.UserCommand.*;

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
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(CREATE.QUERY, Statement.RETURN_GENERATED_KEYS);
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
        PreparedStatement statement = connection.prepareStatement(GET_ID.QUERY);
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

    @SneakyThrows
    public Optional<User> getByEmail(String email) {
        PreparedStatement statement = connection.prepareStatement(GET_EMAIL.QUERY);
        statement.setString(1, email);
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

    @SneakyThrows
    @Override
    public int delete(User user) {
        PreparedStatement statement = connection.prepareStatement(DELETE.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, Integer.parseInt(user.getId()));
        statement.execute();
        if (statement.getGeneratedKeys().next()) {
            connection.setAutoCommit(true);
            return statement.getGeneratedKeys().getInt(1);
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            userList.add(new User(
                    set.getString("id"),
                    set.getString("email"),
                    set.getString("name"),
                    set.getString("surname"),
                    set.getString("photo_url"),
                    ZonedDateTime.ofInstant(set.getTimestamp("last_login").toInstant(), ZoneId.systemDefault())));
        }
        return userList;
    }

    @SneakyThrows
    public List<String> getAllLiked(String id) {
        List<String> likedIdList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_LIKED.QUERY);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            likedIdList.add(set.getString("to_user_id"));
        }
        return likedIdList;
    }
}