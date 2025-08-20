package com.itgroup;

import com.itgroup.bean.Board;
import com.itgroup.bean.Member;
import com.itgroup.dao.BoardDao;
import com.itgroup.dao.MemberDao;

import java.util.List;
import java.util.Scanner;

// 메인클래스 대신 실제 모든 업무를 총 책임지는 클래스
public class DataManager {


    private BoardDao bdao = null;
    private MemberDao mdao = null; // 실제 데이터 베이스와 연동하는 mdao
    private Scanner scan = null;

    public DataManager() {
        this.mdao = new MemberDao();
        this.bdao = new BoardDao();
        this.scan = new Scanner(System.in);
    }

    public void selectAll() { // 모든 회원 정보 조회.
        List<Member> members = mdao.selectAll();
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

        int cnt = mdao.getSize();
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

        List <Member> member = mdao.findByGender(findGender);
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
        Member member = mdao.getMembersOne(findId);
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
        Member bean = new Member();
        int cnt = -1;

        System.out.println("ID를 입력해주세요. ID : ");
        String id = scan.next();

        System.out.println("이름을 입력해주세요 : 이름");
        String name = scan.next();

        bean.setId(id);
        bean.setName(name);
        bean.setPassword("abc123");
        bean.setGender("남자");
        bean.setBirth("90/12/25");
        bean.setMerriage("결혼");
        bean.setSalary(200);
        bean.setAddress("강남");
        bean.setManager("");


        cnt = mdao.insertInfo(bean);

        if (cnt == -1){
            System.out.println("회원 가입에 실패하였습니다.");
        } else if (cnt == 0) {
            System.out.println("해당 회원이 존재하지 않습니다.");
        } else if(cnt > 0) {
            System.out.println("회원 가입에 성공하였습니다.");
        }
    }

    public void deleteInfo() { // id를 이용한 탈퇴
        Scanner scan = new Scanner(System.in);
        System.out.println("탈퇴할 ID를 기입해주세요.");
        String id = scan.nextLine();
        int cnt = -1;
        cnt = mdao.deleteInfo(id);
        if (cnt == -1){
            System.out.println("회원 탈퇴에 실패하였습니다.");
        } else if (cnt == 0) {
            System.out.println("해당 회원이 존재하지 않습니다.");
        } else if(cnt > 0) {
            System.out.println("회원 탈퇴에 성공하였습니다.");
        }

    }

    public void updateData() {
        int cnt = -1;

        System.out.println("수정할 회원의 ID를 입력해주세요.");
        String findId = scan.next(); // yusin 입력

        // 여기서의 bean은 이전에 입력했던 나의 정보
        Member bean = mdao.getMembersOne(findId);

        // 편의상 내 이름과 결혼 여부를 변경
        System.out.println("이름 입력 : ");
        String name = scan.next();

        System.out.println("결혼 여부 입력 : ");
        String merriage = scan.next();

        bean.setName(name);
        bean.setManager(merriage);
        cnt = mdao.updateInfo(bean);
        if (cnt == -1){
            System.out.println("회원 정보 수정에 실패하였습니다.");
        } else if (cnt == 0) {
            System.out.println("해당 회원이 존재하지 않습니다.");
        } else if(cnt > 0) {
            System.out.println("회원 정보 수정에 성공하였습니다.");
        }
    }

    public void selectAllBoard() {
        List<Board> boards = bdao.selectAll();

        for (Board board : boards){
            String msg = "=============================\n"+
                    "글번호 : " + board.getNo() + "\n" +
                    "작성자 : " + board.getWriter() + "\n" +
                    "비밀번호 : " + board.getPassword() + "\n" +
                    "제목 : " + board.getSubject() + "\n" +
                    "내용 : " + board.getContent() + "\n" +
                    "조회수 : " + board.getReadHit() + "\n" +
                    "작성일 : " + board.getRegDate() + "\n";
            System.out.println(msg);
        }
    }

    public void selectEvenData() {
        List<Board> boards = bdao.selectEvenData();
        for (Board board : boards){
            String msg = "=============================\n"+
                    "글번호 : " + board.getNo() + "\n" +
                    "작성자 : " + board.getWriter() + "\n" +
                    "비밀번호 : " + board.getPassword() + "\n" +
                    "제목 : " + board.getSubject() + "\n" +
                    "내용 : " + board.getContent() + "\n" +
                    "조회수 : " + board.getReadHit() + "\n" +
                    "작성일 : " + board.getRegDate() + "\n";
            System.out.println(msg);
        }
    }


}
