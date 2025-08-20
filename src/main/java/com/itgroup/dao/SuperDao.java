package com.itgroup.dao;

import com.itgroup.bean.Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperDao {
    public SuperDao() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            System.out.println("해당 드라이브가 존재하지 않습니다.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn  = null; // 접속객체
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "oraman";
        String password = "ORACLE";
        try {
            conn = DriverManager.getConnection(url,id,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
