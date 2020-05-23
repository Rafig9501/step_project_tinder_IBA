package service;

import dao.LikesDao;
import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;

import static utilities.constants.LocalFiles.ENGINE_FOLDER;
import static utilities.constants.LocalFiles.LIKE_DISLIKE_FTL;

@Log4j2
public class UserService {

    private final LikesDao likesDao;
    private final UserDao userDao;
    private final TemplateEngine engine;

    @SneakyThrows
    public UserService(LikesDao likesDao, UserDao userDao, TemplateEngine engine) {
        this.likesDao = likesDao;
        this.userDao = userDao;
        this.engine = engine;
    }

    public void updateLastLogin(String id) {
        userDao.updateLastLogin(id);
    }

    public Optional<User> getRandomUser(String currentId) {
        return userDao.getRandomUser(currentId);
    }

    @SneakyThrows
    public void displayUser(HttpServletRequest req, HttpServletResponse resp) {
        String currentUserId = new CookiesService(req, resp).getCookie().getValue();
        Optional<User> randomUser = getRandomUser(currentUserId);
        if (!randomUser.isPresent())
            resp.sendRedirect("/liked");
        else {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user", randomUser.get());
            engine.render(LIKE_DISLIKE_FTL, data, resp);
        }
    }
}