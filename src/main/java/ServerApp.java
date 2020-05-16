import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;


public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8089);
        ServletContextHandler svh = new ServletContextHandler();
        TemplateEngine engine = TemplateEngine.folder("./templates");
        svh.addServlet(new ServletHolder(new StaticServlet("css")), "/css/*");
        svh.addServlet(new ServletHolder(new StaticServlet("js")), "/js/*");
        svh.addServlet(new ServletHolder(new UserServlet(engine)), "/users/*");
        svh.addServlet(new ServletHolder(new LoginServlet(engine)), "/login");
        svh.addServlet(new ServletHolder(new LogoutServlet(engine)),"/logout");
        server.setHandler(svh);
        server.start();
        server.join();
    }
}
