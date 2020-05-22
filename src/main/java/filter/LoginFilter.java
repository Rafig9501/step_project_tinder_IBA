package filter;

import dao.UserDao;
import entity.User;
import lombok.SneakyThrows;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static utilities.constants.HttpPaths.LOGIN_PAGE;

public class LoginFilter implements Filter {

    private final UserDao userDao;

    public LoginFilter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public Optional<User> checkingUser(String email, String password) {
        return userDao.get(email, password);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            Optional<User> user = checkingUser(req.getParameter("email"), req.getParameter("password"));
            if (!user.isPresent())
                resp.sendRedirect(LOGIN_PAGE);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
