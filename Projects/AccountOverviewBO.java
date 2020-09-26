import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.GridBagLayout;
import java.util.Date;
import com.desai.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;


class AccountOverviewPanel  extends JPanel implements ActionListener
{
    private String UName,Name;
    private JButton Refresh,ViewInterest;
    private JLabel CBalance, SBalance;


    public AccountOverviewPanel(String U_Name,String CustomerName)
    {
        UName=U_Name;
        Name=CustomerName;


        JLabel WelcomeLabel =new JLabel("Welcome! " +CustomerName);
        WelcomeLabel.setFont(new Font("TimesRoman",Font.BOLD,20));

        Refresh = new JButton("Refresh");
        Refresh.setFont(new Font("TimesRoman",Font.BOLD,16));
        ViewInterest=new JButton ("ViewInterest");
        ViewInterest.setFont(new Font("TimesRoman",Font.BOLD,16));

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 100;
        gbc.weighty = 1;

        JPanel WelcomePanel = new JPanel();

        WelcomePanel.add(WelcomeLabel);
        WelcomePanel.setAlignmentX(0.5f);
        add(WelcomePanel, gbc, 0, 0, 0, 1);

        JPanel CAPanel = new JPanel();
        //CAPanel.setBackground(Color.blue);

        JPanel SAPanel = new JPanel();
       // SAPanel.setBackground(Color.blue);


        CheckingAccount CA = new CheckingAccount();
        CA = CA.getAccountInfo(UName);

        SavingsAccount SA = new SavingsAccount();
        SA = SA.getAccountInfo(UName);

        if(!CA.getCANum().equals(""))
        {
            JLabel CNumberLabel = new JLabel("Account Number: ****");
            CNumberLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
            JLabel CNumber = new JLabel();
            CNumber.setText(CA.getCANum().substring(4));
            JLabel CBalanceLabel = new JLabel("Current Balance: $");
            CBalanceLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
            CBalance = new JLabel();
            CBalance.setText(String.valueOf(CA.getBal()));


            CAPanel.add(CNumberLabel);
            CAPanel.add(CNumber);
            CAPanel.add(CBalanceLabel);
            CAPanel.add(CBalance);

            TitledBorder title1 = BorderFactory.createTitledBorder("Checking Account");
            CAPanel.setBorder(title1);

            add(CAPanel, gbc, 0, 2, 0, 1);
        }

        if(!SA.getSANum().equals("")){
            JLabel SNumberLabel = new JLabel("Account Number: ****");
            SNumberLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
            JLabel SNumber = new JLabel();
            SNumber.setText(SA.getSANum().substring(4));
            JLabel SBalanceLabel = new JLabel("Current Balance: $");
            SBalanceLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
            SBalance = new JLabel();
            SBalance.setText(String.valueOf(SA.getBal()));

            SAPanel.add(SNumberLabel);
            SAPanel.add(SNumber);
            SAPanel.add(SBalanceLabel);
            SAPanel.add(SBalance);

            TitledBorder title2 = BorderFactory.createTitledBorder("Savings Account");
            SAPanel.setBorder(title2);

            add(SAPanel, gbc, 0, 3, 0, 1);


        }
        add(Refresh,gbc,0,4,1,1);
        add(ViewInterest,gbc,1,4,1,1);

        Refresh.addActionListener(this);
        ViewInterest.addActionListener(this);
    }

    public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        add(c, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();
        if(arg.equals("Refresh")) {
            CheckingAccount CA = new CheckingAccount();
            CA = CA.getAccountInfo(UName);
            CBalance.setText(String.valueOf(CA.getBal()));

            SavingsAccount SA = new SavingsAccount();
            SA = SA.getAccountInfo(UName);
            SBalance.setText(String.valueOf(SA.getBal()));
        }
        else if(arg.equals("ViewInterest"))
        {
			try
			{
				String date1 = "2020-09-24";
				Date date =new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				String DateTime = sdf.format(date);
				String[] list = DateTime.split(" ");
				String TransactionDate = list[0];
				System.out.println("The Current Date is:   "+ TransactionDate);
				 if(TransactionDate.equals(date1))
				 {
					 SavingsAccount SA = new SavingsAccount();
					 SA = SA.getAccountInfo(UName);
					 SA.calculateInterests();
					 Transactions transct = new Transactions("Interest","",SA.getSANum() , UName, String.valueOf(SA.showInterest()));
					 transct.recordTransaction();
					 JOptionPane.showMessageDialog(null, "Interest this month: $" + (String)String.valueOf(SA.showInterest()), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "UnSuccessfully! Date is not equal to Statement Date", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				 }
			 }
			catch(Exception ex){}

        }
    }
}