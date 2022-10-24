package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class LibDataBase {
    private static byte[] image;
    public static Connection connection;
    protected static HashMap<String, Integer> map=new HashMap<>();
    protected static int no_of_stu_inLIb=0;

    public static String fetch(String libraryNumber){

        String query = "select Name,image from Student,libraryId,StudentImage where regId=RegisterId AND student.imageID=StudentImage.imageID AND LibraryId='"+libraryNumber+"'";
        String Name="Error";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/University","Student","student@2022");
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery(query);
            System.out.println("Query Executed");
            while(resultSet.next()){
                Name = resultSet.getString(1);
                image= resultSet.getBytes(2);
                LibraryLOG.putLog(libraryNumber);
            }

          connection.close();
          return Name;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "Error";
    }
    public static byte[] sendImage(){
        return image;
    }
 }