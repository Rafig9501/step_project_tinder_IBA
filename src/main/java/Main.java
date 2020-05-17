import dao.LikesDao;
import dao.UserDao;
import database.JdbcConfig;
import entity.Like;
import entity.User;

import java.util.List;
import java.util.Optional;

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
//        new UserDao(JdbcConfig.getConnection()).getAll().forEach(System.out::println);
//
//        int i = new LikesDao(JdbcConfig.getConnection()).create(new Like("0", new User("15", "rafigmammedzade@gmail.com", "Jim", "Jackson",
//                "bla bla bla", null), new User("16", "rafigmammedzade@gmail.com", "Rafig", "Mammadzada",
//                "bla bla bla", null), true));
//
        Optional<Like> like = new LikesDao(JdbcConfig.getConnection()).get("3");

//        int like = new LikesDao(JdbcConfig.getConnection()).delete(new Like("2", "15", "16", null));

        List<Like> all = new LikesDao(JdbcConfig.getConnection()).getAll();

        System.out.println(all);
        System.out.println(like);

    }
}
