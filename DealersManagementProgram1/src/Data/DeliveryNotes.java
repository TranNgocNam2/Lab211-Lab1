package Data;

import Tools.MyTools;
import java.util.ArrayList;
import java.util.List;
import mgn.LogIn;

/**
 *
 * @author ADMIN
 */
public class DeliveryNotes extends ArrayList<Delivery> {

    LogIn loginObj = null;
    private String dataFile = "";
    boolean changed = false;

    public DeliveryNotes() {
    }

    public DeliveryNotes(LogIn loginObj) {
        this.loginObj = loginObj;
    }

    private void loadDealiveryFromFile() {
        List<String> lines = MyTools.readLinesFromFile(dataFile);
        for (String x : lines) {
            String[] a = x.split(",");
            Delivery de = new Delivery(a[0], a[1], a[2], a[3], Integer.parseInt(a[4]), Integer.parseInt(a[5]));
            this.add(de);
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDeliveryFile();
        loadDealiveryFromFile();
    }
    
    public int checkDuplicateID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            Delivery eachDelivery = this.get(i);
            if (eachDelivery.getDeliveryID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void addDealivery() {
        String DeliveryID, DealerID, ProductID, DeliveryDate;
        DealerID = MyTools.readPattern("Enter Dealer's ID: ", Dealer.ID_FORMAT);
        DeliveryID = MyTools.readPattern("Enter Delivery's ID: ", Delivery.DeliveryID_FORMAT);
        ProductID = MyTools.readPattern("Enter Product's ID: ", Delivery.ProductID_FORMAT);
        DeliveryDate = MyTools.readPattern("Date: ", Delivery.DeliveryDate_FORMAT);
        int quantity = MyTools.getInt("Quantity: ", 0);
        int price = MyTools.getInt("Price: ", 0);
        Delivery dl = new Delivery(DeliveryID, DealerID, ProductID, DeliveryDate, quantity, price);
        this.add(dl);
        changed = true;
    }
    
    public void updateDelivery() {
        String ID = MyTools.readPattern("Delivery's ID you want to update: ", Delivery.DeliveryID_FORMAT);
        int pos = checkDuplicateID(ID);
        if (pos >= 0) {
            String newDealerID = MyTools.readPattern("New Dealer's ID: ", Dealer.ID_FORMAT);
            String newProductID = MyTools.readPattern("New Product's ID: ", Delivery.ProductID_FORMAT);
            String newDate = MyTools.readPattern("New Date: ", Delivery.DeliveryDate_FORMAT);
            int newQuantity = MyTools.getInt("New quantity", 0);
            int newPrice = MyTools.getInt("New price", 0);
            Delivery dl = new Delivery(newDealerID, newDealerID, newProductID, newDate, newQuantity, newPrice);
            this.add(dl);
        } else {
            System.out.println("Delivery " + ID + "not found!");
        }
    }
    
    public void removeDelivery(){
        String ID = MyTools.readPattern("Delivery's ID you want to delete: ", Delivery.DeliveryID_FORMAT);
        int pos = checkDuplicateID(ID);
        if(pos >= 0){
            this.remove(pos);
        }
        else {
            System.out.println("Delivery " + ID + "not found!");
        }
    }

    public void printDealivery() {
        if (this.isEmpty()) {
            System.out.println("Empty List!");
        } else {
            System.out.println(this);
        }
    }
    
    public void printTotal(){
        if(this.isEmpty()){
            System.out.println("Empty List!");
        }
        else{
            int total = 0;
            for(int i = 0; i <= this.size(); i++){
                Delivery dL = this.get(i);
                total += dL.getPrice() * dL.getQuantity();
            }
            System.out.println("Total Amount: " + total);
        }
    }

    public void writeDealiveryToFile() {
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
