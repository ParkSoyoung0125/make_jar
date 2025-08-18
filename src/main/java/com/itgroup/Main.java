package com.itgroup;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        memberManager manager = new memberManager();

        while (true){
            System.out.println("메뉴 선택");
            System.out.println("0: 종료, 1:목록조회, 2:가입, 3:수정, 4:총 회원수, 5:탈퇴, 6:회원정보, 7:xx, 8:xx");
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
                    break;
                case 3 : // 수정
                    break;
                case 4 : // 총 회원수
                    manager.getSize();
                    break;
                case 5 : // 탈퇴
                    break;
                case 6 : // 회원정보
                    break;
                case 7 : //
                    break;
                case 8 : //
                    break;
            }

        }

    }
}