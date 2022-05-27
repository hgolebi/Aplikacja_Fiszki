package com.pap.fishes.papfishes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
    public static Vector<Fish> getAllFishes(){
        Vector<Fish> vec = new Vector<>();
        try {
            ResultSet result = QuerySender.select("SELECT * FROM Fishes");
            while (result.next()) {
                int id = result.getInt(1);
                String term = result.getString(2);
                String definition = result.getString(3);
                String category = result.getString(4);
                vec.add(new Fish(id, term, definition, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vec;
    }

    public static Vector<Fish> getFishesFromCategory(String category){
        Vector<Fish> vec = new Vector<>();
        try {
            ResultSet result = QuerySender.select("SELECT * FROM Fishes WHERE category = \"" + category + "\"");
            while (result.next()) {
                int id = result.getInt(1);
                String term = result.getString(2);
                String definition = result.getString(3);
                vec.add(new Fish(id, term, definition, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vec;
    }
    public static Vector<Fish> findFishByTerm(String term){
        Vector<Fish> vec = new Vector<>();
        try {
            ResultSet result = QuerySender.select("SELECT * FROM Fishes WHERE term = \"" + term + "\"");
            while (result.next()) {
                int id = result.getInt(1);
                String definition = result.getString(3);
                String category = result.getString(4);
                vec.add(new Fish(id, term, definition, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vec;
    }
}
