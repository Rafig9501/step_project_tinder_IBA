package servlets;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import service.LikeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class UserServlet extends HttpServlet {

    private final UserService service;
    private final LikeService likeService;

    @SneakyThrows
    public UserServlet(UserService service, LikeService likeService) {
        this.service = service;
        this.likeService = likeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("UserServlet.doGet()");
        service.displayUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("UserServlet.doPost()");
        likeService.getReaction(req,resp);
        log.warn("UserServlet.doPost() reaction got, sending to doGet();");
        doGet(req,resp);
    }
}