package database.commands;

public enum LikeCommand {

    CREATE("INSERT INTO like_dislike (from_user_id, to_user_id, is_like) VALUES (?,?,?)"),
    GET("SELECT like_dislike.id, like_dislike.from_user_id, like_dislike.to_user_id, like_dislike.is_like FROM like_dislike LEFT JOIN users on like_dislike.from_user_id = users.id WHERE like_dislike.id=?"),
    GET_BY_FROM_ID_AND_TO_ID("SELECT * FROM like_dislike WHERE from_user_id = ? AND to_user_id = ? AND is_like IS NULL"),
    DELETE("DELETE FROM like_dislike WHERE id = ? RETURNING id"),
    GET_ALL("SELECT * FROM like_dislike");

    public String QUERY;

    LikeCommand(String QUERY) {
        this.QUERY = QUERY;
    }
}
