import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.desai.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class BankingBO extends JFrame
{
    private JTabbedPane tabbedPane;
    private JPanel tabPanel_1, tabPanel_2,tabPanel_3,tabPanel_4;
    private Account acc;

    public BankingBO(String UName,String Customername, String[] checkingAccounts, String[] savingAccounts)
    {
        tabbedPane =new JTabbedPane();
        //Account Overview, Open Account, Transfer, Inquire Transactions are subtabs
        tabPanel_1 = new AccountOverviewPanel(UName, Customername);
        tabPanel_2 = new OpenBankAccountPanel(UName, Customername);
        tabPanel_3 = new TransferPanel(UName,Customername,checkingAccounts,savingAccounts);
        tabPanel_4 = new InquireTransactionPanel(UName);

        tabbedPane.addTab("Account Overview", tabPanel_1);
        tabbedPane.addTab("Open Account", tabPanel_2);
        tabbedPane.addTab("Transfer-Withdraw-Deposite", tabPanel_3);
        tabbedPane.addTab("Inquire Transactions", tabPanel_4);
        tabbedPane.setSelectedIndex(0);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBackground(new Color(83, 83, 83));
   		tabbedPane.setForeground(new Color(255, 255, 255));
        add(tabbedPane);
        setTitle("Windows Banking System MVC");
        setSize(600, 300);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        setLocation( screenWidth / 3, screenHeight / 5);
        addWindowListener (new WindowAdapter()
        {
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        });
        show();
    }
    public static void main(String [] args)
    {
        String Uname= "";
        BankingBO c=new BankingBO (Uname,"", new String[] { "" }, new String[]{ "" });
        JFrame frame = new BankingBO (Uname,"",new String[] { "" }, new String[] { "" }); // initialize a JFrame object
        frame.show();
    }
}
