package servlets;

import service.LikedPeoplesService;
import utilities.constants.HttpPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PeoplesServlet extends HttpServlet {

    private final LikedPeoplesService likedPeoplesService;

    public PeoplesServlet(LikedPeoplesService likedPeoplesService) {
        this.likedPeoplesService = likedPeoplesService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        likedPeoplesService.displayLikedUsers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
