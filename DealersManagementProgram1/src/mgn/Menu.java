/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgn;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Menu extends ArrayList<String>{
    public Menu(){
        super();
    }

    public Menu(String[] items) {
        super();
        for(String  item: items)
            this.add(item);
    }
    public int getChoice(String title){
        System.out.println(title);
            for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + " - " + this.get(i));
        }
        do {
            try {                
                Scanner sc = new Scanner(System.in);
                System.out.print("Your choice: ");
                return Integer.parseInt(sc.nextLine());
            } catch (Exception ex) {
                System.out.println("Your choice is invalid!");
            }
        } while (true);
    }
}

