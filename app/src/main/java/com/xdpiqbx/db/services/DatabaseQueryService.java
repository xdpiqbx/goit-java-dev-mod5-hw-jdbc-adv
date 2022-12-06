package com.xdpiqbx.db.services;

import com.xdpiqbx.common.Helper;
import com.xdpiqbx.db.DataModels.*;
import com.xdpiqbx.db.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final Database db = Database.getInstance();
    private static final String path = Helper.env("SQL_FILES_PATH");
    public static void main(String[] args) {
        printResult();
    }
    public List<MaxProjectCountClient> maxProjectCountClient(){
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sqlQueryFromFile("find_max_projects_client"));
            List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
            while(rs.next()){
                maxProjectCountClients.add(
                    new MaxProjectCountClient(
                        rs.getString("name"),
                        rs.getInt("project_count")
                    ));
            }
            rs.close();
            st.close();
            return maxProjectCountClients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LongestProject> longestProject(){
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sqlQueryFromFile("find_longest_project"));
            List<LongestProject> longestProjects = new ArrayList<>();
            while(rs.next()){
                longestProjects.add(
                    new LongestProject(
                        rs.getString("name"),
                        rs.getInt("month_count")
                    ));
            }
            rs.close();
            st.close();
            return longestProjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<MaxSalaryWorker> maxSalaryWorker(){
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sqlQueryFromFile("find_max_salary_worker"));
            List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();
            while(rs.next()){
                maxSalaryWorkers.add(
                    new MaxSalaryWorker(
                        rs.getString("name"),
                        rs.getInt("salary")
                    ));
            }
            rs.close();
            st.close();
            return maxSalaryWorkers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<YoungestEldestWorker> youngestAndEldestWorkers(){
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sqlQueryFromFile("find_youngest_eldest_workers"));
            List<YoungestEldestWorker> youngestEldestWorkers = new ArrayList<>();
            while(rs.next()){
                youngestEldestWorkers.add(
                        new YoungestEldestWorker(
                                rs.getString("type"),
                                rs.getString("name"),
                                rs.getString("birthday")
                        ));
            }
            rs.close();
            st.close();
            return youngestEldestWorkers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ProjectPrice> projectPrice(){
        try {
            Statement st = db.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sqlQueryFromFile("print_project_prices"));
            List<ProjectPrice> projectPrices = new ArrayList<>();
            while(rs.next()){
                projectPrices.add(
                    new ProjectPrice(
                        rs.getString("name"),
                        rs.getInt("price")
                    ));
            }
            rs.close();
            st.close();
            return projectPrices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static String sqlQueryFromFile(String fileName){
        try {
            return String.join("\n", Files.readAllLines(Paths.get(path+fileName+".sql")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printResult(){
        System.out.println("\nList<MaxProjectCountClient> maxProjectCountClients");
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().maxProjectCountClient();
        for (MaxProjectCountClient el: maxProjectCountClients) {
            System.out.println(el);
        }

        System.out.println("\nList<LongestProject> longestProjects");
        List<LongestProject> longestProjects = new DatabaseQueryService().longestProject();
        for (LongestProject el: longestProjects) {
            System.out.println(el);
        }

        System.out.println("\nList<MaxSalaryWorker> maxSalaryWorkers");
        List<MaxSalaryWorker> maxSalaryWorkers = new DatabaseQueryService().maxSalaryWorker();
        for (MaxSalaryWorker el: maxSalaryWorkers) {
            System.out.println(el);
        }

        System.out.println("\nList<YoungestEldestWorker> youngestEldestWorkers");
        List<YoungestEldestWorker> youngestEldestWorkers = new DatabaseQueryService().youngestAndEldestWorkers();
        for (YoungestEldestWorker el: youngestEldestWorkers) {
            System.out.println(el);
        }

        System.out.println("\nList<ProjectPrice> projectPrices");
        List<ProjectPrice> projectPrices = new DatabaseQueryService().projectPrice();
        for (ProjectPrice el: projectPrices) {
            System.out.println(el);
        }
    }
}