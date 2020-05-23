package service;

import dao.UserDao;
import database.JdbcConfig;
import entity.User;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static utilities.constants.HttpPaths.LOGIN_PAGE;
import static utilities.constants.HttpPaths.REGISTRATION_PAGE;
import static utilities.constants.LocalFiles.ENGINE_FOLDER;

@Log4j2
public class RegistrationService {
    private final UserDao userDao;
    private final TemplateEngine engine;

    @SneakyThrows
    public RegistrationService(UserDao userDao) {
        this.userDao = userDao;
        this.engine = new TemplateEngine(ENGINE_FOLDER);
    }

    @SneakyThrows
    public void settingEngine(String fileLocation, HttpServletResponse response) {
        engine.render(fileLocation, response);
    }

    @SneakyThrows
    public void getParameters(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String photoUrl = req.getParameter("photoUrl");
            String password = req.getParameter("password");
            User user = new User(name, surname, email, photoUrl, password);
            userDao.create(user);
            resp.sendRedirect(LOGIN_PAGE);
        } catch (Exception e) {
            log.error("Error in RegistrationService.getParameters() " + e.getMessage());
            resp.sendRedirect(LOGIN_PAGE);
        }
    }
}
