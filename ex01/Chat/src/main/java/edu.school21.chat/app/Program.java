package edu.school21.chat.app;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID:");
        try {
            Long id = scanner.nextLong();
            System.out.println(msgRepo.findById(id).orElse(null));
        } catch (Exception e) {
            System.err.println("Id: invalid value");
        }
    }
}