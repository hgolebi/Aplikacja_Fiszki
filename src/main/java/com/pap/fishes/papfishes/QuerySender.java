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
    public static Vector<String> getAllCategories(){
        Vector<String> vec = new Vector<>();
        try {
            ResultSet result = QuerySender.select("SELECT UNIQUE category FROM Fishes");
            while (result.next()) {
                vec.add(result.getString(1));
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

    public static void addFish(String ter, String def, String cat){
//        try {
//            QuerySender.select("Insert INTO Fishes VALUES(NULL, \"" + ter + "\", \"" + def + "\", \"" + cat + "\", NULL)");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            Connection c = DbConnector.connect();
            Statement s = c.createStatement();
            s.executeUpdate("Insert INTO Fishes VALUES(NULL, \"" + ter + "\", \"" + def + "\", \"" + cat + "\")");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void editFishTerm(String ter, String cat, String newTer) {
        try {
            Connection c = DbConnector.connect();
            Statement s = c.createStatement();
            s.executeUpdate("UPDATE Fishes SET term = \"" + newTer + "\" WHERE term = \"" + ter + "\" AND category = \"" + cat + "\"");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void editFishDef(String ter, String cat, String newDef) {
        try {
            Connection c = DbConnector.connect();
            Statement s = c.createStatement();
            s.executeUpdate("UPDATE Fishes SET definition = \"" + newDef + "\" WHERE term = \"" + ter + "\" AND category = \"" + cat + "\"");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static void editFishCat(String ter, String cat, String newCat) {
        try {
            Connection c = DbConnector.connect();
            Statement s = c.createStatement();
            s.executeUpdate("UPDATE Fishes SET category = \"" + newCat + "\" WHERE term = \"" + ter + "\" AND category = \"" + cat + "\"");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
