package servlets;

import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utilities.constants.HttpPaths.MESSAGING_PAGE;

public class MessageServlet extends HttpServlet {

    private final MessageService messageService;

    public MessageServlet(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String receiverUserId = req.getParameter("receiverUser");
        messageService.getMessages(req, resp, receiverUserId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String toId = req.getParameter("receiverUserId");
        if (!text.equals(""))
            messageService.sendMessage(req, resp, text, toId);
        resp.sendRedirect(MESSAGING_PAGE + "?receiverUser=" + toId);
    }
}
