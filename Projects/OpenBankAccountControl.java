import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import com.desai.*;
import java.util.*;


public class OpenBankAccountControl
{
    public OpenBankAccountControl(String AcountType, String  AcountNumber, String  Name, String  UName, String  Balance)
    {
        Transactions tr;
        if (AcountType.equals("Checking"))
        {
			//Use CheckingAccount object to invoke method openAcct()
            CheckingAccount CA = new CheckingAccount(AcountNumber, Name, UName, Balance);
            if (CA.openAcct())
            {

                tr = new Transactions("Deposit", "", AcountNumber, UName, Balance);
                if(tr.recordTransaction())
                {
                    System.out.println("successful!");
                    JOptionPane.showMessageDialog(null, "Opening a Checking Account is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
                else

                    JOptionPane.showMessageDialog(null, "Opening a Checking Account failed.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(AcountType.equals("Savings"))
        {

            SavingsAccount SA = new SavingsAccount(AcountNumber, Name, UName, Balance);
            if (SA.openAcct())
            {

                tr = new Transactions("Deposit", "", AcountNumber, UName, Balance);// Add TransactionTime
                if(tr.recordTransaction())
                {
                    System.out.println("successful!");
                    JOptionPane.showMessageDialog(null, "Opening a Savings Account is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                }
                else

                JOptionPane.showMessageDialog(null, "Opening a Savings Account failed.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}

        }
    }
}