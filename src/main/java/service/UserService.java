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
    public UserService(LikesDao likesDao, UserDao userDao) {
        this.likesDao = likesDao;
        this.userDao = userDao;
        engine = new TemplateEngine(ENGINE_FOLDER);
    }

    public Optional<User> getRandomUser(String currentId) {
        return userDao.getRandomUser(currentId);
    }

    public Optional<User> checkIsUserReacted(String currentId) {
        Optional<User> randomUser = getRandomUser(currentId);
        if (!(likesDao.get(currentId, randomUser.get().getId()).isPresent())) {
            return randomUser;
        }
        log.warn("random object is null");
        if (areAllUsersReacted(currentId)) checkIsUserReacted(currentId);
        return Optional.empty();
    }

    public boolean areAllUsersReacted(String fromId) {
        int allUsersCount = userDao.getAllUsersCount();
        int countFromId = likesDao.getCountFromId(fromId);
        if (allUsersCount != -1 && countFromId != 0) {
            return allUsersCount > countFromId + 1;
        }
        return true;
    }

    @SneakyThrows
    public void displayUser(HttpServletRequest req, HttpServletResponse resp) {
        String currentUserId = new CookiesService(req, resp).getCookie().getValue();
        Optional<User> randomUser = checkIsUserReacted(currentUserId);
        if (randomUser.isPresent()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user", randomUser.get());
            engine.render(LIKE_DISLIKE_FTL, data, resp);
        } else if (!areAllUsersReacted(currentUserId)) {
            checkIsUserReacted(currentUserId);
        } else resp.sendRedirect("/messages");
    }
}
