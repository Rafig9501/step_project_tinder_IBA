package service;

import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import utilities.constants.LocalFiles;
import utilities.engine.TemplateEngine;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static utilities.constants.HttpPaths.*;
import static utilities.constants.LocalFiles.*;

public class LoginService {

    private final UserDao userDao;
    private final TemplateEngine engine;

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
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }

    @SneakyThrows
    public int logInUser(HttpServletRequest req, HttpServletResponse resp) {
        Optional<User> userOptional = checkingUser(req.getParameter("email"), req.getParameter("password"));
        if (userOptional.isPresent()) {
            Cookie cookieEmail = new Cookie("email", req.getParameter("email"));
            Cookie cookieId = new Cookie("id", userOptional.get().getId());
            resp.addCookie(cookieEmail);
            resp.addCookie(cookieId);
            resp.sendRedirect(USERS_PAGE);
            return 1;
        }
        engine.render(LOGIN_FTL, resp);
        return -1;
    }
}
