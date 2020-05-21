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
    }

    @Override
    public int create(Message message) {
        int contentId = createContent(message.getContent());
        return (contentId == -1) ? -1 :
                relateContent(message.getFromId(), message.getToId(), String.valueOf(contentId));
    }

    private int createContent(String content) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_CONTENT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, content);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.execute();
            return !statement.getGeneratedKeys().next() ? -1 : statement.getGeneratedKeys().getInt(1);
        } catch (Exception e) {
            return -1;
        }
    }

    private int relateContent(String fromId, String toId, String contentId) {
        try (PreparedStatement statement = connection.prepareStatement(RELATE_CONTENT.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, Integer.parseInt(fromId));
            statement.setInt(2, Integer.parseInt(toId));
            statement.setInt(3, Integer.parseInt(contentId));
            statement.execute();
            if (statement.getGeneratedKeys().next()) {
                connection.setAutoCommit(true);
                return statement.getGeneratedKeys().getInt(1);
            } else return -1;

        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Optional<Message> get(String id) {
        try (PreparedStatement statement = connection.prepareStatement(GET.QUERY)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            return !set.next() ? Optional.empty() : Optional.of(
                    new Message(
                            set.getString("id"),
                            set.getString("from_id"),
                            set.getString("to_id"),
                            set.getString("content"),
                            ZonedDateTime.ofInstant(set.getTimestamp("date_time").toInstant(), ZoneId.of("UTC"))));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int delete(Message message) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, Integer.parseInt(message.getId()));
            statement.execute();
            if (statement.getGeneratedKeys().next()) {
                connection.setAutoCommit(true);
                return statement.getGeneratedKeys().getInt(1);
            } else return -1;

        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY)) {
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

        } catch (Exception e) {
            return messages;
        }
    }
}