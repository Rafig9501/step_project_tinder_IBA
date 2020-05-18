import dao.LikesDao;
import dao.MessagesDao;
import dao.UserDao;
import database.JdbcConfig;
import entity.Like;
import entity.Message;
import entity.User;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

//        int i = new UserDao(JdbcConfig.getConnection()).create(new User(null, "rafigmammedzade@gmail.com","gugug", "Rafig", "Mammadzada",
//                "bla bla bla", null));
//        System.out.println(i);
//
//        User user = new UserDao(JdbcConfig.getConnection()).get("18").get();
//        System.out.println(user.toString());

        User user = new UserDao(JdbcConfig.getConnection()).get("jimms@gmail.com","bla bla").get();
        System.out.println(user.toString());
        System.out.println(new UserDao(JdbcConfig.getConnection()).getAll());

//        int kerry = new UserDao(JdbcConfig.getConnection()).delete(new User("18", "Kerry","gugg",null, null, null, null));
//        System.out.println(kerry);
//        new UserDao(JdbcConfig.getConnection()).getAll().forEach(System.out::println);
//
//        int i = new LikesDao(JdbcConfig.getConnection()).create(new Like("0", new User("15", "rafigmammedzade@gmail.com", "Jim", "Jackson",
//                "bla bla bla", null), new User("16", "rafigmammedzade@gmail.com", "Rafig", "Mammadzada",
//                "bla bla bla", null), true));
//
//        Optional<Like> like = new LikesDao(JdbcConfig.getConnection()).get("3");

//        int like = new LikesDao(JdbcConfig.getConnection()).delete(new Like("2", "15", "16", null));
//
//        List<Like> all = new LikesDao(JdbcConfig.getConnection()).getAll();
//
//        new UserDao(JdbcConfig.getConnection()).getAllLiked("4").forEach(System.out::println);
//
//        new MessagesDao(JdbcConfig.getConnection()).create(new Message(
//                null,"15","17","Hello from Agabala to Bagdagul",null));
//
//        new MessagesDao(JdbcConfig.getConnection()).create(new Message(
//                null,"16","15","Hello from Gulbahar to Agabala",null));

//        System.out.println(new MessagesDao(JdbcConfig.getConnection()).get("13"));

//        System.out.println(new MessagesDao(JdbcConfig.getConnection()).getAll());
//
//        System.out.println(new MessagesDao(JdbcConfig.getConnection()).delete(new Message(
//                "14", "1", "4", "Hello from Jim to Ann", null)));

    }
}
