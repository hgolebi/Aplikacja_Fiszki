package com.pap.fishes.papfishes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuerySender {
    public static ResultSet select(String query) throws RuntimeException{
        try {
            Connection c = DbConnector.connect();
            Statement s = c.createStatement();
            return s.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
