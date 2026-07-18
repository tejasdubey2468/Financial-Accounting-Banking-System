//Tejas Its a README FILE for you
// in case agar me bhool gaya about this project working
//so this is a terminal and postman both operatable banking system
//  you can operate by running the code and menu will appear and go with the flow and every single deposit and withdraw liked with the account id
// and use the http 8080 port url in postman app and use all methods for operation like  GET , POST , PUT , DELETE
//in MYSQL WORKBENCH the data base is stored
//


package com.tejas.bankingsystem;

import com.tejas.bankingsystem.entity.Account;
import com.tejas.bankingsystem.entity.Transaction;
import com.tejas.bankingsystem.service.AccountService;
import com.tejas.bankingsystem.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BankingApplication implements CommandLineRunner {

	private final AccountService accountService;
	private final TransactionService transactionService;

	public BankingApplication(AccountService accountService,
	                          TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("\n=======================================");
			System.out.println("        BANK MANAGEMENT SYSTEM");
			System.out.println("=======================================");
			System.out.println("1. Create Account");
			System.out.println("2. View All Accounts");
			System.out.println("3. Find Account By ID");
			System.out.println("4. Update Account");
			System.out.println("5. Delete Account");
			System.out.println("6. Deposit Money");
			System.out.println("7. Withdraw Money");
			System.out.println("8. Transaction History");
			System.out.println("0. Exit");
			System.out.println("=======================================");

			System.out.print("Enter Your Choice : ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

				// ================= CREATE ACCOUNT =================

				case 1:

					Account account = new Account();

					System.out.print("Enter User Name : ");
					account.setUser(scanner.nextLine());

					System.out.print("Enter Account Number : ");
					account.setAccountNumber(scanner.nextLong());
					scanner.nextLine();

					System.out.print("Enter Account Type : ");
					account.setAccountType(scanner.nextLine());

					System.out.print("Enter Balance : ");
					account.setBalance(scanner.nextDouble());
					scanner.nextLine();

					System.out.print("Enter Branch Name : ");
					account.setBranchName(scanner.nextLine());

					Account savedAccount = accountService.createAccount(account);

					System.out.println("\nAccount Created Successfully");
					System.out.println("Account ID : " + savedAccount.getAccountId());

					break;

				// ================= VIEW ALL =================

				case 2:

					List<Account> accounts = accountService.getAllAccount();

					if (accounts.isEmpty()) {

						System.out.println("\nNo Accounts Found.");

					} else {

						System.out.println("\n========== ACCOUNT LIST ==========");

						for (Account acc : accounts) {

							System.out.println("--------------------------------");
							System.out.println("Account ID      : " + acc.getAccountId());
							System.out.println("User            : " + acc.getUser());
							System.out.println("Account Number  : " + acc.getAccountNumber());
							System.out.println("Account Type    : " + acc.getAccountType());
							System.out.println("Balance         : " + acc.getBalance());
							System.out.println("Branch          : " + acc.getBranchName());

						}

					}

					break;

				// ================= FIND ACCOUNT =================

				case 3:

					System.out.print("Enter Account ID : ");
					Integer id = scanner.nextInt();

					Account found = accountService.getAccountById(id);

					if (found == null) {

						System.out.println("Account Not Found.");

					} else {

						System.out.println("\nAccount Found");
						System.out.println("--------------------------");
						System.out.println("ID      : " + found.getAccountId());
						System.out.println("User    : " + found.getUser());
						System.out.println("Balance : " + found.getBalance());

					}

					break;

				// ================= UPDATE =================

				case 4:

					System.out.print("Enter Account ID : ");
					Integer updateId = scanner.nextInt();
					scanner.nextLine();

					Account updateAccount = new Account();

					System.out.print("Enter New User Name : ");
					updateAccount.setUser(scanner.nextLine());

					System.out.print("Enter New Account Number : ");
					updateAccount.setAccountNumber(scanner.nextLong());
					scanner.nextLine();

					System.out.print("Enter New Account Type : ");
					updateAccount.setAccountType(scanner.nextLine());

					System.out.print("Enter New Balance : ");
					updateAccount.setBalance(scanner.nextDouble());
					scanner.nextLine();

					System.out.print("Enter New Branch : ");
					updateAccount.setBranchName(scanner.nextLine());

					Account updated =
							accountService.updateAccount(updateId, updateAccount);

					if (updated != null)
						System.out.println("Account Updated Successfully");
					else
						System.out.println("Account Not Found.");

					break;

				// ================= DELETE =================

				case 5:

					System.out.print("Enter Account ID : ");
					Integer deleteId = scanner.nextInt();

					accountService.deleteAccount(deleteId);

					System.out.println("Account Deleted Successfully");

					break;

				// ================= DEPOSIT =================

				case 6: {

					System.out.print("Enter Account ID : ");
					Integer accountId = scanner.nextInt();

					System.out.print("Enter Amount : ");
					double amount = scanner.nextDouble();

					transactionService.deposit(accountId, amount);

					System.out.println("Amount Deposited Successfully");

					break;
				}

				// ================= WITHDRAW =================

				case 7: {
					System.out.print("Enter Account ID : ");
					Integer accountId = scanner.nextInt();

					System.out.print("Enter Amount : ");
					double amount = scanner.nextDouble();

					transactionService.withdraw(accountId, amount);

					System.out.println("Amount Withdrawn Successfully");
					break;
				}

				// ================= HISTORY =================

				case 8:

					System.out.print("Enter Account Number : ");
					long accountNumber = scanner.nextLong();

					List<Transaction> transactions =
							transactionService.getTransactionHistory(accountNumber);

					if (transactions.isEmpty()) {

						System.out.println("\nNo Transactions Found.");

					} else {

						System.out.println("\n========== TRANSACTION HISTORY ==========");

						for (Transaction t : transactions) {

							System.out.println("-----------------------------------");
							System.out.println("Transaction ID : " + t.getTransactionId());
							System.out.println("Type           : " + t.getTransactionType());
							System.out.println("Amount         : " + t.getAmount());
							System.out.println("Date           : " + t.getDate());

						}

					}

					break;

				// ================= EXIT =================

				case 0:

					System.out.println("\nThank You For Using Bank Management System");
					scanner.close();
					return;

				default:

					System.out.println("Invalid Choice");

			}

		}

	}

}