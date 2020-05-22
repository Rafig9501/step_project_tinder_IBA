package service;

import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static utilities.constants.LocalFiles.ENGINE_FOLDER;

public class LoginService {

    private final UserDao userDao;
    private final TemplateEngine engine;
    CookiesService cookiesService;

    @SneakyThrows
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
        this.engine = new TemplateEngine(ENGINE_FOLDER);
    }

    @SneakyThrows
    public void settingEngine(String fileLocation, HttpServletResponse resp) {
        engine.render(fileLocation, resp);
    }

    public void logOutUser(HttpServletRequest req, HttpServletResponse resp) {
        cookiesService = new CookiesService(req, resp);
        cookiesService.removeCookie();
    }

    @SneakyThrows
    public Optional<User> checkingUser(String email, String password) {
        return userDao.get(email, password);
    }
}