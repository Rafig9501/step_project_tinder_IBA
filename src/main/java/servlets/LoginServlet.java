package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    TemplateEngine engine;

    public LoginServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        engine.render("login.ftl", resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter pw = resp.getWriter()) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (login.equals("shaiq@gmail.com") && password.equals("123")) {
                Cookie ck = new Cookie("login", login);
                resp.addCookie(ck);
                resp.sendRedirect("/users");
            } else {
                engine.render("login.ftl", resp);
            }
        }
    }
}
