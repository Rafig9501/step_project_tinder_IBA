package database.commands;

public enum MessageCommand {

    CREATE_CONTENT("INSERT INTO message_content (content, date_time) VALUES (?, ?) RETURNING id"),
    RELATE_CONTENT("INSERT INTO message_manager (from_id, to_id, message_content_id) VALUES (?,?,?) RETURNING id"),
    GET("SELECT message_content.id, from_id, to_id, content, date_time FROM message_manager LEFT JOIN message_content on message_manager.message_content_id = message_content.id WHERE message_content.id = ?"),
    DELETE("DELETE FROM message_content WHERE id = ? RETURNING id"),
    GET_ALL("SELECT message_content.id, from_id, to_id, content, date_time FROM message_manager LEFT JOIN message_content on message_manager.message_content_id = message_content.id WHERE (from_id = ? AND to_id = ?) OR (to_id = ? AND from_id = ?) ORDER BY message_content.date_time");

    public String QUERY;

    MessageCommand(String QUERY) {
        this.QUERY = QUERY;
    }
}
