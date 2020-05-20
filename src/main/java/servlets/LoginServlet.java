package servlets;

import lombok.SneakyThrows;
import service.LoginService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static utilities.constants.LocalFiles.LOGIN_FTL;

public class LoginServlet extends HttpServlet {

    LoginService loginService;

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
        loginService.logInUser(req, resp);
    }
}
