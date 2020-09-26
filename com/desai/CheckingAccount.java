/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: October, 2013													      *
*******************************************************************************/
package com.desai;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.desai.*;

public class CheckingAccount
{   //Instance Variables
	private String CheckingAccountNumber, CustomerName, CustomerID;
	private float Balance = -1;

	public CheckingAccount(String CA_Num, String Cust_Name, String Cust_ID, String Bal)
	{ //Constructor One with three parameters
		CheckingAccountNumber = CA_Num;
		CustomerName = Cust_Name;
		CustomerID = Cust_ID;
		Balance = Float.parseFloat(Bal);
	}

	public CheckingAccount(String Cust_ID)
	{
		CustomerID=Cust_ID;
	}
	public void setCANum(String CA_Num)
	{
		CheckingAccountNumber = CA_Num;
	}
	public void setCName(String CName){
		CustomerName = CName;
	}
	public void setBalance(float Bal){
		Balance = Bal;
	}
	public float getBal()
	{
		return Balance;
	}
	public String getCANum()
	{
		return CheckingAccountNumber;
	}
	public CheckingAccount()
	{
		Balance = 0.0f;
	}

	public String getID()
	{
		return CustomerID;
	}

    public boolean openAcct()
    {
	     boolean done = false;
				try
				{
				    if (!done)
				    {
				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
				        done = !Rslt.next();
				        if (done)
				        {
						    SQL_Command = "INSERT INTO CheckingAccount(CheckingAccountNumber, CustomerName, Balance, CustomerID)"+
						                  " VALUES ('"+CheckingAccountNumber+"','"+CustomerName+"',"+Balance+", '"+CustomerID+"')"; //Save the username, password and Name
						    Stmt.executeUpdate(SQL_Command);
					    }
					    Stmt.close();
					    ToDB.closeConn();
					}
				}
			    catch(SQLException e)
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
	public float getBalance()
	{  //Method to return a CheckingAccount balance
		try
		{
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command for Balance
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

	public float getBalance(String ChkAcctNumber)
    {  //Method to return a CheckingAccount balance
		try
		{
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+ChkAcctNumber+"'"; //SQL query command for Balance
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
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return Balance;
	}
	public boolean Withdraw(String ChkAcctNumber, float amount)
		{
			boolean done = !ChkAcctNumber.equals("");
			double balance = getBalance(ChkAcctNumber);
			System.out.println("The current balance in saving account is:   " + Balance);
			if (Double.compare(balance - amount, amount) < 0)
			{
							done = false;
			}
			double newCKBalance = balance - amount;

			//System.out.println("Balance after withdraw:   " + newCKBalance);

			try
			{
				if (done)
				{
					// Have a connection to the DB
					DBConnection ToDB = new DBConnection();
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CheckingAccountNumber ='" + ChkAcctNumber + "'"; // SQL query command

					ResultSet Rslt = Stmt.executeQuery(SQL_Command);
					done = done && Rslt.next();
					if (done)
					{
						SQL_Command = "UPDATE CheckingAccount SET Balance = '"+ newCKBalance + "'  WHERE CheckingAccountNumber = '" + ChkAcctNumber + "'";
						Stmt.executeUpdate(SQL_Command);

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
		public boolean deposit(String ChkAcctNumber, float amount)
		{
			try
			{
				DBConnection ToDB = new DBConnection();
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+ ChkAcctNumber + "'"; // SQL query command for Balance
				ResultSet Rslt = Stmt.executeQuery(SQL_Command);

				while (Rslt.next())
				{
					Balance = Rslt.getFloat(1);
					System.out.println("The Current Balance in Checking Account is :  " + Balance);
					Balance = Balance + amount;
					System.out.println("Bal deposit: " + Balance);
					Statement Stmt2 = DBConn.createStatement();
					String sqlcmd = "update CheckingAccount set Balance=" + Balance + " where CheckingAccountNumber=" + ChkAcctNumber + "";
					Stmt2.executeUpdate(sqlcmd);
					System.out.println("The Current Balance in Checking Account after Depositing is:   " + Balance);
					//%f is %f
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
		public CheckingAccount getAccountInfo(String UName)
		{
			CheckingAccount CAcc = new CheckingAccount();
			try
			{		//20
				DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_Command = "SELECT * FROM CheckingAccount WHERE CustomerID ='"+UName + "'"; //SQL query command
				ResultSet Rslt = Stmt.executeQuery(SQL_Command);
				if (Rslt.next())
				{
					CAcc.setBalance(Float.parseFloat(Rslt.getString("Balance")));
					CAcc.setCANum(Rslt.getString("CheckingAccountNumber"));
					CAcc.setCName(Rslt.getString("CustomerName"));
					Stmt.close();
					ToDB.closeConn();
				}
				else
				{
					CAcc.setCANum("");
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
			return CAcc;
		}
		public boolean updateBalance(){
			boolean done = !CheckingAccountNumber.equals("") && !CustomerName.equals("") && !CustomerID.equals("");
			try {
				if(done){
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT * FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber + "';"; //SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command);
					if (Rslt.next()) {
						SQL_Command = "UPDATE CheckingAccount SET Balance='"+Balance+"';";
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

