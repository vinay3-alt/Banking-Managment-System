package com.jsp.BankingSystem;

//	gsp2886@gmail.com
//	Akhil123@gmail.com
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

	private static final String url = "jdbc:postgresql://localhost:5432/banking_system";
	private static final String usn = "postgres";
	private static final String pwd = "root";

	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection(url, usn, pwd);
			Scanner scanner = new Scanner(System.in);
			User user = new User(connection, scanner);
			Accounts accounts = new Accounts(connection, scanner);
			Account_Manager account_Manager = new Account_Manager(connection, scanner);

			String email; 
			long account_number;

			while (true) {
				System.out.println("****Welcome To The Banking System****");
				System.out.println();

				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.println("Enter Your Choice");
				int choice1 = scanner.nextInt();

				switch (choice1) {
				case 1:
					user.register();
//					System.out.print("\033[H\033[2J");
//					System.out.flush();

					break;
				case 2:
					email = user.login();
					if (email != null) {
						System.out.println();
						System.out.println("User Logged In!");

						if (!accounts.account_exist(email)) {
							System.out.println();
							System.out.println("1. Open a new Bank Accounf");
							System.out.println("2. Exit");

							if (scanner.nextInt() == 1) {
								account_number = accounts.open_account(email);
								System.out.println("Account Created Successfully");
								System.out.println("Your Account Number is :" + account_number);
							} else {
								break;
							}

						}

						account_number = accounts.getAccount_Number(email);
						int choice2 = 0;
						while (choice2 != 5) {
							System.out.println();
							System.out.println("1. Debit Money");
							System.out.println("2. Credit Money");
							System.out.println("3. Transfer Money");
							System.out.println("4. Check Balance");
							System.out.println("5. Log Out");
							System.out.println("Enter your choice :");
							choice2 = scanner.nextInt();

							switch (choice2) {
							case 1:
								account_Manager.debit_money(account_number);
								break;
							case 2:
								account_Manager.credit_money(account_number);
								break;
							case 3:
								account_Manager.transfer_money(account_number);
								break;
							case 4:
								account_Manager.getBalance(account_number);
								break;
							case 5:
								break;

							default:
								System.out.println("Enter Valid Choice!");
								break;
							}

						}

					} else {
						System.out.println("Incorrect Email or Password");
					}
				case 3:
					System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
					System.out.println("Existing System");
					return;

				default:
					System.out.println("Enter valid choice");
					
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
