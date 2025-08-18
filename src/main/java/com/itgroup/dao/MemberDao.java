package com.itgroup.dao;

import java.sql.*;

// 데이터 베이스와 직접 연동하여 CRUD 작업을 수행해주는 DAO 클래스
public class MemberDao {
    public MemberDao() {
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


    public int getSize() {
        String sql = "SELECT COUNT(*) AS CNT FROM MEMBERS";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        int cnt = 0; // 검색된 회원 명수
        try {
            conn = this.getConnection(); // 접속 객체 구하기
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()){ // 결과값이 있으면 true 반환, 결과값이 없으면 false 반환
                cnt = rs.getInt("CNT"); // sql문에서 CNT로 명명한 COUNT(*) = 총 회원수
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null){ rs.close();}
                if (pstmt != null){ pstmt.close();}
                if (conn != null){ conn.close();}
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return cnt;
    }
}
