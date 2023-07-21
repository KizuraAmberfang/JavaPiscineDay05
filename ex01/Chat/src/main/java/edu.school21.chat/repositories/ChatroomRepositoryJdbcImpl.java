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
                        result.getLong(0),
                        result.getString(1),
                        userRepo.findById(result.getLong(2)).orElse(null),
                        new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Optional.ofNullable(chat));
    }
}