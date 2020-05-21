import dao.LikesDao;
import dao.UserDao;
import database.JdbcConfig;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;
import servlets.*;
import utilities.constants.HttpPaths;
import utilities.engine.TemplateEngine;

@Log4j2
public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        ServletContextHandler svh = new ServletContextHandler();
        TemplateEngine engine = new TemplateEngine("./templates");
        svh.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");
        svh.addServlet(new ServletHolder(new StaticServlet("images")), "/images/*");
        svh.addServlet(new ServletHolder(new UserServlet(new UserService(new LikesDao(JdbcConfig.getConnection()),new UserDao(JdbcConfig.getConnection())),new LikeService(new LikesDao(JdbcConfig.getConnection())))),"/users/*");
        svh.addServlet(new ServletHolder(new LoginServlet(new LoginService(new UserDao(JdbcConfig.getConnection())))), HttpPaths.LOGIN_PAGE);
        svh.addServlet(new ServletHolder(new RegistrationServlet(new RegistrationService(new UserDao(JdbcConfig.getConnection())))), HttpPaths.REGISTRATION_PAGE);
        svh.addServlet(new ServletHolder(new PeoplesServlet(new LikedPeoplesService(new UserDao(JdbcConfig.getConnection())))), HttpPaths.LIKE_PEOPLE);
        server.setHandler(svh);
        server.start();
        server.join();
    }
}