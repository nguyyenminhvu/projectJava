/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.DealerList;
import java.util.ArrayList;
import tools.MyTools;

/**
 *
 * @author ACER
 */
public class Menu {

    private ArrayList<String> showoption = new ArrayList();

    public void addMenu(String input) {
        showoption.add(input);
    }
    public void showMenu() {
        for (int i = 0; i < showoption.size(); i++) {
            System.out.println(showoption.get(i));

        }
    }
    public int getChoice() {
        int rangechoice= showoption.size()-2;
        return tools.MyTools.inputInt("Input your choice: 1-" + rangechoice, 1, 1000);
    }
}
