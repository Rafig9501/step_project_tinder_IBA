package servlets;

import service.UserService;
import utilities.engine.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    TemplateEngine engine;
    UserService service;

    public UserServlet(TemplateEngine engine, UserService service) {
        this.engine = engine;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] ck = req.getCookies();
        if (ck != null) {
//            String login = ck[0].getValue();
//            String id = ck[1].getValue();
//            User user = service.getRandomUser(id);
////            user.getSurname();
//            String email = user.getEmail();
//            String photoUrl = user.getPhotoUrl();
//            String name = user.getName();
//            String surname = use
//            System.out.println(user.getEmail());
//            System.out.println(id);
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
