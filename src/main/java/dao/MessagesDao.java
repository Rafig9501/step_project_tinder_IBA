package dao;

import entity.Message;

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
    public Message delete(Message message) {
        return null;
    }

    @Override
    public Message getAll() {
        return null;
    }
}
