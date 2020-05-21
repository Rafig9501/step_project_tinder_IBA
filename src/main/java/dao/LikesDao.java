package dao;

import entity.Like;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.commands.LikeCommand.*;

@Log4j2
public class LikesDao implements DAO<Like> {

    private final Connection connection;

    @SneakyThrows
    public LikesDao(Optional<Connection> connection) {
        this.connection = connection.orElseThrow(RuntimeException::new);
        this.connection.setAutoCommit(false);
    }

    @Override
    public int create(Like like) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, Integer.parseInt(like.getFromUser()));
            statement.setInt(2, Integer.parseInt(like.getToUser()));
            statement.setBoolean(3, like.getIsLiked());
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
    public Optional<Like> get(String id) {
        try (PreparedStatement statement = connection.prepareStatement(GET.QUERY)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            return !set.next() ? Optional.empty() : Optional.of(
                    new Like(
                            set.getString("id"),
                            set.getString("from_user_id"),
                            set.getString("to_user_id"),
                            set.getObject("is_like", Boolean.class)));
        } catch (Exception e) {
            log.error("error in LikesDao.get(String id)");
            return Optional.empty();
        }
    }

    @Override
    public int delete(Like like) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE.QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, Integer.parseInt(like.getId()));
            statement.execute();
            if (statement.getGeneratedKeys().next()) {
                connection.setAutoCommit(true);
                return statement.getGeneratedKeys().getInt(1);
            }
            else return -1;
        } catch (Exception e) {
            log.error("error in LikesDao.delete(Like like)");
            return -1;
        }
    }

    @Override
    public List<Like> getAll() {
        List<Like> likeList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL.QUERY)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                likeList.add(new Like(
                        set.getString("id"),
                        set.getString("from_user_id"),
                        set.getString("to_user_id"),
                        set.getObject("is_like", Boolean.class)));
            }
        } catch (Exception e) {
            log.error("error in LikesDao.getAll()");
            return likeList;
        }
        return likeList;
    }

    public Optional<Like> get(String fromId, String toId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_FROM_ID_AND_TO_ID.QUERY)) {
            statement.setInt(1, Integer.parseInt(fromId));
            statement.setInt(2, Integer.parseInt(toId));
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                connection.setAutoCommit(true);
                return Optional.of(new Like(
                        set.getString("id"),
                        set.getString("from_user_id"),
                        set.getString("to_user_id"),
                        set.getObject("is_like", Boolean.class)));
            }
        } catch (Exception e) {
            log.error("error in LikesDao.get(String fromId, String toId)");
            return Optional.empty();
        }
        return Optional.empty();
    }


    public int getCountFromId(String id) {
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNT_FROM_ID.QUERY)) {
            statement.setInt(1, Integer.parseInt(id));
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return set.getInt("count");
            }
        } catch (Exception e) {
            log.error("error in LikesDao.getCountFromId()");
        }
        return 0;
    }
}

