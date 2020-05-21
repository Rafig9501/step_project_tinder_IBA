package service;

import dao.MessagesDao;
import entity.Message;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import static utilities.constants.LocalFiles.CHAT;
import static utilities.constants.LocalFiles.ENGINE_FOLDER;

public class MessageService {

    private final MessagesDao messagesDao;
    private final TemplateEngine engine;

    @SneakyThrows
    public MessageService(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
        engine = new TemplateEngine(ENGINE_FOLDER);
    }

    public void getMessages(HttpServletRequest req, HttpServletResponse resp) {
        String fromId = new CookiesService(req, resp).getCookie().getValue();
        List<Message> allLiked = messagesDao.getAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("messages", allLiked);
        data.put("fromId", fromId);
        engine.render(CHAT, data, resp);
    }

    public int sendMessage(HttpServletRequest req, HttpServletResponse resp, String text, String toId) {
        String fromId = new CookiesService(req, resp).getCookie().getValue();
        Message message = new Message(null, fromId,toId,text,null);
        return messagesDao.create(message);
    }
}
