import util.ObjectPlus;
import zAtrybutem.Firma;
import zAtrybutem.Kontrakt;
import zAtrybutem.Pracownik;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Person person = new Person("Ola", "1");
        Person person2 = new Person("Tola", "2");


        //ORDERED

        TreeSet<Person> set = new TreeSet<>((o1, o2) -> o2.getImie().compareTo(o1.getImie()));

        set.add(person);
        set.add(person2);


        System.out.println(set);


        Pracownik pracownik = new Pracownik("Pracownik");
        Firma firma = new Firma("Firma");

        pracownik.addFirma(firma);
        firma.addPracownik(pracownik);

//        HashSet<Kontrakt> kontraktSet = new HashSet<>();
//        kontraktSet.add(ObjectPlus.getExtentFromClass(Kontrakt.class).get(0));
//        kontraktSet.add(ObjectPlus.getExtentFromClass(Kontrakt.class).get(1));

        System.out.println(ObjectPlus.getExtentFromClass(Kontrakt.class));
//        System.out.println(kontraktSet);


    }
}