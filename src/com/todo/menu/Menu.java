package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println("<TodolistApp�� ����>");
        System.out.println("[add] : �׸� �߰�");
        System.out.println("[del] : �׸� ����");
        System.out.println("[edit] : �׸� ��ü ����");
        System.out.println("[ls] : ��ü ���");
        System.out.println("[ls_name] : ���� ������ ����");
        System.out.println("[ls_name_desc] : ���� �������� ����");
        System.out.println("[ls_date] : ��¥ ������ ����");
        System.out.println("[ls_date_desc] : ��¥ �������� ����");
        System.out.println("[find <key>] : Ű����ã��");
        System.out.println("[ls_cate] : ī�װ� Ȯ��");
        System.out.println("[find_cate <key>] : ī�װ�ã��");
        System.out.println("[comp <id>] : �׸� �Ϸ��ϱ�");
        System.out.println("[ls_comp] : �Ϸ�� �׸� ����ϱ�");
        System.out.println("[comp_edit] : �Ϸ��׸� ����");
        System.out.println("[comp_delAll] : �Ϸ�� �׸� ��� ����");
        System.out.println("[find_meet <key>] : ������ ã��");
        System.out.println("[meet_edit] : ������ ����");
        System.out.println("[meet_del] : ������ ����");
        System.out.println("[pri_edit] : �߿䵵 ����");
        System.out.println("[pri_del] : �߿䵵 ����");
        System.out.println("pri_ls <key>] : ������ �߿䵵 �׸� ���");
        System.out.println("[exit] : ���α׷� ����");
    }
    
    public static void prompt() {
    	System.out.println();
    	System.out.print("���ϴ� ����޴� > ");
    }
    
}
