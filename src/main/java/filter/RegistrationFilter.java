package filter;

import entity.User;
import org.eclipse.jetty.http.HttpMethod;
import service.LoginService;
import utilities.engine.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static utilities.constants.LocalFiles.INFO_FTL;

public class RegistrationFilter implements Filter {

    private final LoginService loginService;
    private final TemplateEngine engine;

    public RegistrationFilter(LoginService loginService, TemplateEngine engine) throws IOException {
        this.loginService = loginService;
        this.engine = engine;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public Optional<User> checkingUser(String email) {
        return loginService.checkingUserByEmail(email);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            Optional<User> user = checkingUser(req.getParameter("email"));
            if (user.isPresent()) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("info", "User with this email already exists");
                engine.render(INFO_FTL, data, resp);
            } else {
                HashMap<String, Object> data = new HashMap<>();
                data.put("info", "You have been registered successfully");
                engine.render(INFO_FTL, data, resp);
                chain.doFilter(servletRequest, servletResponse);
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
