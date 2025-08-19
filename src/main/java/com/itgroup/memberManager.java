package com.itgroup;

import com.itgroup.bean.Member;
import com.itgroup.dao.MemberDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// 메인클래스 대신 실제 모든 업무를 총 책임지는 클래스
public class memberManager {
    private MemberDao dao = null; // 실제 데이터 베이스와 연동하는 dao

    public memberManager() {
        this.dao = new MemberDao();
    }

    public void selectAll() { // 모든 회원 정보 조회.
        List<Member> members = dao.selectAll();
        for (Member member : members){
            String msg = "=============================\n"+
                    "ID : " + member.getId() + "\n" +
                    "이름 : " + member.getName() + "\n" +
                    "성별 : " + member.getGender() + "\n" +
                    "생년월일 : " + member.getBirth() + "\n" +
                    "결혼여부 : " + member.getMerriage() + "\n" +
                    "급여 : " + member.getSalary() + "\n" +
                    "주소 : " + member.getAddress() + "\n" +
                    "담당매니저 : " + member.getManager();
            System.out.println(msg);
        }
        System.out.println("=============================");
    }

    public void getSize() { // 몇 명의 회원인지 조회하는 구문.

        int cnt = dao.getSize();
        String message;
        if (cnt == 0) {
            message = "검색된 회원이 존재하지 않습니다.";
        } else {
            message = "검색된 회원은 총 " + cnt + "명 입니다.";
        }
        System.out.println(message);
    }

    public void findByGender() {
        Scanner scan = new Scanner(System.in);
        System.out.println("찾으시는 성별을 \'여자\' 또는 \'남자\'로 입력해주세요.");
        String findGender = scan.nextLine();

        List <Member> member = dao.findByGender(findGender);
        if (member == null){
            System.out.println("찾으시는 회원이 존재하지 않습니다.");
        } else{
            for (Member member1 : member){
                String memberInfo = "이름 : " + member1.getName() + "\n" +
                        "ID : " + member1.getId() + "\n" +
                        "PW : " + member1.getPassword() + "\n" +
                        "성별 : " + member1.getGender() + "\n" +
                        "생년월일 : " + member1.getBirth() + "\n" +
                        "결혼여부 : " + member1.getMerriage() + "\n" +
                        "급여 : " + member1.getSalary() + "\n" +
                        "주소 : " + member1.getAddress() + "\n" +
                        "담당매니저 : " + member1.getManager() + "\n";
                System.out.println(memberInfo);
            }
        }
    }

    public void getMemberOne() {
        Scanner scan = new Scanner(System.in);
        System.out.println("찾으시는 회원의 ID를 입력해주세요.");
        String findId = scan.nextLine();
        Member member = dao.getMembersOne(findId);
        if (member == null){
            System.out.println("찾으시는 회원이 존재하지 않습니다.");
        } else{
            String msg = "=============================\n"+
                    "ID : " + member.getId() + "\n" +
                    "이름 : " + member.getName() + "\n" +
                    "성별 : " + member.getGender() + "\n" +
                    "생년월일 : " + member.getBirth() + "\n" +
                    "결혼여부 : " + member.getMerriage() + "\n" +
                    "급여 : " + member.getSalary() + "\n" +
                    "주소 : " + member.getAddress() + "\n" +
                    "담당매니저 : " + member.getManager();
            System.out.println(msg);
        }
    }

    public void insertInfo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("가입정보를 입력해주세요\n사용할 id : ");
        String id = "";
        String name = "";
        String pw = "";
        String gender = "";
        String birth = "";
        String marriage = "";
        int salary = 0;
        String address = "";
        String mnger = "";

        Member member = new Member(id , name, pw, gender, birth, marriage, salary, address, mnger);

        id = scan.next();

        if (id.length()<5){
            System.out.println("ID가 너무 짧습니다");
        } else {
            member.setId(id);
            System.out.print("이름 : ");
            name = scan.next();

            if (name.length()<1){
                System.out.println("기입한 정보가 올바르지 않습니다.");
            } else {
                member.setName(name);
                System.out.print("비밀번호 : ");
                pw = scan.next();
                member.setPassword(pw);
                System.out.print("성별 : ");
                gender = scan.next();
                if (gender != "남자" && gender != "여자"){
                    System.out.println("정보가 올바르지 않습니다.");
                } else {
                    member.setGender(gender);
                    System.out.print("생년월일(yy/mm/dd형식으로 기입) : ");
                    birth = scan.next();
                    member.setBirth(birth);
                    System.out.print("결혼여부(결혼/미혼/이혼 중 선택하여 작성) : ");
                    marriage = scan.next();
                    if (marriage != "결혼" && marriage != "이혼" && marriage != "미혼"){
                        System.out.println("정보가 올바르지 않습니다.");
                    } else {
                        member.setMerriage(marriage);
                        System.out.print("급여 : ");
                        salary = scan.nextInt();
                        member.setSalary(salary);
                        System.out.print("거주지 : ");
                        address = scan.next();
                        member.setAddress(address);
                        System.out.print("담당자 : ");
                        mnger = scan.next();
                        member.setManager(mnger);
                    }
                }
            }
        }

        member = dao.inserInfo(id , name, pw, gender, birth, marriage, salary, address, mnger);
        if (member != null){
            System.out.println("회원가입에 성공했습니다.");
        } else{
            System.out.println("회원가입에 실패했습니다.");
        }
    }

    public void deleteInfo() { // id를 이용한 탈퇴
        Scanner scan = new Scanner(System.in);
        System.out.println("탈퇴할 ID를 기입해주세요.");
        String id = scan.nextLine();
        int cnt = -1;
        cnt = dao.deleteInfo(id);
        if (cnt == -1){
            System.out.println("회원 탈퇴에 실패하였습니다.");
        } else if (cnt == 0) {
            System.out.println("해당 회원이 존재하지 않습니다.");
        } else if(cnt > 0) {
            System.out.println("회원 탈퇴에 성공하였습니다.");
        }

    }
}
