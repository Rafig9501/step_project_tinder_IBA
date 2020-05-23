package filter;

import entity.User;
import org.eclipse.jetty.http.HttpMethod;
import service.LoginService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static utilities.constants.HttpPaths.LOGIN_PAGE;

public class RegistrationFilter implements Filter {

    private final LoginService loginService;

    public RegistrationFilter(LoginService loginService) {
        this.loginService = loginService;
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
                resp.sendRedirect(LOGIN_PAGE);
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
