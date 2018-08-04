package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
 public static Connection getConnection(){ 
Connection conn = null;
try{
conn=DriverManager.getConnection("jdbc:mysql://localhost/shop", "root", "marko98");
}catch(SQLException ex ){
}
 return conn;
 }
}