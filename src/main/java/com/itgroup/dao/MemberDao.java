package com.itgroup.dao;

import com.itgroup.bean.Member;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 데이터 베이스와 직접 연동하여 CRUD 작업을 수행해주는 DAO 클래스
public class MemberDao extends SuperDao {
    public MemberDao() {
        super();
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

    public List<Member> selectAll() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM MEMBERS ORDER BY NAME";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
//                System.out.println(rs.getString("id"));
//                System.out.println(rs.getString("name"));
//                System.out.println(rs.getString("name"));
                 Member bean = new Member();
//                 bean.setId(rs.getNString("name"));
//                 bean.setId(rs.getNString("padsword"));
                 bean.setId(rs.getString(("id")));
                 bean.setName(rs.getString(("name")));
                 bean.setPassword(rs.getString(("password")));
                 bean.setGender(rs.getString(("gender")));
                 bean.setBirth(rs.getString(("birth")));
                 bean.setMerriage(rs.getString(("marriage")));
                 bean.setSalary(rs.getInt(("salary")));
                 bean.setAddress(rs.getString(("address")));
                 bean.setManager(rs.getString(("manager")));

                 members.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }

        return members;
    }


    public List<Member> findByGender(String gender) {
        List<Member> members = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from members where gender = ?";
        Member member = null;

        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,gender);
            rs = pstmt.executeQuery();

            while(rs.next()){ // 1건 발견됨.
                member = new Member();

                member.setName(rs.getString(("name")));
                member.setPassword(rs.getString(("password")));
                member.setGender(rs.getString(("gender")));
                member.setBirth(rs.getString(("birth")));
                member.setMerriage(rs.getString(("marriage")));
                member.setSalary(rs.getInt(("salary")));
                member.setAddress(rs.getString(("address")));
                member.setManager(rs.getString(("manager")));

                members.add(member);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {rs.close();}
                if (pstmt != null) {pstmt.close();}
                if (conn != null) {conn.close();}
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return  members;
    }

    public Member getMembersOne(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member bean = null; // 찾고자하는 회원의 정보

        String sql =  "select * from members where id = ?";

        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();

            if(rs.next()){ // 1건 발견됨.
                bean = new Member();
                bean.setId(rs.getString(("id")));
                bean.setName(rs.getString(("name")));
                bean.setPassword(rs.getString(("password")));
                bean.setGender(rs.getString(("gender")));
                bean.setBirth(String.valueOf(rs.getDate(("birth"))));
                bean.setMerriage(rs.getString(("marriage")));
                bean.setSalary(rs.getInt(("salary")));
                bean.setAddress(rs.getString(("address")));
                bean.setManager(rs.getString(("manager")));
            }
        }
            catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {rs.close();}
                if (pstmt != null) {pstmt.close();}
                if (conn != null) {conn.close();}
        }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return bean;
    }

    public int insertInfo(Member bean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;

        //INSERT INTO MEMBERS VALUES ('miyeon','미연','abc1234','여자','90/12/25','미혼',200,'인천','yusin')
        String sql =  "INSERT INTO MEMBERS(id,name,password,gender,birth,marriage,salary,address,manager)";
        sql += " VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            bean = new Member();
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,bean.getId());
            pstmt.setString(2,bean.getName());
            pstmt.setString(3,bean.getPassword());
            pstmt.setString(4,bean.getGender());
            pstmt.setString(5,bean.getBirth());
            pstmt.setString(6,bean.getMerriage());
            pstmt.setInt(7,bean.getSalary());
            pstmt.setString(8,bean.getAddress());
            pstmt.setString(9,bean.getManager());

            rs = pstmt.executeUpdate();

            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try{
                conn.rollback();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }finally {
            try {
                if (pstmt != null) {pstmt.close();}
                if (conn != null) {conn.close();}
            }
            catch (Exception e){
                try{
                    conn.rollback(); // 수행 실패 시 롤백
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                e.printStackTrace();
            }
        }return rs;
    }


    public int deleteInfo(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int cnt = -1;

        String sql =  "DELETE FROM members WHERE id = ?";
        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);

            cnt = pstmt.executeUpdate();
            conn.commit(); // 성공적으로 수행 시 커밋
        } catch (Exception ex) {
            try{
                conn.rollback(); // 수행 실패 시 롤백
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex.printStackTrace();
        }finally {
            try {
                if (pstmt != null) {pstmt.close();}
                if (conn != null) {conn.close();}
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int updateInfo(Member bean) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int cnt = -1;

        String sql = "update members set name = ?, password = ?, gender = ?, birth = ?, marriage = ?, salary = ?, address = ?, manager = ?";
        sql += " where id = ?";

        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,bean.getName());
            pstmt.setString(2,bean.getPassword());
            pstmt.setString(3,bean.getGender());
            pstmt.setString(4,bean.getBirth());
            pstmt.setString(5,bean.getMerriage());
            pstmt.setInt(6,bean.getSalary());
            pstmt.setString(7,bean.getAddress());
            pstmt.setString(8,bean.getManager());
            pstmt.setString(9,bean.getId());

            cnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception ex) {
            try{
                conn.rollback(); // 수행 실패 시 롤백
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex.printStackTrace();
        }finally {
            try {
                if (pstmt != null) {pstmt.close();}
                if (conn != null) {conn.close();}
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return cnt;
    }
}
