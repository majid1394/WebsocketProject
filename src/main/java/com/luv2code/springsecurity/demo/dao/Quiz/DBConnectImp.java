/*
package com.luv2code.springsecurity.demo.dao.Quiz;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectImp {
    static String URL="localhost:3306/";
    static String DATABASE_NAME="test";
    static String USERNAME="root";
    static String PASSWORD="123456";

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+URL+DATABASE_NAME,USERNAME,PASSWORD);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}*/
