import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> toBeTrimmed = new ArrayList<>();
        toBeTrimmed.add(3);
        toBeTrimmed.add(15);
        toBeTrimmed.add(8);
        toBeTrimmed.add(33);
        toBeTrimmed.add(-5);
        toBeTrimmed.add(9);
        System.out.println(trimmer(toBeTrimmed));
    }

    //method filters all numbers that are lesser than 10 and bigger than 0
    public static List<Integer> trimmer(List<Integer> toTrim) {
        List<Integer> trimmedList = new ArrayList<>();
        for (Integer element : toTrim) {
            if (element > 0 && element < 10) {
                trimmedList.add(element);
            }
        }
        return trimmedList;
    }
}