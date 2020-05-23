package filter;

import entity.User;
import org.eclipse.jetty.http.HttpMethod;
import service.LoginService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

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
                resp.getWriter().write("User with this email already exists");
            } else {
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
