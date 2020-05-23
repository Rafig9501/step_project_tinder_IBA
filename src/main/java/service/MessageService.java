package service;

import dao.MessagesDao;
import dao.UserDao;
import entity.Message;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static utilities.constants.LocalFiles.CHAT_FTL;

public class MessageService {

    private final MessagesDao messagesDao;
    private final TemplateEngine engine;
    private final UserDao userDao;

    @SneakyThrows
    public MessageService(MessagesDao messagesDao, UserDao userDao, TemplateEngine engine) {
        this.messagesDao = messagesDao;
        this.userDao = userDao;
        this.engine = engine;
    }

    public void getMessages(HttpServletRequest req, HttpServletResponse resp, String receiverId) {
        String fromId = new CookiesService(req, resp).getCookie().getValue();
        List<Message> messagesBetweenFromIdToId = messagesDao.getAll(fromId, receiverId);
        Optional<User> receiver = userDao.get(receiverId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", fromId);
        data.put("messageList", messagesBetweenFromIdToId);
        data.put("receiver", receiver.get());
        engine.render(CHAT_FTL, data, resp);
    }

    public int sendMessage(HttpServletRequest req, HttpServletResponse resp, String text, String toId) {
        String fromId = new CookiesService(req, resp).getCookie().getValue();
        Message message = new Message(null, fromId, toId, text, null);
        return messagesDao.create(message);
    }
}