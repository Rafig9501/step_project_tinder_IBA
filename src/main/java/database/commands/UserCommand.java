package database.commands;

public enum UserCommand {

    CREATE("INSERT INTO users (name, surname, photo_url, last_login, email, password) VALUES (?,?,?,?,?,?) RETURNING id"),
    GET_BY_EMAIL_AND_PASSWORD("SELECT * FROM users WHERE email = ? AND password = ?"),
    GET_BY_ID("SELECT * FROM users WHERE id = ?"),
    GET_EMAIL("SELECT * FROM users WHERE email = ?"),
    GET_RANDOM_USER("SELECT * FROM users WHERE id !=? ORDER BY random() LIMIT 1"),
    DELETE("DELETE FROM users WHERE id = ? RETURNING id"),
    GET_ALL("SELECT * FROM users"),
    GET_ALL_LIKED("SELECT to_user_id FROM like_dislike LEFT JOIN users ON like_dislike.from_user_id = users.id WHERE from_user_id = ? AND is_like = true");

    public String QUERY;

    UserCommand(String QUERY) {
        this.QUERY = QUERY;
    }
}