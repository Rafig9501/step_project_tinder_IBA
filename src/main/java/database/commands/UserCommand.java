package database.commands;

public enum UserCommand {

    CREATE("INSERT INTO users (name, surname, email, photo_url, last_login) VALUES (?,?,?,?,?) RETURNING id"),
    GET_ID("SELECT * FROM users WHERE id = ?"),
    GET_EMAIL("SELECT * FROM users WHERE email = ?"),
    DELETE("DELETE FROM users WHERE id = ? RETURNING id"),
    GET_ALL("SELECT * FROM users"),
    GET_ALL_LIKED("SELECT to_user_id FROM like_dislike LEFT JOIN users ON like_dislike.from_user_id = users.id WHERE from_user_id = ? AND is_like = true");

    public String QUERY;

    UserCommand(String QUERY) {
        this.QUERY = QUERY;
    }
}