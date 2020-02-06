package org.openup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAO {
	
	static String DBDRIVER = "com.mysql.jdbc.Driver";
	static String DBPATH = "jdbc:mysql://localhost:3306/JDB";
	static String DBUSER = "root";
	static String DBPASSWORD = "simplonco";
	
	static List<Names> listName = new ArrayList<>();
	
	public List<Names> getAllName() throws ClassNotFoundException, SQLException{
		listName.removeAll(listName);
		Class.forName(DBDRIVER);
		Connection connection = DriverManager.getConnection(DBPATH, DBUSER, DBPASSWORD);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT Names.id, Names.name FROM JDB.Names");
		
		while(rs.next()) {
			long id=rs.getLong("id");
			String name = rs.getString("name");
			
			Names newName = new Names(id,name);
			listName.add(newName);
		}
		connection.close();
		return listName;
	}
	public void insert(Long id, String name) throws ClassNotFoundException, SQLException {
		Class.forName(DBDRIVER);
		Connection connection = DriverManager.getConnection(DBPATH, DBUSER, DBPASSWORD);
		Statement statement = connection.createStatement();
		
		statement.executeUpdate("insert into Names(id,name) value ('"+id+"','"+name+"');");
		connection.close();
	}
	

}
