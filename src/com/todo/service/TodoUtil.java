package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� �߰�]" + "\n"
				+ "[������ �Է��ϼ���] > ");
		title = sc.nextLine().trim();
		
		if (l.isDuplicate(title)) {
			System.out.println("[ERROR] ������ �ߺ��˴ϴ�!");
			return;
		}
	
		System.out.print("[ī�װ��� �Է��ϼ���] > ");
		category = sc.nextLine().trim();
		
		System.out.print("[������ �Է��ϼ���] > ");
		desc = sc.nextLine().trim();
		
		System.out.print("[�������ڸ� �Է��ϼ���] > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date, 0);
		if(l.addItem(t) > 0)
			System.out.println("�߰��Ǿ����ϴ�!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[�׸� ����]\n");
		System.out.print("[�����ϰ� ���� �׸��� ��ȣ�� �Է��ϼ���] > ");
		
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0)
			System.out.println("�����Ǿ����ϴ�!");
	}
	
	public static void completeDel(TodoList l,int comp_num) {
		if(l.completeDel(comp_num) > 0) {
			System.out.println("�Ϸ� üũ �Ͽ����ϴ�!");
		}
	}


	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		int new_is_completed;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "[�����ϰ� ���� �׸��� ��ȣ�� �Է��ϼ���] > ");
		int index = sc.nextInt();
		
		sc.nextLine(); //enter
		
		System.out.print("[�� ������ �Է��ϼ���] > ");
		new_title = sc.nextLine().trim();
		System.out.print("[�� ī�װ��� �Է��ϼ���] > ");
		new_category = sc.nextLine().trim();
		System.out.print("[�� ������ �Է��ϼ���] > ");
		new_desc = sc.nextLine().trim();
		System.out.print("[�� �������ڸ� �Է��ϼ���] > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("[�ϷῩ�θ� �Է��ϼ���(�Ϸ� = 1, �̿Ϸ� = 0)] > ");
		new_is_completed = sc.nextInt();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_is_completed);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("�����Ǿ����ϴ�!");
	}

	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	//ls_comp ����
	public static void listAll(TodoList l, int num) {
		System.out.printf("�� %d���� �׸��� �Ϸ�Ǿ����ϴ�.\n", l.getCount(num));
		for(TodoItem item : l.getList(num)) {
			System.out.println(item.toString());
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item+" ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	//comp����
	public static void completeItem(TodoList l ,int id_num) {
		int index = id_num;
		if(l.completedItem(index) > 0) {
			System.out.println("�Ϸ� üũ �Ͽ����ϴ�!");
		}
	}
	//����������(todolist.txt)����/�б� ��� - ���α׷� ���� �� �б� & ���� �� ����
	/*public static void saveList(TodoList l, String filename) {
		//FileWriter ���
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("<������ ����Ǿ����ϴ�>");
			w.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		//BufferedReader, FlieReader, StringTokenizer ���
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			int count = 0;
			String oneline;
			while((oneline = br.readLine()) != null) {
				String[] list = oneline.split("##");
				String category = list[0];
				String title = list[1];
				String description = list[2];
				String due_date = list[3];
				String current_date = list[4];
				
				TodoItem item = new TodoItem(category, title, description, due_date, current_date);	
				l.addItem(item);
				count++;
			}	
			br.close();
			System.out.println("<" + count + "���� �׸��� �о����ϴ�>");
		} catch (FileNotFoundException e) {
			System.out.println("<����� ������ �����ϴ�.>");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
