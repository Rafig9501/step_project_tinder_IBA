package dao;

import entity.Message;

import java.util.List;
import java.util.Optional;

public class MessagesDao implements DAO<Message> {
    @Override
    public int create(Message message) {
        return 0;
    }

    @Override
    public Optional<Message> get(String id) {
        return Optional.empty();
    }

    @Override
    public int delete(Message message) {
        return 0;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }
}
