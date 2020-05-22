package database.commands;

public enum UserCommand {

    CREATE("INSERT INTO users (name, surname, photo_url, last_login, email, password) VALUES (?,?,?,?,?,?) RETURNING id"),
    GET_BY_EMAIL_AND_PASSWORD("SELECT * FROM users WHERE email = ? AND password = ?"),
    GET_BY_ID("SELECT * FROM users WHERE id = ?"),
    GET_EMAIL("SELECT * FROM users WHERE email = ?"),
    UPDATE_LAST_LOGIN("UPDATE users SET last_login = ? WHERE id = ? RETURNING id"),
    GET_RANDOM_USER("SELECT * FROM users WHERE id != ? AND id NOT IN (SELECT to_user_id FROM like_dislike WHERE to_user_id = users.id AND from_user_id = ?)LIMIT 1"),
    GET_ALL_LIKED("SELECT to_user_id, email, name, surname , photo_url, last_login FROM users u LEFT JOIN like_dislike l_d on u.id = l_d.to_user_id WHERE from_user_id = ? AND is_like = true"),
    DELETE("DELETE FROM users WHERE id = ? RETURNING id"),
    GET_ALL("SELECT * FROM users");

    public String QUERY;

    UserCommand(String QUERY) {
        this.QUERY = QUERY;
    }
}