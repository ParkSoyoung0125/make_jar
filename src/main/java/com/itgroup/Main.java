package com.itgroup;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DataManager manager = new DataManager();

//        String id;
//        String name;
//        String pw
//        String gender
//        String birth, String marriage, int salary, String address, String manager
        while (true){
            System.out.println("메뉴 선택");
            System.out.println("0: 종료, 1:목록조회, 2:가입, 3:수정, 4:총 회원수, 5:탈퇴, 6:회원정보, 7:성별조회, 8:상세보기");
            System.out.println("11:게시물 전체, 12:등록, 13:수정, 14:전체건수, 15:삭제, 16:1건 정보, 17:짝수 번호만 조회");
            int menu = scan.nextInt(); // 선택한 메뉴
            switch (menu) {
                case 0 : // 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 프로그램이 종료됨을 알리고 빠져나가기.
                    break;
                case 1 : // 목록조회
                    manager.selectAll();
                    break;
                case 2 : // 가입
                    manager.insertInfo();
                    break;
                case 3 : // 수정
                    manager.updateData();
                    break;
                case 4 : // 총 회원수
                    manager.getSize();
                    break;
                case 5 : // 탈퇴
                    manager.deleteInfo();
                    break;
                case 6 : // 회원정보
                    break;
                case 7 : //
                    manager.findByGender();
                    break;
                case 8 : //
                    manager.getMemberOne();
                    break;
                case 11 : //
                    manager.selectAllBoard();
                    break;
                case 12 : //

                    break;
                case 13 : //

                    break;
                case 14 : //

                    break;
                case 15 : //

                    break;
                case 16 : //

                    break;
                case 17 : // 짝수번호만 조회
                    manager.selectEvenData();
                    break;
            }

        }

    }
}