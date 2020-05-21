package service;

import dao.MessagesDao;
import entity.Message;

import java.util.List;

public class MessageService {

    private final MessagesDao messagesDao;

    public MessageService(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    public List<Message> getMessages(String fromId, String toId) {
        return messagesDao.getAll();
    }

    public int sendMessage(Message message){
        return messagesDao.create(message);
    }
}
