package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	Connection conn;
	
	public TodoList() {
		this.conn = DbConnect.getConnection();
	}
	
	private List<TodoItem> list;

	/*public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}*/

	public int addItem(TodoItem t) {
		String sql = "insert into list(title, memo, category, current_date, due_date, meet_place, priority)"
				+" values (?,?,?,?,?,?,?);";
		PreparedStatement  pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setString(6,t.getMeet_place());
			pstmt.setString(7,t.getPriority());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,index);
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int completeDel(int comp_num) {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "delete from list where is_completed = 1";
			count = stmt.executeUpdate(sql);
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=?, is_completed=?, meet_place=?, priority=?"
				+ " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setInt(6,t.getIs_completed());
			pstmt.setString(7,t.getMeet_place());
			pstmt.setString(8,t.getPriority());
			pstmt.setInt(9,t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//completeItem구현 - TodoUtil (comp)
	public int completedItem(int num) {
		String sql = "update list set is_completed=?" + " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, num);
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//is_completed 수정
	public int completeEdit(int id_num) {
		String sql = "update list set is_completed = 0" + " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id_num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//meet_place 수정
	public int meetPlaceEdit(int id_num, String place) {
		String sql = "update list set meet_place = ?" + " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place);
			pstmt.setInt(2, id_num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int meetPlaceDel(int id_num) {
		String sql = "update list set meet_place = ?" + " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "약속장소X");
			pstmt.setInt(2, id_num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int priorityEdit(int id_num, String pri) {
		String sql = "update list set priority = ?" + " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pri);
			pstmt.setInt(2, id_num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int priorityDel(int id_num) {
		String sql = "update list set priority = ?" + " where id = ?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "@@");
			pstmt.setInt(2, id_num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem>getListPriority(String pri){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE priority = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pri);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem>getList(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//getList구현 - TodoUtil(ListAll)
	public ArrayList<TodoItem>getList(int num){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE is_completed like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//ls_comp에서 출력 갯수를 세기위한 getCount구현
	public int getCount(int num) {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list WHERE is_completed = 1";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem>getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem>getListMeetPlace(String place){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE meet_place = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY "+ orderby;
			if(ordering == 0) {
				sql += " DESC";
			}
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String meet_place = rs.getString("meet_place");
				String priority = rs.getString("priority");
				TodoItem t = new TodoItem(title, description, category, due_date, current_date, is_completed, meet_place, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void listAll(TodoList l) {
		System.out.println("[전체 목록, " + "총 "+ l.getList().size() + "개]");
		int num = 1;
		for (TodoItem item : l.getList()) {
			System.out.print(num + ". ");
			System.out.println(item.toString());
			num++;
		}
	}
	
	public Boolean isDuplicate(String title) {
		int result = 0;
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if (title.equals(rs.getString("title"))) 
					result = 1;
				else
					result = 0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(result == 1)
			return true;
		else
			return false;
	}
	
	/*public void sortByName() {
		Collections.sort(list, new TodoSortByName());
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}*/
	
	/*public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader (new FileReader(filename));
			String line;
			String sql = "insert into list(title, memo, category, current_date, due_date)"
					+" values (?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine())!= null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, description);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				int count = pstmt.executeUpdate();
				if(count > 0) records++;
				pstmt.close();
			}
			System.out.println(records + "records read!");
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
