package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private int id;
	private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    
    
    
    public TodoItem(String title, String desc, String category, String due_date){
    	//addList 持失切
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
        this.due_date = due_date;
        SimpleDateFormat c = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = c.format(new Date());
    }
    
    public TodoItem(String category, String title, String desc, String due_date, String current_date){
        //loadList 持失切
    	this.category = category;
    	this.title = title;
        this.desc = desc;
        this.due_date = due_date;
        this.current_date = current_date;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	@Override
	public String toString() {
		return id + " " + "[" + category + "] " + title + " - " + desc + " - " + due_date + 
				" - " + current_date;
	}

	/*public String toSaveString() {
    	return category+ "##" + title + "##" + desc + "##" + due_date + "##" 
    			+ current_date + "\n";
    }*/

}
