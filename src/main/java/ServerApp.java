import dao.LikesDao;
import dao.MessagesDao;
import dao.UserDao;
import database.JdbcConfig;
import filter.LogOutFilter;
import filter.LoginFilter;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;
import servlets.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static utilities.constants.HttpPaths.*;
import static utilities.constants.LocalFiles.STATIC_CSS;
import static utilities.constants.LocalFiles.STATIC_IMAGES;

@Log4j2
public class ServerApp {
    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        ServletContextHandler svh = new ServletContextHandler();
        svh.addServlet(new ServletHolder(new StaticServlet(STATIC_CSS)), STATIC_CSS_URL);
        svh.addServlet(new ServletHolder(new StaticServlet(STATIC_IMAGES)), STATIC_IMAGES_URL);
        svh.addServlet(new ServletHolder(new UserServlet(new UserService(new LikesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())), new LikeService(new LikesDao(JdbcConfig.getConnection())))), USERS_PAGE);
        svh.addServlet(new ServletHolder(new LoginServlet(new LoginService(new UserDao(JdbcConfig.getConnection())))), LOGIN_PAGE);
        svh.addServlet(new ServletHolder(new RegistrationServlet(new RegistrationService(new UserDao(JdbcConfig.getConnection())))), REGISTRATION_PAGE);
        svh.addServlet(new ServletHolder(new PeoplesServlet(new LikedPeoplesService(new UserDao(JdbcConfig.getConnection())))), LIKE_PEOPLE_PAGE);
        svh.addServlet(new ServletHolder(new MessageServlet(new MessageService(new MessagesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())))), MESSAGING_PAGE);
        svh.addFilter(new FilterHolder(new LoginFilter(new UserDao(JdbcConfig.getConnection()))), LOGIN_PAGE, ft);
//        svh.addFilter(new FilterHolder(new LogOutFilter(new UserService(new LikesDao(JdbcConfig.getConnection()),new UserDao(JdbcConfig.getConnection()))), LOGIN_PAGE, ft);s
        svh.addFilter(new FilterHolder(new LogOutFilter(new UserService(new LikesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())))), LOGIN_PAGE, ft);
        server.setHandler(svh);
        server.start();
        server.join();
    }
}