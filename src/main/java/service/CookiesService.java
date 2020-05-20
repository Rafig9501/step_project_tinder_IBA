package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookiesService {

    private final String CURRENT_USER_ID = "id";
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public CookiesService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public Cookie getCookie() {
        Cookie result = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CURRENT_USER_ID)) {
                    result = cookie;
                }
            }
        }
        return result;
    }

    public void addCookie(String id) {
        resp.addCookie(new Cookie(CURRENT_USER_ID, id));
    }

    public void removeCookie() {
        Arrays.stream(req.getCookies()).filter(c -> c.getName().equalsIgnoreCase(CURRENT_USER_ID))
                .map(c ->
                        new Cookie(c.getName(), c.getValue()) {{
                            setMaxAge(0);
                        }})
                .forEach(resp::addCookie);
    }
}