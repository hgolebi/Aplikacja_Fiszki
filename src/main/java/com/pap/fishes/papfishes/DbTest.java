package com.pap.fishes.papfishes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTest {
    public static void main(String[] args) {
        String data = null;
        try {
            ResultSet result = QuerySender.select("SELECT * FROM fishes");
            result.next();
            data = result.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(data);
    }
}
