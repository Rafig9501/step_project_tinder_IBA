package servlets;

import dao.UserDao;
import database.JdbcConfig;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static database.JdbcConfig.getConnection;

public class RegistrationServlet extends HttpServlet {
    TemplateEngine engine;

    public RegistrationServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        engine.render("register.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter pw = resp.getWriter()) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String photoUrl = req.getParameter("photoUrl");
            String password = req.getParameter("password");
            String repeatPassword = req.getParameter("repeatPassword");
            if (repeatPassword.equals(password) && !new UserDao(JdbcConfig.getConnection()).getByEmail(email).isPresent()) {
                User user = new User(name, surname, email, photoUrl, password);
                new UserDao(JdbcConfig.getConnection()).create(user);
                resp.sendRedirect("/login");
            } else if (!repeatPassword.equals(password)) {
                pw.write("the password was repeated incorrectly, please try again");
            }
        }
    }
}
