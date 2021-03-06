package filter;

import entity.User;
import lombok.SneakyThrows;
import org.eclipse.jetty.http.HttpMethod;
import service.CookiesService;
import service.LoginService;
import utilities.engine.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static utilities.constants.LocalFiles.INFO_FTL;

public class LoginFilter implements Filter {

    private final LoginService loginService;
    private final TemplateEngine engine;

    @SneakyThrows
    public LoginFilter(LoginService loginService, TemplateEngine engine) {
        this.loginService = loginService;
        this.engine = engine;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public Optional<User> checkingUser(String email, String password) {
        return loginService.checkingUser(email, password);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            Optional<User> user = checkingUser(req.getParameter("email"), req.getParameter("password"));
            if (user.isPresent()) {
                new CookiesService(req, resp).addCookie(user.get().getId());
            } else {
                HashMap<String, Object> data = new HashMap<>();
                data.put("info", "Email or Password is incorrect");
                engine.render(INFO_FTL, data, resp);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
