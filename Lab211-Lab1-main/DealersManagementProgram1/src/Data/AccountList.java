/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Tools.MyTools;
import java.util.ArrayList;
import java.util.List;
import mgn.LogIn;

/**
 *
 * @author ADMIN
 */
public class AccountList extends ArrayList<Account> {

    private String dataFile = "";
    boolean changed = false;
    LogIn loginObj = null;

    public AccountList() {
    }
    
   public AccountList(LogIn loginObj) {
        this.loginObj = loginObj;
   }
    
    

    public int checkDuplicateID(String Acc) {
        for (int i = 0; i < this.size(); i++) {
            Account eachAcc = this.get(i);
            if (eachAcc.getAccname().equals(Acc)) {
                return i;
            }
        }
        return -1;
    }

    private void loadAccountFromFile() {
        List<String> lines = MyTools.readLinesFromFile(dataFile);
        for (String x : lines) {
            String[] a = x.split(",");
            Account acc = new Account(a[0], a[1], a[2]);
            this.add(acc);
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getAccountFile();
        loadAccountFromFile();
    }

    public void addAccount() {
        String accName;
        int pos = 0;
        do {
            accName = MyTools.readPattern("Account name: ", Account.AccName_FORMAT);
            accName = accName.toUpperCase();
            pos = checkDuplicateID(accName);
            if (pos >= 0) {
                System.out.println("Account name is duplicated!");
            }
        } while (pos >= 0);
        String pwdAcc = MyTools.readNonBlank("Password of new account: ");
        String roleAcc = MyTools.readNonBlank("Role of new account (BOSS, ACC-1, ACC-2): ");
        Account acc = new Account(accName, pwdAcc, roleAcc);
        this.add(acc);
        System.out.println("Add new account successfully!");
        System.out.println();
    }

    public void updateAccount() {
        String accName = MyTools.readPattern("Account name you want to update: ", Account.AccName_FORMAT);
        int pos = checkDuplicateID(accName);
        if (pos >= 0) {
            String newPwd = MyTools.readNonBlank("New account password: ");
            String newRole = MyTools.readNonBlank("New role : ");
            Account acc = new Account(accName, newPwd, newRole);
            this.add(acc);
            System.out.println("Update account successfully!");
        } else {
            System.out.println("Delivery " + accName + "not found!");
        }
    }

    public void removeAccount() {
        String accName = MyTools.readPattern("Account name you want to remove: ", Delivery.DeliveryID_FORMAT);
        int pos = checkDuplicateID(accName);
        if (pos >= 0) {
            this.remove(pos);
            System.out.println("Remove account successfully!");
        } else {
            System.out.println("Delivery " + accName + "not found!");
        }
    }

    public void printAccount() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println(this);
            System.out.println();
        }
    }

    public void writeAccountToFile() {
        if (changed) {
            MyTools.writeFile(dataFile, this);
            changed = false;
            System.out.println("Write to file successfully!");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
