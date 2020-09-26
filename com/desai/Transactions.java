package com.desai;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.desai.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Vector;

public class Transactions
{   //Instance Variables
	private String TransactionNumber, TransactionType, TransactionTime, TransactionDate, FromAccount, ToAccount, CustomerID;
	private float TransactionAmount = -1;

	public Transactions(String Transact_Type, String From_Account, String To_Account, String Customer_ID, String Amount) { //Constructor One with five parameters
			TransactionType = Transact_Type;
			FromAccount = From_Account;
			ToAccount = To_Account;
			CustomerID = Customer_ID;
			TransactionAmount = Float.parseFloat(Amount);
	}
	public Transactions(String Customer_ID){
		CustomerID = Customer_ID;
		}

	  public boolean recordTransaction() {
		     boolean done = false;
					try {
					    if (!done && TransactionAmount != 0) {
							Random rand = new Random();
							TransactionNumber = String.valueOf(rand.nextInt(100000)+100000);
					        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					        Connection DBConn = ToDB.openConn();
					        Statement Stmt = DBConn.createStatement();
					        String SQL_Command = "SELECT TransactionNumber FROM Transactions WHERE TransactionNumber ='"+TransactionNumber+"'"; //SQL query command
					        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the transaction exsits.
					        done = !Rslt.next();
					        if (done) {
								Date date = new Date();
								SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
								String DateTime = formatter.format(date);
								String[] list = DateTime.split(" ");
	        					TransactionDate = list[0];
	        					TransactionTime = list[1];
							    SQL_Command = "INSERT INTO Transactions(TransactionNumber, TransactionAmount, TransactionType, TransactionTime, TransactionDate, FromAccount, ToAccount, CustomerID)"+
							                  " VALUES ('"+TransactionNumber+"','"+TransactionAmount+"', '"+TransactionType+"', '"+TransactionTime+"', '"+TransactionDate+"', '"+FromAccount+"', '"+ToAccount+"', '"+CustomerID+"')"; //save the transaction details
							    Stmt.executeUpdate(SQL_Command);
						    }
						    Stmt.close();
						    ToDB.closeConn();
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
	public Vector[] searchTransaction(String str1, String str2)
			{
				Vector[] vectors = new Vector[2];
				Vector columnNames = new Vector();
				Vector data = new Vector();

				try {
					DBConnection ToDB = new DBConnection(); // Have a connection to the
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT * FROM Transactions WHERE TransactionDate BETWEEN '"+str2+"' AND '"+str1+"' AND CustomerID ='"+CustomerID+"'";
					System.out.println("query: "+SQL_Command);
					ResultSet rs = Stmt.executeQuery(SQL_Command);
					if(null == rs)
						System.out.println("result set is null");
					ResultSetMetaData metaData = rs.getMetaData();
					int columns = metaData.getColumnCount();
					for (int i = 1; i <= columns; i++) {
						columnNames.addElement(metaData.getColumnName(i));
					}

					while (rs.next()) {
						Vector row = new Vector(columns);
						// System.out.println(columns);
						for (int i = 1; i <= columns; i++) {
							// System.out.println(i);
							row.addElement(rs.getObject(i));
						}
						data.addElement(row);
					}
					Stmt.close();
					ToDB.closeConn();
				}

				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQLException: " + e);
					while (e != null) {
						System.out.println("SQLState: " + e.getSQLState());
						System.out.println("Message: " + e.getMessage());
						System.out.println("Vendor: " + e.getErrorCode());
						e = e.getNextException();
						System.out.println("");
					}
				} catch (Exception e) {
					System.out.println("Exception: " + e);
					e.printStackTrace();
				}
				vectors[0] = columnNames;
				vectors[1] = data;
				return vectors;
				// return data;
	}
}