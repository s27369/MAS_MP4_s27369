package root;

import root.Inne.Adres;
import root.Osoby.Osoba;
import root.Osoby.Zolnierz;
import root.StrukturyOrganizacyjne.Batalion;
import root.StrukturyOrganizacyjne.Brygada;
import root.StrukturyOrganizacyjne.Sztab;

public class Main {
    public static void main(String[] args) {
//        Bag();
//        Unique();
        XOR();
    }

    static void Bag() {
        System.out.println("----------------------------------------------Bag--------------------------------------------");
        Zolnierz z1 = new Zolnierz("Jan", "Kowalski", "5", "Szeregowy", "1234");
        Brygada b1 = new Brygada(18, "Stołeczna", "Warszawa");

        z1.addBrygada(b1, 3);

        ObjectPlus.printExtent();

        z1.addBrygada(b1, 5);

//        ObjectPlus.printExtent();

        z1.removeFromExtent();

        ObjectPlus.printExtent();
    }

    static void Ordered() {
        System.out.println("----------------------------------------------Ordered--------------------------------------------");
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
    }

    static void Unique() {
        Osoba o1 = new Osoba("Jan", "Kowalski", "1234");
        try {
            Osoba o2 = new Osoba("Karol", "Nowak", "1234");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
