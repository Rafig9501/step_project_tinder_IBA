package service;

import dao.LikesDao;
import dao.UserDao;
import entity.User;

import java.util.Optional;

public class UserService {

    private final LikesDao likesDao;
    private final UserDao userDao;

    public UserService(LikesDao likesDao, UserDao userDao) {
        this.likesDao = likesDao;
        this.userDao = userDao;
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
        if (randomUser.isPresent()) {
            if (!notReactedUser(currentUserId, randomUser.get().getId()))
                return randomUser;
        }
        return Optional.empty();
    }

    private boolean notReactedUser(String fromId, String toId) {
        return likesDao.get(fromId, toId).isPresent();
    }
}