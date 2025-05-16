package com.jsp.BankingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDB_Table {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("org.postgresql.Driver");

			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banking_system",
					"postgres", "root");
//			PreparedStatement preparedStatement = connection.prepareStatement(
//					"create table accounts( account_number bigInt primary key , full_name character varying not null unique , email character varying not null unique , balance double precision not null , security_pin character varying not null check(security_pin ~ '^\\d{4}|\\d{6}$') ) ");

			PreparedStatement preparedStatement = connection.prepareStatement(
					"create table \"user\"( full_name character varying not null , email character varying primary key , password character varying not null)");

			int res = preparedStatement.executeUpdate();
			System.out.println(res);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
