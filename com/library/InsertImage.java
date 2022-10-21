package com.library;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertImage {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/University","UniversityAdmin","AdminOfUniversity");
            PreparedStatement preparedStatement=con.prepareStatement("insert into StudentImage values (?,?)");
            System.out.println("prepare statement initilized ");
            preparedStatement.setString(1,"Img@1");
            System.out.println("The first string has setted succesfully");
            InputStream in = new FileInputStream("E:\\PassportPhoto\\3.jpg");
            preparedStatement.setBlob(2,in);
            preparedStatement.execute();
            con.close();
        }
        catch (Exception e){

            System.out.println(e);
        }
    }
}
