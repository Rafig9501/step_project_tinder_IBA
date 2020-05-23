import lombok.extern.log4j.Log4j2;
import servlets.ControllerServlets;

@Log4j2
public class TinderApp {

    public static void main(String[] args) throws Exception {
        new ControllerServlets().run();
    }
}