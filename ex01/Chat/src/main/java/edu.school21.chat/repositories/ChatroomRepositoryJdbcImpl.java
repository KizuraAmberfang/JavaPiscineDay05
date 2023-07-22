package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import edu.school21.chat.models.Chatroom;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    Connection conn = null;
    UserRepository userRepo;

    public ChatroomRepositoryJdbcImpl(Connection conn, UserRepository uRepo) {
        this.conn = conn;
        this.userRepo = uRepo;
    }

    @Override
    public Optional<Chatroom> findById(Long id) {
        Chatroom chat = null;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM chat.chatroom WHERE chat_id=?");
            query.setLong(1, id);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                chat = new Chatroom(
                        result.getLong("chat_id"),
                        result.getString("chat_name"),
                        userRepo.findById(result.getLong("chat_owner")).orElse(null),
                        new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Optional.ofNullable(chat));
    }
}