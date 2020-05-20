package service;

import dao.UserDao;
import database.JdbcConfig;
import entity.User;
import lombok.SneakyThrows;
import utilities.engine.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static utilities.constants.HttpPaths.LOGIN_PAGE;
import static utilities.constants.LocalFiles.ENGINE_FOLDER;

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
    public int getParameters(HttpServletRequest req, HttpServletResponse resp) {
        try (PrintWriter pw = resp.getWriter()) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String photoUrl = req.getParameter("photoUrl");
            String password = req.getParameter("password");
            String repeatPassword = req.getParameter("repeatPassword");
            if (checkEmailPassword(repeatPassword, password, email)) {
                User user = new User(name, surname, email, photoUrl, password);
                userDao.create(user);
                resp.sendRedirect(LOGIN_PAGE);
                return 1;
            } else if (new UserDao(JdbcConfig.getConnection()).getByEmail(email).isPresent()) {
                pw.write("this email is existed ,please include another email");
            }
        }
        return 0;
    }

    public boolean checkEmailPassword(String repeatPassword, String password, String email) {
        return repeatPassword.equals(password) && !new UserDao(JdbcConfig.getConnection()).getByEmail(email).isPresent();
    }
}
