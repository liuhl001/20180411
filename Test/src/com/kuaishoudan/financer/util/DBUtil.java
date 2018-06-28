package com.kuaishoudan.financer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	public static final String url = "jdbc:mysql://rm-2ze7vfrx833b858d1do.mysql.rds.aliyuncs.com:3306/zhihjf_test?characterEncoding=utf8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "read_only";
	public static final String password = "Jizhicar2014";
	public static Connection conn = null;
	public PreparedStatement pst = null;

	public void closeConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection openConnection1() {
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password = null;

		try {

		//	System.out.println(url);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {

		new DBUtil().openConnection();

	}

	public static Connection openConnection() {

		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
	//		System.out.println(conn);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
