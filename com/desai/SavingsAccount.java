package com.desai;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.desai.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

public class SavingsAccount

{
	//Instance Variables
	private String SavingsAccountNumber, CustomerName, CustomerID;
	private float Balance, Interest;
	private Date lastCompounded = new Date();
	private float calInterest;

	/*Constructors*/
	public SavingsAccount(String SA_Num, String Cu_Name, String Cu_ID, String Bal)
	{
		SavingsAccountNumber = SA_Num;
		CustomerName = Cu_Name;
		CustomerID = Cu_ID;
		Balance =Float.parseFloat(Bal);
		//Interest = InterestRate;
		Interest = 0.012f;
	}
	//public SavingsAccount(String SA_Num)
		//{
		//	SavingsAccountNumber = SA_Num;
		//}

	public SavingsAccount(String Cust_ID)
	{
		CustomerID=Cust_ID;
		lastCompounded = new Date();
	}

	public void setSANum(String SA_Num)
	{
		SavingsAccountNumber = SA_Num;
	}

	public float getBal()
	{
		return Balance;
	}
	public String getSANum()
	{
		return SavingsAccountNumber;
	}
	public void setSName(String SName){
		CustomerName = SName;
	}

	public void setBalance(float Bal){
		Balance = Bal;
	}
	public String getID()
	{
		return CustomerID;
	}
	public float getInterest()
	{
		return Interest;
	}
	public SavingsAccount()
	{
		Balance = 0.0f;
	}

