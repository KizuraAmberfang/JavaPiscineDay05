package edu.school21.chat.models;

public class App {

    final static String connectionString = "jdbc:postgresql://localhost:5432/postgres";
    final static String user = "test";
    final static String pass = "test";

    public static void main(String[] args) {
        DBConnection connection = new DBConnection(connectionString, user, pass);
        connection.connect();
        connection.process("/home/riku/Documents/Java/JavaPiscineDay05/ex00/Chat/src/main/resources/schema.sql");
        connection.process("/home/riku/Documents/Java/JavaPiscineDay05/ex00/Chat/src/main/resources/data.sql");
    }
}
