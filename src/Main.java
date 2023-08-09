import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        DaniList<String> daniList = new DaniList<>();
        daniList.add("Ali");
        daniList.add(0, "d");
        for (String k :
                daniList) {
            System.out.println(k);
        }
    }
}
