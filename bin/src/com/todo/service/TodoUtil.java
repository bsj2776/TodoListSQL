package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[�׸� �߰�]"
				+ "[������ �Է��ϼ���] > \n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("(�� �׸��� �̹� ��Ͽ� �����մϴ�.)");
			return;
		}
		
		System.out.println("[������ �Է��ϼ���] > ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[�׸� ����]\n");
		String title = sc.next();
		
		System.out.println("[�����ϰ� ���� �׸��� ������ �Է��ϼ���] > \n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[�׸� ����]\n"
				+ "[�����ϰ� ���� �׸��� ������ �Է��ϼ���] > \n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("(�� �׸��� �������� �ʽ��ϴ�)");
			return;
		}

		System.out.println("[�߰��ϰ� ���� �׸��� ������ �Է��ϼ���] > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("(�� �׸��� �̹� ��Ͽ� �����մϴ�.)");
			return;
		}
		
		System.out.println("[������ �Է��ϼ���] > ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("(�׸��� �����Ǿ����ϴ�)");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("[����] : " + item.getTitle() + "  [����] :  " + item.getDesc());
		}
	}
}
