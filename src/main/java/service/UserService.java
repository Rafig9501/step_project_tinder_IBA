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