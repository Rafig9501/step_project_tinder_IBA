package database.commands;

public enum UserCommand {

    CREATE("INSERT INTO users (name, surname, email, photo_url, last_login) VALUES (?,?,?,?,?) RETURNING id"),
    GET("SELECT * FROM users WHERE id = ?"),
    DELETE("DELETE FROM users WHERE id = ? RETURNING id"),
    GET_ALL("SELECT * FROM users");

    public String QUERY;

    UserCommand(String QUERY) {
        this.QUERY = QUERY;
    }
}
