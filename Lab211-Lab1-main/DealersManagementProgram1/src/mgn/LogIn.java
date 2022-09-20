/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgn;

import Data.Account;
import Data.AccountChecker;
import Data.AccountList;
import Data.DealerList;
import Data.DeliveryNotes;
import Tools.MyTools;

/**
 *
 * @author ADMIN
 */
public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String accName = MyTools.readPattern("Enter account name: ", "E\\d{3}");
        String pwd = MyTools.readNonBlank("Enter password: ");
        String role = MyTools.readNonBlank("Enter role (BOSS/ACC-1/ACC-2): ");
        Account eachAccount = new Account(accName, pwd, role);
        return eachAccount;
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont = false;
        boolean valid = false;
        do {
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTools.readBool("Invalid account - Try again?");
            }
            else if (!valid && !cont) {
                System.exit(0);
            //Bug login nằm ở chỗ này nè ông. Lý do là trong 2 điều kiện if (!valid), (!valid && !cont), thì KO CÓ cái nào
            //là cho phép mình tiếp tục cả. (cái Invalid là bắt nhập lại, cái System.exit(0) là thoát app), chứ ko 
            //có cái nào là liên quan đến tiếp tục sử dụng app. Nên tui thêm cái else break để cho nó thoát ra.
            }else break;
        } while (cont);

        LogIn loginObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {

            String[] options = {"Add new dealer", "Search a dealer", "Remove a dealer", "Update a dealer", "Print all dealers",
                "Print continuing dealers", "Print UN-continuing dealers", "Write to file and return to account menu", "Exit"};
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(loginObj);
            dList.initWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice("Managing dealers");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealersToFile();
                        inputAccount();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = MyTools.readBool("Data is changed. Write to file?");
                            if (res == true) {
                                dList.writeDealersToFile();
                                System.exit(0);
                            } else {
                                System.exit(0);
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Bye");
        } else if (acc.getRole().equalsIgnoreCase("ACC-2")) {     
            String[] options = {"Add new delivery", "Print all deliveries", "Update delivery", "Remove Delivery", "Write to file and return to account menu", "Exit"};
            Menu mnu = new Menu(options);
            DeliveryNotes dN = new DeliveryNotes(loginObj);
            dN.initWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice("Managing deliveries");
                switch (choice) {
                    case 1:
                        dN.addDealivery();
                        break;
                    case 2:
                        dN.printDealivery();
                        break;
                    case 3:
                        dN.updateDelivery();
                        break;
                    case 4:
                        dN.removeDelivery();
                        break;
                    case 5:
                        dN.writeDealiveryToFile();
                        inputAccount();
                        break;
                    default:
                        if (dN.isChanged()) {
                            boolean res = MyTools.readBool("Data is changed. Write to file?");
                            if (res == true) {
                                dN.writeDealiveryToFile();
                                System.exit(0);
                            }
                        } else {
                            System.exit(0);
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Bye");
        }
        else if(acc.getRole().equalsIgnoreCase("BOSS")){
            String[] options = {"Add new account", "Print all account", "Update account", "Remove account", "Write to file and return to account menu", "Exit"};
            Menu mnu = new Menu(options);
            AccountList accList = new AccountList(loginObj);
            accList.initWithFile();
            int choice = 0;
            do {
                
                choice = mnu.getChoice("Managing deliveries");
                switch (choice) {
                    case 1:
                        accList.addAccount();
                        break;
                    case 2:
                        accList.printAccount();
                        break;
                    case 3:
                        accList.updateAccount();
                        break;
                    case 4:
                        accList.removeAccount();
                        break;
                    case 5:
                        accList.writeAccountToFile();
                        inputAccount();
                        break;
                    default:
                        if (accList.isChanged()) {
                            boolean res = MyTools.readBool("Data is changed. Write to file?");
                            if (res == true) {
                                accList.writeAccountToFile();
                            }
                            else{
                                System.exit(0);
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Bye");
        }
    }
}
