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
public class Account {
   public static final String AccName_FORMAT = "E\\d{3}";
    private String accname;
    private String pwd;
    private String role;

    public Account() {
    }

    public Account(String accname, String pwd, String role) {
        this.accname = accname;
        this.pwd = pwd;
        this.role = role;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
   @Override
    public String toString(){
        return  accname + "," + pwd + "," + role;
    }
}

