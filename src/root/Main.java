package root;

import root.Inne.Adres;
import root.Osoby.Osoba;
import root.Osoby.Zolnierz;
import root.StrukturyOrganizacyjne.Batalion;
import root.StrukturyOrganizacyjne.Brygada;
import root.StrukturyOrganizacyjne.Sztab;

public class Main {
    public static void main(String[] args) {
        Bag();
        Unique();
        XOR();
        Subset();
        Ordered();
    }

    static void Bag() {
        System.out.println("----------------------------------------------Bag--------------------------------------------");
        Zolnierz z1 = new Zolnierz("Jan", "Kowalski", "5", "Szeregowy");
        Brygada b1 = new Brygada(18, "Stołeczna", "Warszawa");

        z1.addBrygada(b1, 3);
        ObjectPlus.printExtent();

        z1.addBrygada(b1, 5);
        ObjectPlus.printExtent();

//        z1.removeFromExtent();
//        ObjectPlus.printExtent();
//        b1.removeFromExtent();

        b1.removeFromExtent();
        ObjectPlus.printExtent();
        z1.removeFromExtent();
        System.out.println("\n");
    }

    static void Ordered() {
        System.out.println("----------------------------------------------Ordered--------------------------------------------");
        Zolnierz z1 = new Zolnierz("Jan", "Bartczak", "123", "Szeregowy");
        Zolnierz z2 = new Zolnierz("Piotr", "Jedrzejczak", "234", "Kapral");
        Zolnierz z3 = new Zolnierz("Karol", "Kowalski", "345", "Kapral");
        Zolnierz z4 = new Zolnierz("Andrzej", "Nowak", "456", "Pułkownik");
        Zolnierz z5 = new Zolnierz("Michal", "Kacprzak", "567", "Pułkownik");

        Zolnierz.addToHierarchia(z1);
        Zolnierz.addToHierarchia(z1);
        Zolnierz.addToHierarchia(z2);
        Zolnierz.addToHierarchia(z3);
        Zolnierz.addToHierarchia(z4);
        Zolnierz.addToHierarchia(z5);

        var x = Zolnierz.getHierarchia();
        for(var z: x) System.out.println(z.getSimpleName()+"   ");
        System.out.println();

        z1.removeFromExtent();
        z2.removeFromExtent();
        z3.removeFromExtent();
        z4.removeFromExtent();
        z5.removeFromExtent();
        System.out.println("\n");
    }

    static void XOR() {
        System.out.println("----------------------------------------------XOR--------------------------------------------");
        Sztab sztab = new Sztab("Sztab 1");
        Brygada brygada = new Brygada(18, "Stołeczna", "Warszawa");
        Batalion batalion181 = new Batalion(181, new Adres("Wesoła", 1, "Warszawa"));
        Batalion batalionSamodzielny = new Batalion(10, new Adres("Smutna", 2, "Warszawa"));

        brygada.setSztab(sztab);
        batalionSamodzielny.setSztab(sztab);
        try {
            batalionSamodzielny.setBrygadaMacierzysta(brygada);
        }catch( Exception e){
            System.out.println(e.getMessage());
        }
        batalion181.setBrygadaMacierzysta(brygada);
        try {
            batalion181.setSztab(sztab);
        }catch( Exception e){
            System.out.println(e.getMessage());
        }

        ObjectPlus.printExtent();

        sztab.removeFromExtent();

        ObjectPlus.printExtent();

        brygada.removeFromExtent();
        batalion181.removeFromExtent();
        System.out.println("\n");
    }

    static void Subset(){
        Sztab sztab = new Sztab("Sztab 1");
        Osoba o1 = new Osoba("Pan", "Prezydent", "1234");
        Osoba o2 = new Osoba("Jan", "Kowalski", "3456");
        Osoba o3 = new Osoba("Osoba", "Ktorarobikawe", "4567");
        Zolnierz z1 = new Zolnierz("Karol", "Nowak", "2345", "Pułkownik");

        try{
            sztab.addDowodztwo(o1, "Zwierzchnik SZ");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        sztab.addCzlonek(o1);
        sztab.addDowodztwo(o1, "Zwierzchnik SZ");

        sztab.addCzlonek(o2);
        sztab.addCzlonek(o3);
        sztab.addCzlonek(z1);
        sztab.addDowodztwo(z1, "Dowódca brygady");

        ObjectPlus.printExtent();

        sztab.removeFromExtent();

        ObjectPlus.printExtent();

        o1.removeFromExtent();
        o2.removeFromExtent();
        o3.removeFromExtent();
        z1.removeFromExtent();
        System.out.println("\n");
    }

    static void Atrybutu() {
        System.out.println("----------------------------------------------Atrybutu--------------------------------------------");
        Osoba o1 = new Osoba("Jan", "Kowalski", "1");
        try {
            Osoba o2 = new Osoba("J", "Kowalski", "2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Osoba o3 = new Osoba("3", "Kowalski", "3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        o1.removeFromExtent();
        ObjectPlus.printExtent();
        System.out.println("\n");
    }

    static void Unique() {
        System.out.println("----------------------------------------------Unique--------------------------------------------");
        Osoba o1 = new Osoba("Jan", "Kowalski", "1234");
        try {
            Osoba o2 = new Osoba("Karol", "Nowak", "1234");
        }catch(Exception e){
//            System.out.println(e.getMessage());
        }
        ObjectPlus.printExtent();
        o1.removeFromExtent();
        System.out.println("\n");
    }
}
