package edu.school21.chat.app;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.ChatroomRepository;
import edu.school21.chat.repositories.ChatroomRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UserRepository;
import edu.school21.chat.repositories.UserRepositoryJdbcImpl;

public class Program {
    final static String connectionString = "jdbc:postgresql://localhost:5432/postgres";
    final static String user = "test";
    final static String pass = "test";

    public static void main(String[] args) {
        DBConnection connection = new DBConnection(connectionString, user, pass);
        connection.connect();
        connection.process("schema.sql");
        connection.process("data.sql");

        UserRepository userRepo = new UserRepositoryJdbcImpl(connection.getConnection());
        ChatroomRepository chatRepo = new ChatroomRepositoryJdbcImpl(connection.getConnection(), userRepo);
        MessagesRepository msgRepo = new MessagesRepositoryJdbcImpl(connection.getConnection(), userRepo, chatRepo);
        Message msg = msgRepo.findById(4L).get();
        System.out.println("Before: " + msg.toString());
        msg.setAuthor(null);
        msg.setRoom(null);
        msg.setText(null);
        msg.setTimestamp(null);
        msgRepo.update(msg);
        msg = msgRepo.findById(4L).get();
        System.out.println("After : " + msg.toString());
    }
}