package servlets;

import dao.UserDao;
import database.JdbcConfig;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    TemplateEngine engine;

    public LoginServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    static boolean userExist(HttpServletRequest req) {
        Optional<User> user = new UserDao(JdbcConfig.getConnection()).get(req.getParameter("login"), req.getParameter("password"));
        return user.isPresent();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        engine.render("login.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter pw = resp.getWriter()) {
            String login = req.getParameter("login");
            if (userExist(req)) {
                Cookie ck = new Cookie("login", login);
                resp.addCookie(ck);
                resp.sendRedirect("/users");
            } else {
                engine.render("login.ftl", resp);
            }
        }
    }
}
