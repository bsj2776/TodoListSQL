package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println("<TodolistApp의 사용법>");
        System.out.println("[add] : 항목 추가");
        System.out.println("[del] : 항목 삭제");
        System.out.println("[edit] : 항목 전체 수정");
        System.out.println("[ls] : 전체 목록");
        System.out.println("[ls_name] : 제목 순으로 정렬");
        System.out.println("[ls_name_desc] : 제목 역순으로 정렬");
        System.out.println("[ls_date] : 날짜 순으로 정렬");
        System.out.println("[ls_date_desc] : 날짜 역순으로 정렬");
        System.out.println("[find <key>] : 키워드찾기");
        System.out.println("[ls_cate] : 카테고리 확인");
        System.out.println("[find_cate <key>] : 카테고리찾기");
        System.out.println("[comp <id>] : 항목 완료하기");
        System.out.println("[ls_comp] : 완료된 항목 출력하기");
        System.out.println("[comp_edit] : 완료항목 수정");
        System.out.println("[comp_delAll] : 완료된 항목 모두 삭제");
        System.out.println("[find_meet <key>] : 약속장소 찾기");
        System.out.println("[meet_edit] : 약속장소 수정");
        System.out.println("[meet_del] : 약속장소 삭제");
        System.out.println("[pri_edit] : 중요도 수정");
        System.out.println("[pri_del] : 중요도 삭제");
        System.out.println("pri_ls <key>] : 선택한 중요도 항목 출력");
        System.out.println("[exit] : 프로그램 종료");
    }
    
    public static void prompt() {
    	System.out.println();
    	System.out.print("원하는 실행메뉴 > ");
    }
    
}
