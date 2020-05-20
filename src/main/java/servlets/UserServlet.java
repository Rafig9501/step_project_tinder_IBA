package servlets;

import lombok.SneakyThrows;
import service.LikeService;
import service.UserService;
import utilities.engine.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utilities.constants.HttpPaths.LOGIN_PAGE;
import static utilities.constants.LocalFiles.ENGINE_FOLDER;
import static utilities.constants.LocalFiles.LIKE_DISLIKE_FTL;

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
        service.getUserToShow(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        likeService.getReaction(req,resp);
        doGet(req,resp);
    }
}