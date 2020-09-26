import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.table.TableColumn;
import com.desai.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class InquireTransactionControl extends JPanel
{

	private JTextField to;
	private JTextField from;
	String str1;
	String str2;
	String UserName;
	Transactions user;

	public InquireTransactionControl(String fromDate, String toDate, String UName)
	{
		str1 = toDate;
		str2 = fromDate;
		UserName = UName;
		user=new Transactions(UName);
	}

	public void searchTransaction()
	{
		Vector [] arr = user.searchTransaction(str1, str2);
		Vector columnNames = arr[0];
		Vector data = arr[1];
		if(data.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"No Transactions available", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JPanel panel = new JPanel();
			JTable table = new JTable(data, columnNames);

			table.setPreferredScrollableViewportSize(new Dimension(1000,200));
			TableColumn column;

			for (int i = 0; i < table.getColumnCount(); i++)
			{
				column = table.getColumnModel().getColumn(i);
				column.setMaxWidth(400);
			}
			JScrollPane scrollPane = new JScrollPane(table);
			panel.add(scrollPane);

			JFrame frame = new JFrame();
			frame.setTitle("Inquire Transaction Record");
			frame.add(panel);
			frame.setSize(1100, 300);
			frame.setVisible(true);
		}
	}

}