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

class InquireTransactionPanel extends JPanel
{
	private JTextField to, from;
	private JButton Inquire;


	public InquireTransactionPanel(final String UName)
	{
		JLabel toLabel = new JLabel("Starting Date: ");
		toLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
		to = new JTextField(20);
		to.setText("YYYY-MM-DD");


		JLabel fromLabel = new JLabel("Ending Date: ");
		fromLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
		from = new JTextField(20);
		from.setText("YYYY-MM-DD");


		Inquire = new JButton("Inquire Transaction");
		Inquire.setFont(new Font("TimesRoman",Font.BOLD,16));



		from.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				String str1=to.getText();
				String str2=from.getText();
				InquireTransactionControl e =new InquireTransactionControl(str1,str2,UName);
				e.searchTransaction();
			}
		});

		Inquire.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) {
				String str1 = to.getText();
				String str2 = from.getText();
				InquireTransactionControl e = new InquireTransactionControl(str1, str2, UName);
				e.searchTransaction();
			}
		});

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 100;
		gbc.weighty =100;

		add(toLabel, gbc, 0, 1, 1, 1);
		add(to, gbc, 1, 1, 1, 1);

		add(fromLabel, gbc, 0, 2, 1, 1);
		add(from, gbc, 1, 2, 1, 1);
		add(Inquire, gbc, 1, 3, 1, 1);
	}

	public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h)
	{
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		add(c, gbc);
	}
}