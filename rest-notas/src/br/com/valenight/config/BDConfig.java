package br.com.valenight.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/notes_db", "root", "");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			System.out.println("SQLException");
			e.printStackTrace();
			return null;
		}
		
	}

}
