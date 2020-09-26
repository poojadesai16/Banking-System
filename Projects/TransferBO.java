import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import com.desai.*;

class TransferPanel extends JPanel implements ActionListener
{
    private JButton Deposit, Withdraw, Transfer;
    private JTextField UsernameField, NameField,BalanceField;
    private JComboBox ToAccountField,FromAccountField;
    private String UName, AccountNumber,Balance, Name, AccountType;
    private String[] accounts;

    public TransferPanel(String UName, String CustomerName, String[] checkingAccounts, String[] SavingAccounts) {

        accounts = new String[checkingAccounts.length + SavingAccounts.length];
        int count = 0;

        UsernameField = new JTextField(15);
        UsernameField.setText(UName);
        NameField = new JTextField(CustomerName);
        ToAccountField = new JComboBox();
        ToAccountField.setFont(new Font("TimesRoman",Font.BOLD,14));
        ToAccountField.addItem("Choose Account Type");
        for(int i = 0 ; i < checkingAccounts.length; i ++){
            if(checkingAccounts[i] != null){
                ToAccountField.addItem("Checking - ****" + checkingAccounts[i].substring(4));
                accounts[count] = checkingAccounts[i];
                count++;
            }
        }
        for(int i = 0 ; i < SavingAccounts.length; i ++){
            if(SavingAccounts[i] != null){
                ToAccountField.addItem("Savings - ****" + SavingAccounts[i].substring(4));
                accounts[count] = SavingAccounts[i];
                count++;
            }
        }

        count = 0;
        FromAccountField = new JComboBox();
        FromAccountField.setFont(new Font("TimesRoman",Font.BOLD,14));
        FromAccountField.addItem("Choose Account Type");
        for(int i = 0 ; i < checkingAccounts.length; i ++){
            if(checkingAccounts[i] != null){
                FromAccountField.addItem("Checking - ****" + checkingAccounts[i].substring(4));
                accounts[count] = checkingAccounts[i];
                count++;
            }
        }
        for(int i = 0 ; i < SavingAccounts.length; i ++){
            if(SavingAccounts[i] != null){
                FromAccountField.addItem("Savings - ****" + SavingAccounts[i].substring(4));
                accounts[count] = SavingAccounts[i];
                count++;
            }
        }
        BalanceField = new JTextField(15);
        BalanceField.setText("0.0");

        Deposit = new JButton("Deposit");
        Deposit.setFont(new Font("TimesRoman",Font.BOLD,16));
        Withdraw = new JButton("Withdraw");
        Withdraw.setFont(new Font("TimesRoman",Font.BOLD,16));
        Transfer = new JButton("Transfer");
        Transfer.setFont(new Font("TimesRoman",Font.BOLD,16));

        //JLabel TypeLabel = new JLabel("Choose Account Type: ");
        JLabel NameLabel = new JLabel("Customer Name:");
        NameLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
        JLabel UsernameLabel = new JLabel("Username:");
        UsernameLabel.setFont(new Font("TimesRoman",Font.BOLD,14));
        JLabel NumberLabel1 = new JLabel(" From:");
        NumberLabel1.setFont(new Font("TimesRoman",Font.BOLD,14));
        JLabel NumberLabel2 = new JLabel(" To:");
        NumberLabel2.setFont(new Font("TimesRoman",Font.BOLD,14));
        JLabel BalanceLabel = new JLabel("Enter Amount:");
        BalanceLabel.setFont(new Font("TimesRoman",Font.BOLD,14));


        JPanel TypePanel = new JPanel();
        JPanel UsernamePanel = new JPanel();
        JPanel NamePanel = new JPanel();
        JPanel NumberPanel = new JPanel();
        JPanel BalancePanel = new JPanel();


        UsernamePanel.add(UsernameLabel);
        UsernamePanel.add(UsernameField);
        NamePanel.add(NameLabel);
        NamePanel.add(NameField);
        NumberPanel.add(NumberLabel1);
        NumberPanel.add(FromAccountField);
        NumberPanel.add(NumberLabel2);
        NumberPanel.add(ToAccountField);

        BalancePanel.add(BalanceLabel);
        BalancePanel.add(BalanceField);


        add(TypePanel);
        add(UsernamePanel);
        add(NamePanel);
        add(NumberPanel);
        add(BalancePanel);


        add(Deposit);
        add(Withdraw);//add the two buttons on to this panel
        add(Transfer);
        Deposit.addActionListener(this); //event listener registration
        Withdraw.addActionListener(this);
        Transfer.addActionListener(this);

    }



