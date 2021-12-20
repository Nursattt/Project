package com.company;

import authorization.AuthMenu;

import java.io.*;
import java.util.*;
import java.sql.*;

public class Main {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost/dataserver?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user = "root";
    private static String pass = "";
    public static void main(String[] args) {
        ProgressRun progressFrame = new ProgressRun();
        try{
            connection = DriverManager.getConnection(url,user,pass);
            //System.out.println("connection");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveUsersList(List<Item> good_item) {
        try {
            File file = new File("newFile.txt");
            if (!file.exists())
                file.createNewFile();
            PrintWriter wr = new PrintWriter(new FileWriter(file));
            for(Item item:good_item) {
                wr.write(item.getName()+" "+ item.getPrice() + " " + System.getProperty("line.separator"));
            }
            wr.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void addItem(List<Item> good_item){
        try
        {
            String query = "insert into server(name,price) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for(Item item:good_item) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setLong(2, item.getPrice());
            }
            preparedStatement.executeUpdate();
            //System.out.println("inserted successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void editItem(int index, String name, Long price){
        try
        {
            String query = "update server set name=?, price=? where id = " + index;
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, price);

            preparedStatement.executeUpdate();
            //System.out.println("Updated successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();

            String query = "select * from server";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long price = resultSet.getLong("price");

                items.add(new Item(id,name,price));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }
    public static void deleteItem(int index){
        try
        {
            Statement statement = connection.createStatement();

            String query = "delete from server where id = " +index;
            statement.executeUpdate(query);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showItem(){
        ArrayList<Item> items = getItems();
        for(Item i: items){
            System.out.println(i.toString());
        }
    }
}