package servlets;

import utilities.engine.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    TemplateEngine engine;

    public UserServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] ck = req.getCookies();
        if (ck != null) {
            String login = ck[0].getValue();
            engine.render("like-page.ftl", resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("/users/");
//        List<User> all = new UserDao(JdbcConfig.getConnection()).getAll();
//        HashMap<String,Object>
        engine.render("like-page.ftl", resp);

    }
}