    public void actionPerformed(ActionEvent act) {

        String arg = act.getActionCommand();
        if (arg.equals("Deposit")) {
            UName = UsernameField.getText(); //take actions
            Name = NameField.getText();
            Balance = BalanceField.getText();
            AccountType = (String) ToAccountField.getSelectedItem();
            String selectedAccountType = AccountType.substring(0,8);
            System.out.println(selectedAccountType);


            if (AccountType.equals("Choose Account Type")) {
                JOptionPane.showMessageDialog(null, "Please choose Account type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (selectedAccountType.equals("Checking")) {

                String AccountNumber = accounts[ToAccountField.getSelectedIndex()-1];
                CheckingAccount checkingAdd = new CheckingAccount(AccountNumber, Name, UName, Balance);
                Boolean resultdonecheck = checkingAdd.deposit(checkingAdd.getCANum(), Float.parseFloat(Balance));

                if (resultdonecheck)
                {

                    JOptionPane.showMessageDialog(null, "Successfully Deposit Amount To Checking Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    Transactions transct = new Transactions("Deposit", "", AccountNumber, UName, Balance);
                    transct.recordTransaction();

                } else {
                    JOptionPane.showMessageDialog(null, "Unsuccessfully Deposit Amount To Checking Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (selectedAccountType.equals("Savings ")) {

                String AccountNumber = accounts[ToAccountField.getSelectedIndex()-1];
                SavingsAccount sav = new SavingsAccount(AccountNumber, Name, UName, Balance);
                Boolean resultdoneSav = sav.deposit(sav.getSANum(), Float.parseFloat(Balance));
                System.out.println("I am here");

                if (resultdoneSav) {
                    JOptionPane.showMessageDialog(null, "Successfully Deposit Amount To Savings Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    Transactions transct = new Transactions("Deposit", "", AccountNumber, UName, Balance);
                    transct.recordTransaction();
                } else {
                    JOptionPane.showMessageDialog(null, "UnSuccessfully Deposit Amount To Savings Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }
        if (arg.equals("Withdraw"))
        {
            UName = UsernameField.getText(); //take actions
            Name = NameField.getText();
            Balance = BalanceField.getText();
            AccountType = (String) FromAccountField.getSelectedItem();
            String selectedAccountType = AccountType.substring(0,8);
            System.out.println(selectedAccountType);
            if (AccountType.equals("Choose Account Type")) {
                JOptionPane.showMessageDialog(null, "Please choose Account type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else if (selectedAccountType.equals("Checking")) {

                String AccountNumber = accounts[FromAccountField.getSelectedIndex()-1];
                System.out.println(AccountNumber);
                CheckingAccount checkingAdd = new CheckingAccount(AccountNumber, Name, UName, Balance);
                Boolean resultdonecheck2 = checkingAdd.Withdraw(checkingAdd.getCANum(), Float.parseFloat(Balance));

                if (resultdonecheck2) {
                    JOptionPane.showMessageDialog(null, "Successfully Withdraw Amount From Checking Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                    Transactions transct = new Transactions("Withdraw", AccountNumber, "", UName, Balance);
                    transct.recordTransaction();
                } else {
                    JOptionPane.showMessageDialog(null, "UnSuccessfully Withdraw Amount From Checking Account! ", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                }
            } else if (selectedAccountType.equals("Savings ")) {

                String AccountNumber = accounts[FromAccountField.getSelectedIndex()-1];
                System.out.println(AccountNumber);
                SavingsAccount sav = new SavingsAccount(AccountNumber, Name, UName, Balance);
                Boolean resultdoneSav2 = sav.Withdraw(sav.getSANum(), Float.parseFloat(Balance));
                if (resultdoneSav2) {
                    JOptionPane.showMessageDialog(null, "Successfully Withdraw Amount From Savings Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                    Transactions transct = new Transactions("Withdraw", AccountNumber, "", UName, Balance);
                    transct.recordTransaction();
                } else {
                    JOptionPane.showMessageDialog(null, "UnSuccessfully Withdraw Amount From Savings Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if(arg.equals("Transfer"))
        {
            UName = UsernameField.getText(); //take actions
            Name = NameField.getText();
            Balance = BalanceField.getText();

            String fromAccount = accounts[FromAccountField.getSelectedIndex()-1];
            String toAccount = accounts[ToAccountField.getSelectedIndex()-1];

            System.out.println("From Account: " +fromAccount + " -> To Account: "+ toAccount);
            String toAccountType = (String)ToAccountField.getSelectedItem();
            toAccountType = toAccountType.substring(0, 8);

            String fromAccountType = (String)FromAccountField.getSelectedItem();
            fromAccountType = fromAccountType.substring(0, 8);

            TransferControl tfc = new TransferControl(toAccount,fromAccount,UName,Name,Balance, toAccountType, fromAccountType);
            tfc.transfer();
        }
    }
}

public class TransferBO extends JFrame
{

    private TransferPanel TDW_Panel;

    public TransferBO(String UName, String CustomerName, String[] checkingAccounts, String[] SavingAccounts) {

        setTitle("Transfer/Withdraw/Deposit Account");
        setSize(600, 300);

        //get screen size and set the location of the frame
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setLocation(screenWidth / 3, screenHeight / 4);

        addWindowListener(new WindowAdapter()  //handle window event
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Container contentPane = getContentPane(); //add a panel to a frame
        TDW_Panel = new TransferPanel(UName, CustomerName,checkingAccounts,SavingAccounts);
        contentPane.add(TDW_Panel);
        show();
    }
}

