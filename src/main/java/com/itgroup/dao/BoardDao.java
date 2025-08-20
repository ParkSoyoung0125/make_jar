package com.itgroup.dao;

import com.itgroup.bean.Board;
import com.itgroup.bean.Member;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends SuperDao {
    public BoardDao() {
        super();
    }


    public List<Board> selectAll() {
        // 전체 게시물을 최신 항목부터 조회하여 반환합니다.
        List<Board> boardList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM BOARDS ORDER BY NO DESC";

        try{
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){
                Board bean = this.makeBean(rs);
                boardList.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs  != null){rs.close();}
                if (pstmt  != null){pstmt.close();}
                if (conn  != null){conn.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }



        return boardList;
    }

    private Board makeBean(ResultSet rs) {
        // ResulSet에서 데이터를 읽어와서 Bean 객체에 담아 반환합니다.
        Board bean = new Board();
        try {
            bean.setNo(rs.getInt("no"));
            bean.setWriter(rs.getString("writer"));
            bean.setPassword(rs.getString("password"));
            bean.setSubject(rs.getString("subject"));
            bean.setContent(rs.getString("content"));
            bean.setReadHit(rs.getInt("readhit"));
            bean.setRegDate(rs.getString("regdate"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<Board> selectEvenData() {
        List<Board> boardList = new ArrayList<>();
        String sql = "Select * from boards where mod(no,2) = 0 order by no desc";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Board board = this.makeBean(rs);
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){rs.close();}
                if (pstmt != null){pstmt.close();}
                if (conn != null){conn.close();}
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return boardList;
    }
}
