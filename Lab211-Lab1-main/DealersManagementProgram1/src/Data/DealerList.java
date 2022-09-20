/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import mgn.LogIn;
import Tools.MyTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.transform.Source;

/**
 *
 * @author ADMIN
 */
public class DealerList extends ArrayList<Dealer> {

    private final Scanner sc = new Scanner(System.in);
    LogIn loginObj = null;
    private String dataFile = "";
    boolean changed = false;

    public DealerList() {
    }

    public DealerList(LogIn loginObj) {
        this.loginObj = loginObj;
    }

    private void loadDealerFromFile() {
        List<String> lines = MyTools.readLinesFromFile(dataFile);
        for (String x : lines) {
            String[] a = x.split(",");
            Dealer dl = new Dealer(a[0], a[1], a[2], a[3], MyTools.parseBool(a[4]));
            this.add(dl);
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList list = new DealerList();
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i).isContinuing()) {
                this.add(list.get(i));
            }
        }
        return list;
    }

    public DealerList getUnContinuingList() {
        DealerList list = new DealerList();
        //lỗi in ra là do ông để <=, thay vì <. (Index luôn bé hơn chiều dài mảng 1 đơn vị)
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isContinuing() == false) {
                this.add(list.get(i));
            }
        }
        return list;
    }

    public int checkDuplicateID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            Dealer eachDeal = this.get(i);
            if (eachDeal.getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void searchDealer() {
        String ID = "";
        ID = MyTools.readPattern("ID of dealer: ", Dealer.ID_FORMAT);
        int pos = checkDuplicateID(ID);
        if (pos >= 0) {
            System.out.println(this.get(pos));
        } else {
            System.out.println("Dealer " + ID + "not found!");
        }
    }

    public void addDealer() {
        String ID;
        int pos = 0;
        do {
            ID = MyTools.readPattern("ID of new dealer: ", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = checkDuplicateID(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        String name = MyTools.readNonBlank("Name of new dealer: ").toUpperCase();
        String addr = MyTools.readNonBlank("Address of new dealer: ");
        String phone = MyTools.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        boolean continuing = true;
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("Add new dealer successfully!");
    }

    public void removeDealer() {
        String ID;
        ID = MyTools.readPattern("ID of dealer: ", Dealer.ID_FORMAT);
        int pos = checkDuplicateID(ID);
        if (pos >= 0) {
            this.get(pos).setContinuing(false);
        } else {
            System.out.println("Dealer " + ID + "not found!");
        }
    }

    public void updateDealer() {
        String ID = MyTools.readPattern("Dealer's ID needs updating: ", Dealer.ID_FORMAT);
        int pos = checkDuplicateID(ID);
        if (pos >= 0) {
            String newName = MyTools.readNonBlank("New name of dealer: ").toUpperCase();
            String newAddr = MyTools.readNonBlank("New address of dealer: ");
            String phone = MyTools.readPattern("New phone number: ", Dealer.PHONE_FORMAT);
            Dealer a = new Dealer(ID, newName, newAddr, phone, this.get(pos).isContinuing());
            this.add(a);
        } else {
            System.out.println("Dealer " + ID + "not found!");
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {           
            //lỗi in ra là do ông để <=, thay vì <. (Index luôn bé hơn chiều dài mảng 1 đơn vị)
            //Còn nó ko in ra kết quả là do ông không có system.out.println(), mà toString nó chỉ RETURN, ko phải IN RA
            //MÀN HÌNH.
            for (int index = 0; index < this.size(); index++) { 
                Dealer eachDeal = this.get(index);
                System.out.println(eachDeal.toString());
            }
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealersToFile() {
        if (changed) {
            MyTools.writeFile(dataFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
