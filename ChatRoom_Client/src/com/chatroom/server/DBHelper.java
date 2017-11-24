package com.chatroom.server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBHelper {
    public static final String url = "jdbc:mysql://127.0.0.1/chatroom";
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "1234";
  
    public Connection conn = null;

    public DBHelper() {

    }  


    public void openDB(){
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
//            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {  
        try {  
            this.conn.close();  
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }

    public PreparedStatement execSql(String sql){

        if(conn!=null){
            PreparedStatement  pst = null;
            try {
                pst = conn.prepareStatement(sql);//准备执行语句
                return pst;
            } catch (SQLException e) {
                e.printStackTrace();
                return pst;
            }
        }else {
            return null;
        }
    }
}  