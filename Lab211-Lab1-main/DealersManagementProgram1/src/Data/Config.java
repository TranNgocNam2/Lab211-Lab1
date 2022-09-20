/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Tools.MyTools;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Config {
    //Tự dưng tui sửa cái chữ config thường thành In Hoa, và giờ nó chạy được như bình thường.
    private static final String CONFIG_FILE = "DealerData/CONFIG.TXT"; 
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = MyTools.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();    
            String[] parts = line.split(":");
            if (line.indexOf("ACCOUNT") >= 0) {
                accountFile = "DealerData/" + parts[1].trim();
            } else if (line.indexOf("DEALER") >= 0) {
                dealerFile = "DealerData/" + parts[1].trim();                
            } else if (line.indexOf("DELIVER") >= 0) {
                deliveryFile = "DealerData/" + parts[1].trim();         
            }
        }
    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }
}
