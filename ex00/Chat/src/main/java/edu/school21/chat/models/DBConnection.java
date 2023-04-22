package edu.school21.chat.models;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBConnection {
    Connection connection = null;
    String urlConnection;
    String user;
    String password;

    public DBConnection(String url, String u, String pw) {
        this.urlConnection = url;
        this.user = u;
        this.password = pw;
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Database connection successful");
        } catch (SQLException e) {
            System.err.printf("SQLException::DBConnection::connect::Sqlcode %d\n", e.getErrorCode());
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            System.err.printf("Class name not found: %s\n", e.getMessage());
            System.exit(-1);
        }
    }

    public void process(String sqlUrl) {
        File sqlFile = new File(sqlUrl);
        Scanner scan;
        try {
            scan = new Scanner(sqlFile).useDelimiter(";");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            Statement statement = connection.createStatement();
            while (scan.hasNext()) {
                statement.execute(scan.next());
            }
            System.out.printf("%s successful executed\n", sqlUrl);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scan.close();

    }
}
