package sample;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private final static String pattern = "^\\s*[А-яЁё]+\\s*$";

    public static boolean validate(String str){

        Pattern patt = Pattern.compile(pattern);

        Matcher m = patt.matcher(str);

        if (m.find()){
            return true;
        } else {
            return false;
        }

    }


    public static int getStringLength(String line){
        if (line != null)
            line = line.replaceAll("\\s+", "");
        if (line == null)
            return 0;
        return line.length();
    }

    public static void  main(String args[]){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Name:");
            String name = scanner.nextLine();
            System.out.println("Surname:");
            String surname = scanner.nextLine();
            System.out.println("Middlename:");
            scanner = new Scanner(System.in);
            String middle = scanner.nextLine();

            Variant var = new Variant(name, surname, middle);
            ArrayList<Integer> numbers = var.generateNumbers();

            for (Integer num: numbers)
                System.out.println(num.intValue());
        }
    }
}
