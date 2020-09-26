import java.lang.*; //including Java packages used by this program
import java.util.*;
import javax.swing.*;
import com.desai.*;

public class TransferControl
{
    String toAccount;
    String fromAccount;
    String UName;
    String Name;
    String Balance;
    String toAccountType, fromAccountType;

    public TransferControl(String ToAccount,String FromAccount,String uName, String name, String balance, String toAcc, String fromAcc)
    {
        toAccount = ToAccount;
        fromAccount = FromAccount;
        UName =  uName;
        Name = name;
        Balance = balance;
        toAccountType = toAcc;
        fromAccountType = fromAcc;
    }

    public void transfer()
    {

        if (fromAccount.equals("Choose Account Type") || toAccount.equals("Choose Account Type")) {
            JOptionPane.showMessageDialog(null, "Please choose Account type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        }

        if(fromAccountType.equals("Checking") && toAccountType.equals("Savings ")){
            CheckingAccount check = new CheckingAccount(fromAccount, Name, UName, Balance);
            Boolean withdrawstatus = check.Withdraw(check.getCANum(), Float.parseFloat(Balance));
            Boolean depositstatus = false;
            if(withdrawstatus)
            {
                SavingsAccount savacc = new SavingsAccount(toAccount, Name, UName,Balance);
                depositstatus = savacc.deposit(savacc.getSANum(), Float.parseFloat(Balance));
            }
            if (withdrawstatus && depositstatus) {
                JOptionPane.showMessageDialog(null, "Successfully Transfer From Checking To Savings!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                SavingsAccount savacc = new SavingsAccount(toAccount, Name, UName,Balance);

                Transactions TA = new Transactions("Transfer",fromAccount, toAccount, UName, Balance);
                TA.recordTransaction();
            } else {
                JOptionPane.showMessageDialog(null, "UnSuccessfully Transfer From Checking To Savings!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if(fromAccountType.equals("Savings ") && toAccountType.equals("Checking")){


            SavingsAccount savacc = new SavingsAccount(fromAccount, Name, UName, Balance);
            Boolean withdrawstatus = savacc.Withdraw(savacc.getSANum(), Float.parseFloat(Balance));
            Boolean depositstatus = false;
            if(withdrawstatus){
                CheckingAccount check = new CheckingAccount(toAccount, Name, UName, Balance);
                depositstatus = check.deposit(check.getCANum(), Float.parseFloat(Balance));
            }

            if (withdrawstatus && depositstatus) {
                JOptionPane.showMessageDialog(null, "Successfully Transfer From Savings To Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                CheckingAccount check = new CheckingAccount(toAccount, Name, UName, Balance);

                Transactions TA = new Transactions("Transfer",fromAccount, toAccount, UName, Balance);
                TA.recordTransaction();
            } else {
                JOptionPane.showMessageDialog(null, "UnSuccessfully Transfer From Savings To Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }
}