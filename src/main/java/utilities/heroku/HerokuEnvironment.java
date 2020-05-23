package utilities.heroku;

public class HerokuEnvironment {

    public static int port() {
        try {
            String port = System.getenv("PORT");
            return Integer.parseInt(port);
        } catch (NumberFormatException x) {
            return 5000;
        }
    }
}
