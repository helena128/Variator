package sample;


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

    public static void  main(String args[]){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String line = scanner.nextLine();
            validate(line);
        }
    }
}
