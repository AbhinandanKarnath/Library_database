package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibDataBase {
    private static byte[] image;
    static Connection connection;
    public static String fetch(String libraryNumber){
        System.out.println("The Library number is "+libraryNumber);
        //"select Name,image from Student,libraryId,StudentImage where regId=RegisterId AND LibraryId='"+ libraryNumber +"'";
        String query = "select Name,image from Student,libraryId,StudentImage where regId=RegisterId AND student.imageID=StudentImage.imageID AND LibraryId='"+libraryNumber+"'";
        String Name="Error";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/University","Student","student@2022");
            System.out.println("Inside connection");
            Statement statement=connection.createStatement();
            System.out.println("Statement executed successfully");
            ResultSet resultSet=statement.executeQuery(query);

          while(resultSet.next()){
                Name = resultSet.getString(1);
                image= resultSet.getBytes(2);
            }
          connection.close();
            System.out.println(Name);


          System.out.println("query Executed successfully");
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
 /*
          String[] Concat=new String[100];
          Concat[0]=Name;
          Concat[1]= String.valueOf(ImageByte);*/
