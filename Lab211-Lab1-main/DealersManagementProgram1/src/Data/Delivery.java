/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ADMIN
 */
public class Delivery {
    public static final char SEPARATOR = ',';
    public static final String DeliveryID_FORMAT = "DL\\d{3}";
    public static final String DeliveryDate_FORMAT = "\\d{2}/\\d{2}/\\d{4}";
    public static final String ProductID_FORMAT = "P\\d{3}";
    public String DeliveryID;
    public String DealerID;
    public String ProductID;
    public String DeliveryDate;
    public int quantity,price;

    public Delivery(String DeliveryID, String DealerID, String ProductID, String DeliveryDate, int quantity, int price) {
        this.DeliveryID = DeliveryID;
        this.DealerID = DealerID;
        this.ProductID = ProductID;
        this.DeliveryDate = DeliveryDate;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Delivery(String line) {
        String[] parts = line.split("" + this.SEPARATOR);
        DeliveryID = parts[0].trim();
        DealerID = parts[1].trim();
        ProductID = parts[2].trim();
        DeliveryDate=parts[3].trim();
        quantity = Integer.parseInt(parts[4].trim());
        price = Integer.parseInt(parts[5].trim());
    }

    public String getDeliveryID() {
        return DeliveryID;
    }

    public void setDeliveryID(String DeliveryID) {
        this.DeliveryID = DeliveryID;
    }

    public String getDealerID() {
        return DealerID;
    }

    public void setDealerID(String DealerID) {
        this.DealerID = DealerID;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString(){
        return DeliveryID + "|" + DealerID + "|" + ProductID + "|" +DeliveryDate + "|" + quantity + "|" + price;
    } 
}

