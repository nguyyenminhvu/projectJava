/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.Account;
import data.AccountChecker;
import data.Config;
import data.DealerList;
import java.util.ArrayList;
import tools.MyTools;

public class LogIn extends ArrayList<String> {

    public static void main(String args[]) {
        AccountChecker accCheck = new AccountChecker(); // goi class accountChecker 
        Account acc = accCheck.inputAccount();          // input account
        boolean checkAcc = accCheck.checkLogin(acc);    // check true false account
        boolean check = true;
        do {        // vong lap neu account nhap sai
            if (!checkAcc) {
                boolean answer = MyTools.yOn("Wrong account name or password, do you want input again(y/n)?");
                if (answer) {
                    acc = accCheck.inputAccount();
                    checkAcc = accCheck.checkLogin(acc);
                } else {
                    check = false;
                }
            } else {
                check = false;
            }
        } while (check); // nguoi dung nhap dung hay sai van se out vong lap
        if (checkAcc) {
            if (acc.getRole().equalsIgnoreCase("ACC-1")) {

                DealerList list = new DealerList();
                list.loadDealerFromFile();         // load danh sach dealer trong file
                Menu menu = new Menu();
                menu.addMenu("1- Add new dealer");
                menu.addMenu("2- Search a dealer ");
                menu.addMenu("3- Remove a dealer ");
                menu.addMenu("4- Update a dealer");
                menu.addMenu("5- Print all dealers ");
                menu.addMenu("6- Print continuing dealers");
                menu.addMenu("7- Print UN-continuing dealers");
                menu.addMenu("8- Write to file");
                menu.addMenu("Other for quit.");
                menu.addMenu("Choose [1.. 8]: ");
                int choice;
                boolean cont = false;

                do {
                    menu.showMenu();    // hien thi menu
                    choice = menu.getChoice();  // nhap lua chon
                    if (choice < 1 || choice > 8) { // nhap so ngoai 1-8 out chuong trinh
                        cont = false;
                        if (list.comfirmYesNo("Do you wanna quit? ")) {
                            cont = true;
                            break;
                        } else {
                            cont = false;
                        }
                    }
                    switch (choice) {
                        case 1:
                            list.addDealer();
                            list.ContinueAdd();
                            break;
                        case 2:
                            list.searchDealer();
                            break;
                        case 3:
                            list.removeDealer();
                            break;
                        case 4:
                            list.updateDealer();
                            break;
                        case 5:
                            list.printAllDealers();
                            break;
                        case 6:
                            list.printContinuingDealers();
                            break;
                        case 7:
                            list.printUnContinuingDealers();
                            break;
                        case 8:
                            list.writeToFile();
                            break;
                    }
                } while (!cont);
            }
        }
    }
}
