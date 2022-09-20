/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class MyTools {

    public static final Scanner sc = new Scanner(System.in);



    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d]+.*")
                && str.matches(".*[\\W]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateStr);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dF.format(date);
        dF.applyPattern(strDate);
        return strDate;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + "");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        do {
            System.out.print(message + "");
            input = sc.nextLine();
            if(input.matches(pattern)) return input;
            else System.out.println("Invalid input. Please try again!");
                    
        } while (true);
    }

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<>();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.out.println("File doesn't exist");
                return null;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while ((line = bf.readLine()) != null) {
                list.add(line.trim());
            }
            bf.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void writeFile(String filename, List list) {
        if (list.size() == 0) {
            System.out.println("The list is empty");
            return;
        }
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Object x : list) {
                pw.write(x.toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static int getInt(String welcome, int min){ //Biến để sử dụng nhiều lần
        boolean check = true;
        int number = 0;
        Scanner sc = new Scanner(System.in);
        do{
            try{//Người dùng không nhập số mà nhập các kí tự khác
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if(number < min){
                    System.out.println("Number must be large than " + min);
                }
                else{
                    check = false;
                }
            }
            catch(NumberFormatException ex){
                System.out.println("You enter the wrong number format! Please check again!");
            }
        }
        while(check || number < min);
        return number;
    }

}
