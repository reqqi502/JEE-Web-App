package com.metier;

import java.sql.DriverManager;
import java.sql.Connection;


public class SingletonConnection {
	
	private static Connection connection;
	
	static {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/products?useSSL=false","root","");
	} catch (Exception e) {
	
		e.printStackTrace();
	}	
	}
	
	private SingletonConnection() {}
	
	public static Connection getInstance() {
		return connection;
	}

}
