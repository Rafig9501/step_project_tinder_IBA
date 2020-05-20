package service;

import dao.LikesDao;
import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Optional;

import static utilities.constants.LocalFiles.ENGINE_FOLDER;
import static utilities.constants.LocalFiles.LIKE_DISLIKE_FTL;

public class UserService {

    private final LikesDao likesDao;
    private final UserDao userDao;
    private final TemplateEngine engine;

    @SneakyThrows
    public UserService(LikesDao likesDao, UserDao userDao) {
        this.likesDao = likesDao;
        this.userDao = userDao;
        engine = new TemplateEngine(ENGINE_FOLDER);
    }

    public void getUserToShow(HttpServletRequest req, HttpServletResponse resp) {
        CookiesService cookiesService = new CookiesService(req, resp);
        int id = Integer.parseInt(cookiesService.getCookie().getValue());
        User randomUser = getRandomUser(String.valueOf(id));
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", randomUser);
        engine.render(LIKE_DISLIKE_FTL, data, resp);
    }

    public User getRandomUser(String currentId) {
        Optional<User> user = checkRandomUser(currentId);
        if (user.isPresent()) {
            return user.get();
        } else {
            checkRandomUser(currentId);
            return null;
        }
    }

    private Optional<User> checkRandomUser(String currentUserId) {
        Optional<User> randomUser = userDao.getRandomUser(currentUserId);
        if ((randomUser.isPresent())
                && (!notReactedUser(currentUserId, randomUser.get().getId()))
                && (ifAllUsersChecked(currentUserId)))
            return randomUser;
        return Optional.empty();
    }

    private boolean notReactedUser(String fromId, String toId) {
        return likesDao.get(fromId, toId).isPresent();
    }

    private boolean ifAllUsersChecked(String fromId) {
        int countFromId = likesDao.getCountFromId(fromId);
        int allUsersCount = userDao.getAllUsersCount();
        if (countFromId != -1) {
            return (countFromId) < (allUsersCount - 1);
        }
        return false;
    }
}