package tools;

import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class MyTools {

    public static final int MIN = 1;
    public static final int MAX = 100;

    public MyTools() {
        super();
    }

    public static String inputString(String input) {    // ham kiem soat nhap chuoi( ko khoang trang, ko enter)
        String result = "";
        boolean check = false;
        do {
            System.out.println(input);
            Scanner sc = new Scanner(System.in);
            result = sc.nextLine();
            if (result.isEmpty() || result.trim().length() == 0) {
                System.out.println("Input please !!");
                check = false;
            } else {
                check = true;
            }
        } while (!check);
        return result;
    }

    public static String phoneFormat(String input, String format) { //ham kiem tra format phone
        boolean check;
        String phone = "";
        do {
            phone = MyTools.inputString(input);
            check = phone.matches(format);
            if (phone.length() == 0 || phone.isEmpty() || !check) {
                System.out.println("Wrong format, input again: ");
            } else {
                return phone;
            }
        } while (!check);
        return phone;
    }

    public static int inputInt(String input, int min, int max) {    // ham kiem soat nhap int 
        int result = 0;
        boolean check = false;
        do {
            try {
                System.out.println(input);
                Scanner sc = new Scanner(System.in);
                result = Integer.parseInt(sc.nextLine());
                check = true;
            } catch (Exception e) {
                System.out.println("input integer pls!!");
            }
        } while (result < min || result > max || !check);
        return result;
    }

    public static boolean comfirmYN(String input) { // ham nhap kiem tra yes no, tra ve quit chuong trinh
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        String answer = inputString(input);
        if ("Y".equalsIgnoreCase(answer)) {
            check = true;
        }
        return check;
    }

    public static String idRegex(String input, String format) { // ham kiem tra format id
        boolean check = true;
        String ID = "";
        do {
            ID = inputString(input);
            check = ID.matches(format);
            if (ID.length() == 0 || ID.isEmpty() || !check) {
                System.out.println("Wrong format, input again: ");
            } else {
                return ID;
            }
        } while (!check);
        return ID;
    }

    public static boolean yOn(String input) {       //ham kiem soat nhap y/n.
        boolean check = false;                      // kiem soat y/n
        boolean check1 = false;                     // tra ve y/n
        String answer = "";
        do {
            answer = inputString(input);
            if ("Y".equalsIgnoreCase(answer) || "N".equalsIgnoreCase(answer)) {
                if ("Y".equalsIgnoreCase(answer)) {
                    check = true;
                    check1 = false;
                } else {
                    check = false;
                    check1 = false;
                }
            } else {
                System.out.println("Please enter Y or N to confirm, Input again: ");
                check1 = true;
            }
        } while (check1);
        return check;
    }
}
