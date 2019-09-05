package com.context;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBContext {
    
    // connection database SQLServer 
     public Connection getConnection()throws Exception {
        String url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
     
    // information connetion of database
    private final String serverName = "localhost";
    private final String dbName = "Chat";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "12345";
}