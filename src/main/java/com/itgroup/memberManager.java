package com.itgroup;

import com.itgroup.dao.MemberDao;

// 메인클래스 대신 실제 모든 업무를 총 책임지는 클래스
public class memberManager {
    private MemberDao dao = null; // 실제 데이터 베이스와 연동하는 dao

    public memberManager() {
        this.dao = new MemberDao();
    }

    public void selectAll() { // 모든 회원 정보 조회.
        
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
}
