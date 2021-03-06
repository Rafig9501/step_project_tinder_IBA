package service;

import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import static utilities.constants.LocalFiles.LIKED_PEOPLES_FTL;

public class LikedPeoplesService {

    private final UserDao userDao;
    private final TemplateEngine engine;

    @SneakyThrows
    public LikedPeoplesService(UserDao userDao, TemplateEngine engine) {
        this.userDao = userDao;
        this.engine = engine;
    }

    private List<User> getLikedUsers(String currentId) {
        return userDao.getLikedUsers(currentId);
    }

    public void displayLikedUsers(HttpServletRequest req, HttpServletResponse resp) {
        String currentId = new CookiesService(req, resp).getCookie().getValue();
        List<User> likedUsers = getLikedUsers(currentId);
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", likedUsers);
        engine.render(LIKED_PEOPLES_FTL, data, resp);
    }
}