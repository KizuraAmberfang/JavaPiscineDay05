package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import edu.school21.chat.models.User;

public class UserRepositoryJdbcImpl implements UserRepository {
    Connection conn = null;

    public UserRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM chat.user WHERE user_id=?");
            query.setLong(1, id);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                user = new User(
                        result.getLong("user_id"),
                        result.getString("login"),
                        result.getString("password"),
                        new ArrayList<>(),
                        new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (Optional.ofNullable(user));
    }
}
