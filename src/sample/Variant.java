package sample;


import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.List;

/**
 * class holding all variables we need
 * plus the main calculating method
 */
public class Variant {

    public Variant(String name, String surname, String middlename){
        this.name = name;
        this.surname = surname;
        this.middle = middlename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    private String name, surname, middle;


    /**
     * method where we generate our numbers
     * @return
     */
    public ArrayList<Integer> generateNumbers(){
        ArrayList<Integer> numbers = new ArrayList<>(7);
        // filling out generic
        int nameLength = Controller.getStringLength(name);
        int surnameLength = Controller.getStringLength(surname);
        int middleLength = (middle.replaceAll("\\s+", "")).length();

        numbers.add(surnameLength * nameLength); // А
        numbers.add(numbers.get(0) + 1); // Б

        // not to multiply by zero
        if (middleLength == 0)
            middleLength = 1;

        numbers.add(surnameLength * middleLength * 1000); // В
        numbers.add((int)(numbers.get(2) / nameLength)); // Г
        numbers.add(5 * numbers.get(3) + numbers.get(2)); // Д = 5 * Г + В
        numbers.add(numbers.get(3)); // Е
        numbers.add(3 + (numbers.get(0) % 6)); // Ё

        return numbers;
    }

    @Override
    public String toString(){
        return ("Name " + this.name + " Surname " + this.surname + " Middle " + this.middle);
    }
}
