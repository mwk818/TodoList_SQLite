package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			System.out.println();
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
				try {
					TodoUtil.listAll(l, "id", 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "ls_name":
				System.out.println("[Listing items in order of each title]");
				TodoUtil.listAll(l, "title", 1);
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("[Listing items in descending order of each name]");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("[Listing items in order of each date]");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("[Listing items in order of each date in descending order]");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;

			case "exit":
				quit = true;
				System.out.println("Bye bye~!!");
				break;
			
			case "help":
				Menu.displaymenu();
				break;
			
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
			
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;

			default:
				System.out.println("Please enter the right command!!");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	} 
}
