package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DB {

	private Connection cnx;
	private ResultSet rs;
	private PreparedStatement pstm;
	private int ok;

	private void getConnexion() {
		String url = "jdbc:mysql://localhost:3306/election";
		String user = "root";
		String password = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnx = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void initPrepar(String sql) {
		try {
			getConnexion();
			pstm = cnx.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int executeMaj() {
		ok = 0;
		try {
			ok = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
	public ResultSet executeSelect() {
		try {
			rs = pstm.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public PreparedStatement getPstm() {
		return this.pstm;
	}
}
