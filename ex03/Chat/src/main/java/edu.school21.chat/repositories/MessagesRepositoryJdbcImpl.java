package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.postgresql.core.SqlCommand;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

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

    @Override
    public void save(Message msg) {
        try {
            PreparedStatement query = conn.prepareStatement(
                    "INSERT INTO chat.message (author, room, text_message, data_time) VALUES (?, ?, ?, ?) RETURNING message_id");
            query.setLong(1, userRepo.findById(msg.getAuthor().getUserId()).get().getUserId());
            query.setLong(2, chatRepo.findById(msg.getRoom().getChatId()).get().getChatId());
            query.setString(3, msg.getText());
            query.setTimestamp(4, msg.getTimestamp());
            ResultSet result = query.executeQuery();
            if (result.next())
                msg.setId(result.getLong("message_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            throw new NotSavedSubEntityException();
        }
    }
}
