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
    public List<User> findAll(int page, int size) {
        User user = null;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM chat.user ORDER BY user_id OFFSET " + (page * size).toString() + " ROWS FETCH NEXT " + size.toString() + "ROWS ONLY");
            ResultSet result = query.executeQuery();
            while (result.next())
            {
                user = new User(result.getLong("user_id"),
                    result.getString("login"),
                    result.getString("password"),
                        result.get,
                    new ArrayList<>());
            }
            if (result.next()) {
            }tc (SQLException 

    ) {
            e}
    
    
        return (Optional.ofNullable(user));
    }
