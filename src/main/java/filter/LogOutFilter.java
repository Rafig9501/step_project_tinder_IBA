package filter;

import org.eclipse.jetty.http.HttpMethod;
import service.CookiesService;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutFilter implements Filter {

    private final UserService userService;

    public LogOutFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie cookie = new CookiesService(req, resp).getCookie();
        if (HttpMethod.GET.name().equalsIgnoreCase(req.getMethod()) && cookie != null) {
            userService.updateLastLogin(cookie.getValue());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
