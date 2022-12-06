package com.xdpiqbx.db.services;

import com.xdpiqbx.common.Helper;
import com.xdpiqbx.db.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        populateDatabase();
    }
    private static void populate(Database db, String pathToFile){
        try {
            String sql = String.join("\n", Files.readAllLines(Paths.get(pathToFile)));
            db.executeUpdate(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populateDatabase(){
        String initDbFile = Helper.env("SQL_FILES_PATH") + "populate_db.sql";
        populate(Database.getInstance(), initDbFile);
    }
}