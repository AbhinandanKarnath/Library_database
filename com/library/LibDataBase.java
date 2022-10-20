package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibDataBase {
    static Connection connection;
    public static String fetch(String libraryNumber){
        System.out.println("The Library number is "+libraryNumber);
        String query ="select Name from Student,libraryId where regId=RegisterId AND LibraryId='"+ libraryNumber +"'";
        String Name="Error";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/University","Student","student@2022");
            System.out.println("Inside connection");
            Statement statement=connection.createStatement();
            System.out.println("Statement executed successfully");
            ResultSet resultSet=statement.executeQuery(query);
          while(resultSet.next())
          Name= resultSet.getString(1);
            connection.close();
            System.out.println("query Executed successfully");
            return Name;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "Error";
    }
 }
