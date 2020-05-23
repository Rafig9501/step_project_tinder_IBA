package servlets;

import dao.LikesDao;
import dao.MessagesDao;
import dao.UserDao;
import database.JdbcConfig;
import filter.LogOutFilter;
import filter.LoginFilter;
import filter.RegistrationFilter;
import lombok.SneakyThrows;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;
import utilities.heroku.HerokuEnvironment;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static utilities.constants.HttpPaths.*;
import static utilities.constants.LocalFiles.STATIC_CSS;
import static utilities.constants.LocalFiles.STATIC_IMAGES;

public class ControllerServlets {

    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    @SneakyThrows
    public void run(){
        Server server = new Server(HerokuEnvironment.port());
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new StaticServlet(STATIC_CSS)), STATIC_CSS_URL);
        handler.addServlet(new ServletHolder(new StaticServlet(STATIC_IMAGES)), STATIC_IMAGES_URL);
        handler.addServlet(new ServletHolder(new UserServlet(new UserService(new LikesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())), new LikeService(new LikesDao(JdbcConfig.getConnection())))), USERS_PAGE);
        handler.addServlet(new ServletHolder(new LoginServlet(new LoginService(new UserDao(JdbcConfig.getConnection())))), LOGIN_PAGE);
        handler.addServlet(new ServletHolder(new RegistrationServlet(new RegistrationService(new UserDao(JdbcConfig.getConnection())))), REGISTRATION_PAGE);
        handler.addServlet(new ServletHolder(new PeoplesServlet(new LikedPeoplesService(new UserDao(JdbcConfig.getConnection())))), LIKE_PEOPLE_PAGE);
        handler.addServlet(new ServletHolder(new MessageServlet(new MessageService(new MessagesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())))), MESSAGING_PAGE);
        handler.addFilter(new FilterHolder(new LoginFilter(new LoginService(new UserDao(JdbcConfig.getConnection())))), LOGIN_PAGE, ft);
        handler.addFilter(new FilterHolder(new LogOutFilter(new UserService(new LikesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())))), LOGIN_PAGE, ft);
        handler.addFilter(new FilterHolder(new RegistrationFilter(new LoginService(new UserDao(JdbcConfig.getConnection())))), REGISTRATION_PAGE, ft);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
