import dao.UserDao;
import database.JdbcConfig;
import entity.User;

public class Main {

    public static void main(String[] args) {

//        int i = new UserDao(JdbcConfig.getConnection()).create(new User(null, "rafigmammedzade@gmail.com", "Rafig", "Mammadzada",
//                "bla bla bla", null));
//        System.out.println(i);
//
//        User user = new UserDao(JdbcConfig.getConnection()).get("1").get();
//        System.out.println(user.toString());

//        int kerry = new UserDao(JdbcConfig.getConnection()).delete(new User("2", "Kerry", null, null, null, null));
//        System.out.println(kerry);
        new UserDao(JdbcConfig.getConnection()).getAll().forEach(System.out::println);
    }
}
