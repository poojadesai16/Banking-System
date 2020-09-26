/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: February, 2014													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.desai.*;

public class LoginControl
{
    private Account Acct;

    public LoginControl(String UName, String PWord) {
		Acct = new Account(UName, PWord);
		String CustomerName = Acct.signIn();
        if (!CustomerName.equals("")) {
            //System.out.println("successful!");
            //JOptionPane.showMessageDialog(null, "Login is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            //OpenBankAccountBO OpenAcctBO = new OpenBankAccountBO(UName, CustomerName);
            String[] checkingAccountNumbers = getCheckingAccountNumbers(UName);
			String[] savingsAccountNUmbers = getSavingAccountNumbers(UName);
			//TransferBO TBA = new TransferBO(UName, CustomerName,checkingAccountNumbers,savingsAccountNUmbers);
			BankingBO c = new BankingBO(UName,CustomerName,checkingAccountNumbers,savingsAccountNUmbers);
        } else
            //System.out.println("fail!");
            JOptionPane.showMessageDialog(null, "Login failed because of invalid username or password.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	public String[] getCheckingAccountNumbers(String UName){
			String[] checkingAccountNumbers = new String[3];

			try{
				DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_command;
				SQL_command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CustomerID = '"+ UName +"' ";

				ResultSet Rst = Stmt.executeQuery(SQL_command);

				int count = 0;
				while (Rst.next()){
					if(count < 3){
						String result = Rst.getString(1);
						System.out.println(result);
						checkingAccountNumbers[count] = result;
					}
					count ++;
				}

				Stmt.close();
				DBConn.close();
			}
			catch(java.sql.SQLException e){
				System.out.println("SQLException: " + e);
				while (e != null)
				{   System.out.println("SQLState: " + e.getSQLState());
					System.out.println("Message: " + e.getMessage());
					System.out.println("Vendor: " + e.getErrorCode());
					e = e.getNextException();
					System.out.println("");
				}
			}
			catch (java.lang.Exception e)
			{
				System.out.println("Exception: " + e);
				e.printStackTrace ();
			}

			return checkingAccountNumbers;
		}

		public String[] getSavingAccountNumbers(String UName){

			String[] savingAccountNumbers = new String[3];

			try{
				DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_command;
				SQL_command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE CustomerID = '"+UName +"' ";
				ResultSet Rst = Stmt.executeQuery(SQL_command);
				int count = 0;
				while (Rst.next()){
					if(count < 3){
						String result = Rst.getString(1);
						System.out.println(result);
						savingAccountNumbers[count] = result;
					}
					count ++;
				}
				Stmt.close();
				DBConn.close();
			}
			catch(java.sql.SQLException e){
				System.out.println("SQLException: " + e);
				while (e != null)
				{   System.out.println("SQLState: " + e.getSQLState());
					System.out.println("Message: " + e.getMessage());
					System.out.println("Vendor: " + e.getErrorCode());
					e = e.getNextException();
					System.out.println("");
				}
			}
			catch (java.lang.Exception e)
			{
				System.out.println("Exception: " + e);
				e.printStackTrace ();
			}

			return savingAccountNumbers;
	}
}