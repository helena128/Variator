package sample;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private final static String pattern = "^\\s*[А-яЁё]+\\s*$";

    public static boolean validate(String str){

        Pattern patt = Pattern.compile(pattern);

        Matcher m = patt.matcher(str);

        System.out.println("GOT: >" + str + "<");
        if (m.find()){
            System.out.println("FOUND");
            return true;
        } else {
            System.out.println("Not found");
            return false;
        }

    }


    public static int getStringLength(String line){
        return ((line.replaceAll("\\s+", "")).length());
    }

    public static void  main(String args[]){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Name:");
            String name = scanner.nextLine();
            System.out.println("Surname:");
            String surname = scanner.nextLine();
            System.out.println("Middlename:");
            String middle = scanner.nextLine();

            Variant var = new Variant(name, surname, middle);
            ArrayList<Integer> numbers = var.generateNumbers();
        }
    }
}
