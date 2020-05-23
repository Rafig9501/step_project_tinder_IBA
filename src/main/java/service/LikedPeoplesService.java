package service;

import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static utilities.constants.LocalFiles.ENGINE_FOLDER;
import static utilities.constants.LocalFiles.LIKED_PEOPLES;

public class LikedPeoplesService {

    private final UserDao userDao;
    private final TemplateEngine engine;

    @SneakyThrows
    public LikedPeoplesService(UserDao userDao) {
        this.userDao = userDao;
        this.engine = new TemplateEngine(ENGINE_FOLDER);
    }

    private List<User> getLikedUsers(String currentId) {
        return userDao.getLikedUsers(currentId);
    }

    public void displayLikedUsers(HttpServletRequest req, HttpServletResponse resp) {
        String currentId = new CookiesService(req, resp).getCookie().getValue();
        List<User> likedUsers = getLikedUsers(currentId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", likedUsers);
        engine.render(LIKED_PEOPLES, data, resp);
    }
}