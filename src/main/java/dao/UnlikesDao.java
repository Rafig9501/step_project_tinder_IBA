package dao;

import entity.Unlike;

import java.util.Optional;

public class UnlikesDao implements DAO<Unlike>{

    @Override
    public int create(Unlike unlike) {
        return 0;
    }

    @Override
    public Optional<Unlike> get(String id) {
        return Optional.empty();
    }

    @Override
    public Unlike delete(Unlike unlike) {
        return null;
    }

    @Override
    public Unlike getAll() {
        return null;
    }
}
