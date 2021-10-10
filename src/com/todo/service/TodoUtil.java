package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import java.io.*;
import java.text.SimpleDateFormat;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Create an item]\n"
				+ "Enter the title > ");
		
		title = sc.nextLine().trim();
		if (l.isDuplicate(title)) {
			System.out.printf("There exists an item with same Title!");
			return;
		}

		System.out.print("Enter the category > ");
		category = sc.nextLine().trim();
		
		System.out.print("Enter the description > ");
		desc = sc.nextLine().trim();
		
		System.out.print("Enter the due date > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if (l.addItem(t)>0)
			System.out.println("Item has been added!\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Delete an Item]\n"
				+ "Enter the number of the item to remove > ");
		int index = sc.nextInt();
		if (l.deleteItem(index)>0)
			System.out.println("Item has been deleted.");
	}


	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[Edit an Item]\n"
				+ "Enter the number of the item you want to update > ");
		int index = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("Enter the new title of the item > ");
		new_title = sc.nextLine().trim();
		System.out.print("Enter the new category > ");
		new_category = sc.nextLine().trim();
		System.out.print("Enter the new description > ");
		new_desc = sc.nextLine().trim();
		System.out.print("Enter the new due_date > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println("Item has been updated!");
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[Total list, total %d items]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	public static void saveList(TodoList l, String filename) throws Exception {
			FileWriter fw = new FileWriter(filename);
			fw.flush();
			for (TodoItem item : l.getOrderedList("id", 1)) {
				fw.write(item.toSaveString());
			}
			fw.close();
	}
	public static void loadList(TodoList l, String filename) {
		try {
			String number, title, desc, date, category, due_date;
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String i = " ";
			while(i != null) { 
				i = br.readLine();
				StringTokenizer st = new StringTokenizer(i, "##");
				while(st.hasMoreTokens()) {
					category = st.nextToken();
					title = st.nextToken();
					desc = st.nextToken();
					due_date = st.nextToken();
					date = st.nextToken();
					TodoItem t = new TodoItem(title, desc, category, due_date); 
					t.setCurrent_date(date);
					l.addItem(t);
				}
			}
			br.close();
		} catch(Exception e) {System.out.print("todolist.txt doesn't exist");}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("Total %d number of items were found.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\ntotal %dnumber of items were found.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count=0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\ntotal %dnumber of categories are in the list.\n", count);
	}
}
