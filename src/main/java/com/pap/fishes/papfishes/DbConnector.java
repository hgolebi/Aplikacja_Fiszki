package com.pap.fishes.papfishes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
//    static String URL = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
    static String URL = "jdbc:mysql://localhost:3306/fishes";
    static String username = "fishes_user";
    static String password = "fish123";
    public static Connection connect() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, username, password);
            System.out.println("Udalo sie!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
