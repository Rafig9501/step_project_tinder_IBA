package dao;

import entity.Like;

import java.util.Optional;

public class LikesDao implements DAO<Like>{

    @Override
    public int create(Like like) {
        return 0;
    }

    @Override
    public Optional<Like> get(String id) {
        return Optional.empty();
    }

    @Override
    public Like delete(Like like) {
        return null;
    }

    @Override
    public Like getAll() {
        return null;
    }
}
