package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
        System.out.println("<Commands for ToDoList>");
        System.out.println("add                   Adds a new item");
        System.out.println("del                   Deletes an existing item");
        System.out.println("edit                  Updates an item"); 
        System.out.println("ls                    Lists all items");
        System.out.println("ls_name_asc           Sorts the list by name in ascending order");
        System.out.println("ls_name_desc          Sorts the list by name in descending order");
        System.out.println("ls_date               Sorts the list by date");
        System.out.println("ls_date_desc          Sorts the list by date in descending order");
        System.out.println("help                  Shows the list of commands");
        System.out.println("find <keyword>        Shows the items that contains the keyword in the title or description");
        System.out.println("find_cate <keyword>   Shows the items that contains the keyword in the category");
        System.out.println("exit                  Exit command");
        System.out.println();
    }
    public static void prompt() {
    	System.out.print("\nEnter a command > ");
    }
}
