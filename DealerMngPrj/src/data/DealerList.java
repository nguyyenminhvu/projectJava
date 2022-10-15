package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import tools.MyTools;
import mng.LogIn;
import static tools.MyTools.inputString;

/**
 *
 * @author ACER
 */
public class DealerList {

    private String accountFile;
    private ArrayList<Dealer> list = new ArrayList();       // tao list chua dealer
    private final Scanner sc = new Scanner(System.in);

    public DealerList() {
        Config cf = new Config();
        accountFile = cf.getDealerFile();
    }

    public void addDealer() {
        boolean check = true;
        String ID = "";

        do {                            // kiem soat nhap id, bi trung lap hay ko
            ID = tools.MyTools.idRegex("Input ID to add: ", "^[D|d]\\d{3}$").toUpperCase();
            int index = this.findId(ID);
            if (index == -1) {
                check = false;
            } else {
                System.out.println("ID already available, input again: ");
            }
        } while (check);
        String name = tools.MyTools.inputString("Input Name: ").toUpperCase();
        String addr = tools.MyTools.inputString("Input Address: ");
        String phone = tools.MyTools.phoneFormat("Input phone: ", "\\d{9}|\\d{11}");
        boolean ans = tools.MyTools.yOn("Is continuing(Y or N)? ");
        list.add(new Dealer(ID, name, addr, phone, ans));
        System.out.println("Added Successful!!");
    }

    public void ContinueAdd() {
        boolean check = tools.MyTools.yOn("Do you want to continue adding(Y or N)? ");
        if (check) {
            this.addDealer();
        }
    }

    public void searchDealer() {
        String ID = "";
        int update = 0;
        boolean check = true;
        do {
            ID = tools.MyTools.idRegex("Input ID to Search: ", "^[D|d]\\d{3}$");
            update = findId(ID);        // goi ham findName check trung lap, khac -1 thi co trong danh sach
            if (update != -1) {
                System.out.println("+----------+---------------+--------------+--------------+----------+");
                System.out.println("|    ID    |      NAME     |   ADDRESS    |     PHONE    |CONTINUING|");
                System.out.println("+----------+---------------+--------------+--------------+----------+");
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getID().equalsIgnoreCase(ID)) {
                        list.get(i).show();
                    }
                }
                System.out.println("+----------+---------------+--------------+--------------+----------+");
                System.out.println("Search Successful!!");
                System.out.println("\n");
                check = false;
            } else {
                System.out.println("This name is not on the list, input again: ");
            }
        } while (check);
    }

    public void removeDealer() {
        boolean check = true;
        int update = 0;
        String ID = "";
        do {
            ID = tools.MyTools.idRegex("Input ID to delete: ", "^[D|d]\\d{3}$");
            update = findId(ID);

            if (update != -1) {
                list.get(update).setContinuing(false);
                this.printAllDealers();
                System.out.println("Deleted Successful!!");
                System.out.println("\n");
                check = false;
            } else {
                System.out.println("This ID is not in the list, input again: ");
            }
        } while (check);
    }

    public void updateDealer() {
        int update = 0;
        String ID = "";
        boolean check = true;
        do {
            ID = tools.MyTools.idRegex("Input ID to update: ", "^[D|d]\\d{3}$");
            update = findId(ID);

            if (update != -1) {
                check = false;
            } else {
                System.out.println("This ID is not in the list, input again: ");
            }

        } while (check);
        String name = tools.MyTools.inputString("Input new name: ");
        String addr = tools.MyTools.inputString("Input new address: ");
        String phone = tools.MyTools.phoneFormat("Input new phone: ", "\\d{9}|\\d{11}");
        boolean continuing = tools.MyTools.yOn("Is continuing(Y or N)? ");
        list.get(update).setName(name);
        list.get(update).setAddr(addr);
        list.get(update).setPhone(phone);
        list.get(update).setContinuing(continuing);

        this.printAllDealers();
        System.out.println("Update Successful!!");
        System.out.println("\n");
    }

    public void printAllDealers() {
        if (list.isEmpty()) {
            System.out.println("The list is empty, nothing to show. ");
        } else {
            Collections.sort(list);
            System.out.println("+----------+---------------+--------------+--------------+----------+");
            System.out.println("|    ID    |      NAME     |   ADDRESS    |     PHONE    |CONTINUING|");
            System.out.println("+----------+---------------+--------------+--------------+----------+");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).show();
            }
        }
        System.out.println("+----------+---------------+--------------+--------------+----------+");
    }

    public void printContinuingDealers() {
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        System.out.println("|    ID    |      NAME     |   ADDRESS    |     PHONE    |CONTINUING|");
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isContinuing()) {
                list.get(i).show();
            }
        }
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        System.out.println("\n");
    }

    public void printUnContinuingDealers() {
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        System.out.println("|    ID    |      NAME     |   ADDRESS    |     PHONE    |CONTINUING|");
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isContinuing()) {
                list.get(i).show();
            }

        }
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        System.out.println("\n");
    }

    public boolean CheckcomfirmYN(String input) {
        if ("Y".equalsIgnoreCase(input)) {
            return true;
        }
        return false;
    }

    public boolean comfirmYesNo(String input) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        boolean check1 = false;
        String answer = "";
        do {
            answer = inputString(input);
            if ("Y".equalsIgnoreCase(answer) || "N".equalsIgnoreCase(answer)) {
                if (CheckcomfirmYN(answer)) {
                    check1 = true;
                    check = true;
                } else {
                    check1 = false;
                    check = true;
                }
            } else {
                System.out.println("Please enter Y or N to confirm, Input again: ");
                check = false;
            }
        } while (!check);
        return check1;
    }

    private int findId(String ID) {     // ham kiem tra id, tra ve position cua id
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID().trim().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    private int findName(String name) { // ham kiem tra name, tra ve position cua name
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().trim().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public void sorted() {
        Collections.sort(list);
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        System.out.println("|    ID    |      NAME     |   ADDRESS    |     PHONE    |CONTINUING|");
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).show();
        }
        System.out.println("+----------+---------------+--------------+--------------+----------+");
        System.out.println("Sort Successful!!");
        System.out.println("\n");

    }

    public void loadDealerFromFile() {
        try {
            FileReader fr = new FileReader(accountFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String id = txt[0];
                String name = txt[1];
                String addr = txt[2];
                String phone = txt[3];
                boolean continuing = Boolean.parseBoolean(txt[4]);
                list.add(new Dealer(id, name, addr, phone, continuing));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Can't read data of examination from file.Fail..");
        }
    }

    public void writeToFile() {
        Config newConfig = new Config();
        accountFile = newConfig.getDealerFile();
        try {
            File f = new File(accountFile);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.print(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Save to file Successfully.");

    }

}
