package service;

import dao.LikesDao;
import entity.Like;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikeService {

    private final LikesDao likesDao;

    public LikeService(LikesDao likesDao) {
        this.likesDao = likesDao;
    }

    public void getReaction(HttpServletRequest req, HttpServletResponse resp) {
        CookiesService cookiesService = new CookiesService(req, resp);
        String currentUserId = cookiesService.getCookie().getValue();
        String toUser = req.getParameter("userId");
        if (req.getParameter("like") != null) {
            likesDao.create(new Like(null, currentUserId, toUser, true));
        } else if (req.getParameter("dislike") != null) {
            likesDao.create(new Like(null, currentUserId, toUser, false));
        }
    }
}