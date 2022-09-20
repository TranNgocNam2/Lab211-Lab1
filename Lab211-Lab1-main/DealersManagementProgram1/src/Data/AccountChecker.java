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
public class AccountChecker {

    private String accFile;
    private static String SEPARATOR = ",";

    public AccountChecker() {
        setupAccFile();
    }

    public void setupAccFile() {
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }

    public boolean check(Account acc) {
        List<String> lines = MyTools.readLinesFromFile(accFile);

                
        for (String line : lines) {
            /*System.out.println("Each time:");*/
            String[] parts = line.split(this.SEPARATOR);
            if (parts.length < 3) {
                return false;
            }
            /*System.out.println(parts[0]+' '+parts[1]+' '+parts[2]);
            System.out.println(acc.getAccname()+' '+acc.getPwd()+' '+acc.getRole());*/
            if (parts[0].equalsIgnoreCase(acc.getAccname())
                    && parts[1].equals(acc.getPwd())
                    && parts[2].equalsIgnoreCase(acc.getRole())) {
                
                return true;
            }
        }
        return false;
    }

}
