package dao;

import entity.Message;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.commands.MessageCommand.*;

public class MessagesDao implements DAO<Message> {

    private final Connection connection;

    @SneakyThrows
    public MessagesDao(Optional<Connection> connection) {
        this.connection = connection.orElseThrow(RuntimeException::new);
        this.connection.setAutoCommit(false);
    }

    @SneakyThrows
    @Override
    public int create(Message message) {
        connection.setAutoCommit(false);
        int contentId = createContent(message.getContent());
        if (contentId != -1) {
            return relateContent(message.getFromId(), message.getToId(), String.valueOf(contentId));
        }
        return -1;
    }

    @SneakyThrows
    private int createContent(String content) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(CREATE_CONTENT.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, content);
        statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        statement.execute();
        if (statement.getGeneratedKeys().next())
            return statement.getGeneratedKeys().getInt(1);
        return -1;
    }

    @SneakyThrows
    private int relateContent(String fromId, String toId, String contentId) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(RELATE_CONTENT.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, Integer.parseInt(fromId));
        statement.setInt(2, Integer.parseInt(toId));
        statement.setInt(3, Integer.parseInt(contentId));
        statement.execute();
        if (statement.getGeneratedKeys().next()) {
            connection.setAutoCommit(true);
            return statement.getGeneratedKeys().getInt(1);
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public Optional<Message> get(String id) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(GET.QUERY);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            connection.setAutoCommit(true);
            return Optional.of(new Message(
                    set.getString("id"),
                    set.getString("from_id"),
                    set.getString("to_id"),
                    set.getString("content"),
                    ZonedDateTime.ofInstant(set.getTimestamp("date_time").toInstant(), ZoneId.of("UTC"))));
        }
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public int delete(Message message) {
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(DELETE.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, Integer.parseInt(message.getId()));
        statement.execute();
        if (statement.getGeneratedKeys().next()) {
            connection.setAutoCommit(true);
            return statement.getGeneratedKeys().getInt(1);
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            messages.add(new Message(
                    set.getString("id"),
                    set.getString("from_id"),
                    set.getString("to_id"),
                    set.getString("content"),
                    ZonedDateTime.ofInstant(set.getTimestamp("date_time").toInstant(), ZoneId.of("UTC"))));
        }
        return messages;
    }
}