package dao;

import entity.Like;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.commands.LikeCommand.*;

public class LikesDao implements DAO<Like> {

    private final Connection connection;

    @SneakyThrows
    public LikesDao(Optional<Connection> connection) {
        this.connection = connection.orElseThrow(RuntimeException::new);
        this.connection.setAutoCommit(false);
    }

    @SneakyThrows
    @Override
    public int create(Like like) {
        PreparedStatement statement = connection.prepareStatement(CREATE.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, Integer.parseInt(like.getFromUser()));
        statement.setInt(2, Integer.parseInt(like.getToUser()));
        statement.setBoolean(3, like.getIsLiked());
        statement.execute();
        if (statement.getGeneratedKeys().next()) {
            connection.setAutoCommit(true);
            return statement.getGeneratedKeys().getInt(1);
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public Optional<Like> get(String id) {
        PreparedStatement statement = connection.prepareStatement(GET.QUERY);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            connection.setAutoCommit(true);
            return Optional.of(new Like(
                    set.getString("id"),
                    set.getString("from_user_id"),
                    set.getString("to_user_id"),
                    set.getObject("is_like", Boolean.class)));
        }
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public int delete(Like like) {
        PreparedStatement statement = connection.prepareStatement(DELETE.QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, Integer.parseInt(like.getId()));
        statement.execute();
        if (statement.getGeneratedKeys().next()) {
            connection.setAutoCommit(true);
            return statement.getGeneratedKeys().getInt(1);
        }
        return -1;
    }

    @SneakyThrows
    @Override
    public List<Like> getAll() {
        List<Like> likeList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            likeList.add(new Like(
                    set.getString("id"),
                    set.getString("from_user_id"),
                    set.getString("to_user_id"),
                    set.getObject("is_like", Boolean.class)));
        }
        return likeList;
    }
}
