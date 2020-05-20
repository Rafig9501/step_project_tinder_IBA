import dao.UserDao;
import database.JdbcConfig;
import entity.User;

public class Main {

    public static void main(String[] args) {
//        User randomUser = new UserService(new LikesDao(JdbcConfig.getConnection()), new UserDao(JdbcConfig.getConnection())).getRandomUser("1");
//        System.out.println(randomUser);
        User user = new UserDao(JdbcConfig.getConnection()).get("1").get();
        int lastLogin = new UserDao(JdbcConfig.getConnection()).updateLastLogin("4");


        System.out.println(lastLogin);

    }
}