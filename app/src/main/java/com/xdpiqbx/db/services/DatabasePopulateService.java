package com.xdpiqbx.db.services;

import com.xdpiqbx.common.Helper;
import com.xdpiqbx.db.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabasePopulateService {
    private static final Database db = Database.getInstance();
    private static final Connection connection = db.getConnection();
    public static void main(String[] args) {
        populateDatabase();
    }
    private static void populate(String pathToFile){
        try {
            String sql = String.join("\n", Files.readAllLines(Paths.get(pathToFile)));
            connection.prepareStatement(sql).executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addWorker(String name, LocalDate birthday, DatabasePopulateService.Level level, int salary){
        String sql = "INSERT INTO worker (name, birthday, level, salary)\n"
                   + "  VALUES (?, ?, ?, ?)";
        PreparedStatement insert = null;
        try {
            insert = connection.prepareStatement(sql);
            insert.setString(1, name);
            insert.setString(2, birthday.toString());
            insert.setString(3, String.valueOf(level));
            insert.setInt(4, salary);
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populateDatabase(){
        String initDbFile = Helper.env("SQL_FILES_PATH") + "populate_db.sql";
        populate(initDbFile);
    }

    public enum Level{
        TRAINEE, JUNIOR, MIDDLE, SENIOR;
        @Override
        public String toString() {
            switch (this){
                case TRAINEE:
                    return "Trainee";
                case JUNIOR:
                    return "Junior";
                case MIDDLE:
                    return "Middle";
                case SENIOR:
                    return "Senior";
            }
            return "ERROR";
        }
    }
}