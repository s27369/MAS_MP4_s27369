import util.ObjectPlus;
import zAtrybutem.Firma;
import zAtrybutem.Pracownik;

import java.util.ArrayList;
import java.util.List;

public class Person extends ObjectPlus {
    private String imie;
    private String PESEL;

    //SUBSET
    private static List<Person> peopleInCountry = new ArrayList<>();
    private static List<Person> peopleInTown = new ArrayList<>();

    public void addToCountry() {
        peopleInCountry.add(this);
    }

    public void addToTown() {
        if(!peopleInCountry.contains(this)) {
            throw new IllegalArgumentException("Person is not in country");
        }
        peopleInTown.add(this);
    }


    public void removeFromCountry() {
        if(peopleInTown.contains(this)) {
            removeFromTown();
        }
        peopleInCountry.remove(this);
    }

    public void removeFromTown() {
        peopleInTown.remove(this);
    }

    //to powinny być asocjacje które się wykluczają - XOR
    private Firma BEZROBOCIE;
    private Pracownik ROBOCIE;
    private EnumeratorDoXOR enumeratorDoXOR;

    public void setBEZROBOCIE(Firma BEZROBOCIE) {
        if(enumeratorDoXOR != null) {
            throw new IllegalStateException("ROBOCIE already set");
        }
        enumeratorDoXOR = EnumeratorDoXOR.Bezrobotny;
        this.BEZROBOCIE = BEZROBOCIE;
    }

    public void setROBOCIE(Pracownik ROBOCIE) {
        if(enumeratorDoXOR != null) {
            throw new IllegalStateException("BEZROBOCIE already set");
        }
        enumeratorDoXOR = EnumeratorDoXOR.Robotny;
        this.ROBOCIE = ROBOCIE;
    }

    public Person(String imie, String PESEL) {
        setImie(imie);
        setPESEL(PESEL);
    }

    public String getImie() {
        return imie;
    }

    public String getPESEL() {
        return PESEL;
    }
    //ATRYBUTU
    public void setImie(String imie) {
        if(imie == null || imie.isBlank()){
            throw new IllegalArgumentException("imie cannot be blank");
        }
        if(!imie.matches("[A-Za-z]*")){
            throw new IllegalArgumentException("imie must be alphanumeric");
        }
        if(imie.length() < 3){
            throw new IllegalArgumentException("imie must be at least 3 characters");
        }
        this.imie = imie;
    }

    //UNIQUE
    public void setPESEL(String PESEL) {
        if(ObjectPlus.getExtentFromClass(getClass()).stream().anyMatch(p -> p != this && p.getPESEL().equals(PESEL))){
            throw new IllegalArgumentException("PESEL already exists");
        }
        this.PESEL = PESEL;
    }

    @Override
    public String toString() {
        return "Person{" +
                "imie='" + imie + '\'' +
                ", PESEL='" + PESEL + '\'' +
                '}';
    }
}
