package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    TemplateEngine engine;

    public LogoutServlet(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        for (Cookie c : req.getCookies()) {
//            c.setMaxAge(0);
//            resp.addCookie(c);
//        }
//        engine.render("like-page.ftl", resp);
//        System.out.print(req.getCookies().toString());
//        resp.sendRedirect("/login");
    }
}
