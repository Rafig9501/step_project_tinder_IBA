package dao;

import entity.User;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.commands.UserCommand.*;

@Log4j2
public class UserDao implements DAO<User> {

    private final Connection connection;

    @SneakyThrows
    public UserDao(Optional<Connection> connection) {
        this.connection = connection.orElseThrow(RuntimeException::new);
        this.connection.setAutoCommit(false);
    }

    @Override
    public int create(User user) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhotoUrl());
            statement.setTimestamp(4, Timestamp.valueOf(ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.execute();
            if (statement.getGeneratedKeys().next()) {
                connection.setAutoCommit(true);
                return statement.getGeneratedKeys().getInt(1);
            } else return -1;

        } catch (Exception e) {
            log.warn("Exception in UserDao.create(User user)" + e.getMessage());
            return -1;
        }
    }

    public void updateLastLogin(String id) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_LAST_LOGIN.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setTimestamp(1, Timestamp.valueOf(ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            statement.setInt(2, Integer.parseInt(id));
            statement.execute();
            if (statement.getGeneratedKeys().next()) {
                connection.setAutoCommit(true);
                statement.getGeneratedKeys().getInt(1);
            }

        } catch (Exception e) {
            log.warn("Exception in UserDao.updateLastLogin(String id)" + e.getMessage());
        }
    }

    @Override
    public Optional<User> get(String id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID.QUERY)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            return !set.next() ? Optional.empty() :
                    Optional.of(new User(
                            set.getString("id"),
                            set.getString("email"),
                            null,
                            set.getString("name"),
                            set.getString("surname"),
                            set.getString("photo_url"),
                            set.getTimestamp("last_login").toLocalDateTime().atZone(ZoneOffset.UTC)));
        } catch (Exception e) {
            log.warn("Exception in UserDao.get(String id)" + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<User> getByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL.QUERY)) {
            statement.setString(1, email);
            ResultSet set = statement.executeQuery();
            return !set.next() ? Optional.empty() :
                    Optional.of(new User(
                            set.getString("id"),
                            set.getString("email"),
                            set.getString("password"),
                            set.getString("name"),
                            set.getString("surname"),
                            set.getString("photo_url"),
                            set.getTimestamp("last_login").toLocalDateTime().atZone(ZoneOffset.UTC)));
        } catch (Exception e) {
            log.warn("Exception in UserDao.getByEmail(String email)" + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int delete(User user) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, Integer.parseInt(user.getId()));
            statement.execute();
            if (statement.getGeneratedKeys().next()) {
                connection.setAutoCommit(true);
                return statement.getGeneratedKeys().getInt(1);
            } else return -1;

        } catch (Exception e) {
            log.warn("Exception in UserDao.delete(User user)" + e.getMessage());
            return -1;
        }
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                userList.add(new User(
                        set.getString("id"),
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("name"),
                        set.getString("surname"),
                        set.getString("photo_url"),
                        set.getTimestamp("last_login").toLocalDateTime().atZone(ZoneOffset.UTC)));
            }
            return userList;

        } catch (Exception e) {
            log.warn("Exception in UserDao.getAll()" + e.getMessage());
            return userList;
        }
    }

    public Optional<User> get(String email, String password) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD.QUERY)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            return !set.next() ? Optional.empty() :
                    Optional.of(new User(
                            set.getString("id"),
                            set.getString("email"),
                            set.getString("password"),
                            set.getString("name"),
                            set.getString("surname"),
                            set.getString("photo_url"),
                            set.getTimestamp("last_login").toLocalDateTime().atZone(ZoneOffset.UTC)));
        } catch (Exception e) {
            log.warn("Exception in UserDao.get(String email, String password)" + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<User> getRandomUser(String id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_RANDOM_USER.QUERY)) {
            statement.setInt(1, Integer.parseInt(id));
            statement.setInt(2, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            return !set.next() ? Optional.empty() :
                    Optional.of(new User(
                            set.getString("id"),
                            set.getString("email"),
                            set.getString("password"),
                            set.getString("name"),
                            set.getString("surname"),
                            set.getString("photo_url"),
                            set.getTimestamp("last_login").toLocalDateTime().atZone(ZoneOffset.UTC)));
        } catch (Exception e) {
            log.warn("Exception in UserDao.getRandomUser(String id)" + e.getMessage());
            return Optional.empty();
        }
    }

    public List<User> getLikedUsers(String id) {
        List<User> likedUsers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_LIKED.QUERY)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                likedUsers.add(new User(
                        set.getString("to_user_id"),
                        set.getString("email"),
                        null,
                        set.getString("name"),
                        set.getString("surname"),
                        set.getString("photo_url"),
                        set.getTimestamp("last_login").toLocalDateTime().atZone(ZoneOffset.UTC)));
            }
            return likedUsers;
        } catch (Exception e) {
            log.warn("Exception in UserDao.getRandomUser(String id)" + e.getMessage());
            return likedUsers;
        }
    }
}