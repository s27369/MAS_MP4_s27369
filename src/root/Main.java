package root;

import root.Osoby.Osoba;
import root.Osoby.Zolnierz;
import root.StrukturyOrganizacyjne.Brygada;

public class Main {
    public static void main(String[] args) {
//        Bag();
        Unique();
    }

    static void Bag() {
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

    }

    static void XOR() {

    }

    static void Atrybutu() {
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
