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
    private int is_completed;
    private String meet_place;
    private String priority;
    
    
    
    public TodoItem(String title, String desc, String category, String due_date, int is_completed
    		, String meet_place, String priority){
    	//addList »ý¼ºÀÚ
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
        this.due_date = due_date;
        SimpleDateFormat c = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = c.format(new Date());
        this.is_completed = is_completed;
        this.meet_place = meet_place;
        this.priority = priority;
    }
    
    public TodoItem(String title, String desc, String category, String due_date, String current_date, int is_completed
    		, String meet_place, String priority){
    	this.title = title;
    	this.desc = desc;
    	this.category = category;
        this.due_date = due_date;
        this.current_date = current_date;
        this.is_completed = is_completed;
        this.meet_place = meet_place;
        this.priority = priority;
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

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
	
	public String getMeet_place() {
		return meet_place;
	}

	public void setMeet_place(String meet_place) {
		this.meet_place = meet_place;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		if(is_completed == 1) {
			return id + " " + "[" + category + "] " + title +  " [V] " + " - " + desc + " - " 
					+ meet_place + " - " + priority + " - " + due_date + " - " + current_date;
		}else
			return id + " " + "[" + category + "] " + title  + " - " + desc + " - " 
					+ meet_place + " - " + priority + " - " + due_date + " - " + current_date;
	}

	/*public String toSaveString() {
    	return category+ "##" + title + "##" + desc + "##" + due_date + "##" 
    			+ current_date + "\n";
    }*/

}