	/* method */
	public boolean openAcct()
	{
		boolean done = false;
		try {
			if (!done)
			{
				DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command
				ResultSet Rslt = Stmt.executeQuery(SQL_Command);
				//Inquire if the username exsits.
				done = !Rslt.next();
				if (done)
				{
					SQL_Command = "INSERT INTO SavingsAccount(SavingsAccountNumber, CustomerName, Balance, InterestRate, CustomerID)"+
							" VALUES ('"+SavingsAccountNumber+"','"+CustomerName+"',"+Balance+","+ Interest +", '"+CustomerID+"')"; //Save the username, password and Name
					Stmt.executeUpdate(SQL_Command);
				}
				Stmt.close();
				ToDB.closeConn();
			}
		}
		catch(SQLException e)
		{
			done = false;
			System.out.println("SQLException: " + e);
			while (e != null)
			{   System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Vendor: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		}
		catch (Exception e)
		{
			done = false;
			System.out.println("Exception: " + e);
			e.printStackTrace ();
		}
		return done;
	}
	public float getBalance()
	{  //Method to return a SavingsAccount balance
		try
		{
			DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			Connection DBConn = ToDB.openConn();
			Statement Stmt = DBConn.createStatement();
			String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command for Balance
			ResultSet Rslt = Stmt.executeQuery(SQL_Command);
			while (Rslt.next())
			{
				Balance = Rslt.getFloat(1);
			}
			Stmt.close();
			ToDB.closeConn();
		}
		catch(SQLException e)
		{
			System.out.println("SQLException: " + e);
			while (e != null)
			{   System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Vendor: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
			e.printStackTrace ();
		}
		return Balance;
	}
	public float getBalance(String SavngsAcct) {  //Method to return a SavingsAccount balance
		try
		{
			DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			Connection DBConn = ToDB.openConn();
			Statement Stmt = DBConn.createStatement();
			String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber+"'"; //SQL query command for Balance
			ResultSet Rslt = Stmt.executeQuery(SQL_Command);
			while (Rslt.next()) {
				Balance = Rslt.getFloat(1);
			}
			Stmt.close();
			ToDB.closeConn();
		}
		catch(SQLException e)
		{
			System.out.println("SQLException: " + e);
			while (e != null)
			{   System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Vendor: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
			e.printStackTrace ();
		}
		return Balance;
	}
	public boolean Withdraw(String SAcctNumber, double amount)
		{
			boolean done = !SAcctNumber.equals("");
			double balance = getBalance(SAcctNumber);
			System.out.println("The current balance in saving account is:   " + Balance);
			if (Double.compare(balance - amount, amount) < 0)
			{
				done = false;
			}
			double newSKBalance = balance - amount;
			try
			{
				if (done)
				{
					DBConnection ToDB = new DBConnection(); // Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT SavingsAccountNumber FROM SavingsAccount WHERE SavingsAccountNumber ='"+ SAcctNumber + "'"; // SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command);
					done = done && Rslt.next();
					if (done)
					{
						SQL_Command = "UPDATE SavingsAccount SET Balance = '" + newSKBalance + "'  WHERE SavingsAccountNumber = '" + SAcctNumber + "'";
						Stmt.executeUpdate(SQL_Command);
						System.out.println("The current balance in savings account after withdraw is:  " + Balance);
					}
					Stmt.close();
					ToDB.closeConn();
				}
			}
			catch (SQLException e)
			{
				System.out.println("SQLException: " + e);
				while (e != null)
				{
					System.out.println("SQLState: " + e.getSQLState());
					System.out.println("Message: " + e.getMessage());
					System.out.println("Vendor: " + e.getErrorCode());
					e = e.getNextException();
					System.out.println("");
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception: " + e);
				e.printStackTrace();
			}
			return done;
		}
		public boolean deposit(String SAcctNumber, float amount)
			{
				float newBalance;
				try
				{
					DBConnection ToDB = new DBConnection(); // Have a connection to the  DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT Balance FROM SavingsAccount WHERE SavingsAccountNumber ='" + SAcctNumber + "'"; // SQL query command for Balance
					ResultSet Rslt = Stmt.executeQuery(SQL_Command);

					while (Rslt.next())
					{
						Balance = Rslt.getFloat(1);
						System.out.println("The current balance in Saving account is:   " + Balance);
						Balance = Balance + amount;
						System.out.println("Bal deposit: " + Balance);
						Statement Stmt2 = DBConn.createStatement();
						String sqlcmd = "update SavingsAccount set Balance = " + Balance + " where SavingsAccountNumber='" + SAcctNumber + "'";
						Stmt2.executeUpdate(sqlcmd);
						System.out.println("The Current Balance in Saving Account after Depositing is:   "+ Balance);
					}
					Stmt.close();
					ToDB.closeConn();
				}
				catch (SQLException e)
				{
					System.out.println("SQLException: " + e);
					while (e != null)
					{
						System.out.println("SQLState: " + e.getSQLState());
						System.out.println("Message: " + e.getMessage());
						System.out.println("Vendor: " + e.getErrorCode());
						e = e.getNextException();
						System.out.println("");
					}
				}
				catch (Exception e)
				{
					System.out.println("Exception: " + e);
					e.printStackTrace();
				}

				return true;
	}

	public boolean calculateInterests()
				{

					try
					{
						DBConnection ToDB = new DBConnection(); // Have a connection to the  DB
						Connection DBConn = ToDB.openConn();
						Statement Stmt = DBConn.createStatement();
						String SQL_Command = "SELECT Balance FROM SavingsAccount where SavingsAccountNumber= '" + SavingsAccountNumber+"'"; // SQL query command for Balance
						ResultSet Rslt = Stmt.executeQuery(SQL_Command);

						while (Rslt.next())
						{
							float Interestrate = 0.012f;
							Balance = Rslt.getFloat(1);
							System.out.println("The current balance in Saving account is:   " + Balance);
							calInterest = Interestrate / 12 ;
							calInterest = Balance * Interestrate;
							Balance = Balance + calInterest;
							Statement Stmt2 = DBConn.createStatement();
							String sqlcmd = "update SavingsAccount set Balance = '" + Balance + "' where SavingsAccountNumber= '" + SavingsAccountNumber+"'";
							Stmt2.executeUpdate(sqlcmd);
							System.out.println("The present balance in Saving account after Interest added is:   "+ Balance);
							Stmt2.close();
						}
						Stmt.close();
						ToDB.closeConn();
					}
					catch (SQLException e)
					{
						System.out.println("SQLException: " + e);
						while (e != null)
						{
							System.out.println("SQLState: " + e.getSQLState());
							System.out.println("Message: " + e.getMessage());
							System.out.println("Vendor: " + e.getErrorCode());
							e = e.getNextException();
							System.out.println("");
						}
					}
					catch (Exception e)
					{
						System.out.println("Exception: " + e);
						e.printStackTrace();
					}

					return true;
	}
	public float showInterest()
	{
		return calInterest;
	}
	public SavingsAccount getAccountInfo(String UName)
		{
			SavingsAccount SAcc = new SavingsAccount();
			try {		//20
				DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_Command = "SELECT * FROM SavingsAccount WHERE CustomerID ='"+UName + "';"; //SQL query command
				ResultSet Rslt = Stmt.executeQuery(SQL_Command);
				if (Rslt.next()) {
					SAcc.setBalance(Float.parseFloat(Rslt.getString("Balance")));
					SAcc.setSANum(Rslt.getString("SavingsAccountNumber"));
					SAcc.setSName(Rslt.getString("CustomerName"));
					Stmt.close();
					ToDB.closeConn();
				}else{
					SAcc.setSANum("");
				}
			}
			catch(SQLException e)		//5
			{
				System.out.println("SQLException: " + e);
				while (e != null)
				{   System.out.println("SQLState: " + e.getSQLState());
					System.out.println("Message: " + e.getMessage());
					System.out.println("Vendor: " + e.getErrorCode());
					e = e.getNextException();
					System.out.println("");
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception: " + e);
				e.printStackTrace ();
			}
			return SAcc;
	}
	public boolean updateBalance(){
			boolean done = !SavingsAccountNumber.equals("") && !CustomerName.equals("") && !CustomerID.equals("");
			try {
				if(done){
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT * FROM SavingsAccount WHERE SavingsAccountNumber ='"+SavingsAccountNumber + "'"; //SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command);
					if (Rslt.next()) {
						SQL_Command = "UPDATE SavingsAccount SET Balance='"+Balance+"';";
						Stmt.executeUpdate(SQL_Command);
						Stmt.close();
						ToDB.closeConn();
					}
				}
			}
			catch(java.sql.SQLException e)
			{         done = false;
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
			{         done = false;
				System.out.println("Exception: " + e);
				e.printStackTrace ();
			}
			return done;
	}
}




















