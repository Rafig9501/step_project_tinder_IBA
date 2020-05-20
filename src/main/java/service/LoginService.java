package service;

import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static utilities.constants.HttpPaths.USERS_PAGE;
import static utilities.constants.LocalFiles.ENGINE_FOLDER;
import static utilities.constants.LocalFiles.LOGIN_FTL;

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

    public Optional<User> checkingUser(String email, String password) {
        return userDao.get(email, password);
    }

    public void logOutUser(HttpServletRequest req, HttpServletResponse resp) {
        cookiesService = new CookiesService(req, resp);
        cookiesService.removeCookie();
    }

    @SneakyThrows
    public int logInUser(HttpServletRequest req, HttpServletResponse resp) {
        cookiesService = new CookiesService(req, resp);
        Optional<User> user = checkingUser(req.getParameter("email"), req.getParameter("password"));
        if (user.isPresent()) {
            cookiesService.addCookie(user.get().getId());
            resp.sendRedirect(USERS_PAGE);
            return 1;
        }
        engine.render(LOGIN_FTL, resp);
        return -1;
    }
}