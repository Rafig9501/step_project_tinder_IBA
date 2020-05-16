package dao;

import entity.Like;

import java.util.List;
import java.util.Optional;

public class LikesDao implements DAO<Like> {

    @Override
    public int create(Like like) {
        return 0;
    }

    @Override
    public Optional<Like> get(String id) {
        return Optional.empty();
    }

    @Override
    public int delete(Like like) {
        return 0;
    }

    @Override
    public List<Like> getAll() {
        return null;
    }
}
