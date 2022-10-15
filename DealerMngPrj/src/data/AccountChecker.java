/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import tools.MyTools;

/**
 *
 * @author ACER
 */
public class AccountChecker {

    private String accFile;
    private static final ArrayList<Account> listAcc = new ArrayList();

    public AccountChecker() {
        Config cR = new Config();
        this.accFile = cR.getAccountFile();
        readData();
    }

    public String getAccFile() {
        return accFile;
    }

    public void readData() {
        try {
            FileReader fr = new FileReader(accFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String accName = txt[0];
                String password = txt[1];
                String role = txt[2];
                listAcc.add(new Account(accName, password, role));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Can't read data of examination from file.Fail..");
        }
    }

    public Account inputAccount() {
        String name = MyTools.inputString("Your Account name: ");
        String password = MyTools.inputString("Your password: ");
        String role = MyTools.inputString("Your role: ");
        Account acc = new Account(name, password, role);
        return acc;
    }

    public boolean checkLogin(Account acc) {
        if (listAcc.isEmpty()) {
            return false;
        }
        for (int i = 0; i < listAcc.size(); i++) {
            if (listAcc.get(i).getAccName().equalsIgnoreCase(acc.getAccName())
                    && listAcc.get(i).getPwd().equals(acc.getPwd())
                    && listAcc.get(i).getRole().equalsIgnoreCase(acc.getRole())) {
                return true;
            }
        }
        return false;
    }

}
