package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.postgresql.core.SqlCommand;

import edu.school21.chat.models.Message;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    Connection conn = null;
    UserRepository userRepo;
    ChatroomRepository chatRepo;

    public MessagesRepositoryJdbcImpl(Connection conn, UserRepository userR, ChatroomRepository chatR) {
        this.conn = conn;
        this.userRepo = userR;
        this.chatRepo = chatR;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message msg = null;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM chat.message WHERE message_id=?");
            query.setLong(1, id);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                msg = new Message(
                        result.getLong("message_id"),
                        userRepo.findById(result.getLong("author")).orElse(null),
                        chatRepo.findById(result.getLong("room")).orElse(null),
                        result.getString("text_message"),
                        result.getTimestamp("data_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Optional.ofNullable(msg));
    }
}
