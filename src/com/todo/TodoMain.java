package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		//TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				System.out.println("[제목 순 정렬리스트]");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("[제목 역순 정렬리스트]");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("[날짜 순 정렬리스트]");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("[날짜 역순 정렬리스트]");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "comp":
				int id_num = sc.nextInt();
				TodoUtil.completeItem(l, id_num);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;
				
			case "comp_edit":
				TodoUtil.completeEdit(l, 0);
				break;
				
			case "comp_delAll":
				TodoUtil.completeDel(l, 1);
				break;
				
			case "find_meet":
				String place = sc.nextLine().trim();
				TodoUtil.findPlaceList(l, place);
				break;
				
			case "meet_edit":
				TodoUtil.meetPlaceEdit(l);
				break;
				
			case "meet_del":
				TodoUtil.meetPlaceDel(l);
				break;
				
			case "pri_edit":
				TodoUtil.priorityEdit(l);
				break;
				
			case "pri_del":
				TodoUtil.priorityDel(l);
				break;
				
			case "pri_ls":
				String priority = sc.nextLine().trim();
				TodoUtil.findPriorityList(l, priority);
				break;
				
			case "multi_del":
				int count_del = sc.nextInt();
				TodoUtil.multiDelete(l, count_del);
				break;
				
			case "multi_comp":
				int count_comp = sc.nextInt();
				TodoUtil.multiComp(l, count_comp);
				break;

			case "exit":
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			default:
				System.out.println("원하는 실행메뉴가 존재하지 않습니다. (도움말 - help)");
				break;
			}
			
			if(isList) l.listAll(l);
		} while (!quit);
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
