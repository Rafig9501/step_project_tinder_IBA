import dao.LikesDao;
import dao.UserDao;
import database.JdbcConfig;
import entity.User;
import service.UserService;

public class Main {

    public static void main(String[] args) {
        User randomUser = new UserService(new LikesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())).getRandomUser("25");
        System.out.println(randomUser);
    }
}