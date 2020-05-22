package servlets;

import entity.User;
import lombok.SneakyThrows;
import service.CookiesService;
import service.LoginService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static utilities.constants.HttpPaths.USERS_PAGE;
import static utilities.constants.LocalFiles.LOGIN_FTL;


public class LoginServlet extends HttpServlet {

    LoginService loginService;
    CookiesService cookiesService;

    public LoginServlet(LoginService loginService) {
        this.loginService = loginService;
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        loginService.logOutUser(req, resp);
        loginService.settingEngine(LOGIN_FTL, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Optional<User> user = loginService.checkingUser(req.getParameter("email"), req.getParameter("password"));
        cookiesService = new CookiesService(req, resp);
        cookiesService.addCookie(user.get().getId());
        resp.sendRedirect(USERS_PAGE);
    }
}
