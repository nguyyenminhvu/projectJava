/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import tools.MyTools;

public class Dealer implements Comparable<Dealer>{

    private String ID;
    private String name;
    private String addr;
    private String phone;
    private boolean continuing;

    public Dealer(String ID, String name, String addr, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;        
        this.addr = addr;
        this.phone = phone;
        this.continuing = continuing;
    }
    Dealer() {
     
    }
    Dealer(String ID) {
      this.ID=ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isContinuing() {
        return continuing;
    }
    
    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }    
    @Override
    public String toString() {
        return ID + ',' + name + ',' + addr + ',' + phone + ',' + continuing + "\n";
    }

    public void show() {

        System.out.printf("|%-10s|%-15s|%-14s|%-14s|%-10s|\n", ID, name, addr, phone, continuing);
    }

    @Override
    public int compareTo(Dealer o) {
        return this.getID().compareToIgnoreCase(o.getID());
    }

}
