/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Config{
    private static final String CONFIG_FILE ="DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;
    
    public Config(){
       readData();
    }
    public void readData(){
        ArrayList<String> data = new ArrayList();
        try {
            FileReader fr = new FileReader(CONFIG_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                data.add(line);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Fail!!!");
        }
        for (String line : data) {
            String[] parts = line.split(":");
            if(line.contains("accounts")){
                accountFile = "DealerData/" + parts[1].trim();
            }else if(line.contains("dealers")){
                dealerFile = "DealerData/" + parts[1].trim();
            }else if(line.contains("delivery")){
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
