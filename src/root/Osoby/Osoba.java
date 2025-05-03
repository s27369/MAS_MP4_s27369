package root.Osoby;

import root.ObjectPlus;
import root.ToStringType;

public class Osoba extends ObjectPlus {
    private String imie, nazwisko, PESEL;

    protected ToStringType toStringType = ToStringType.DETAILED;

    public Osoba(String imie, String nazwisko, String PESEL) {
        try {
            setImie(imie);
            setNazwisko(nazwisko);
            setPESEL(PESEL);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public String getImie() {
        return imie;
    }

    //ATRYBUTU
    public void setImie(String imie) {
        if(imie==null || imie.isBlank()){
            throw new IllegalArgumentException("Imie nie może być null ani puste");
        }
        if(!imie.matches("[A-Za-z]*")){
            throw new IllegalArgumentException("imie musi być alphanumeric");
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

    public String getPESEL() {return PESEL;}

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        if(nazwisko==null || nazwisko.isBlank()){
            throw new IllegalArgumentException("nazwisko nie może być null ani puste");
        }
        this.nazwisko = nazwisko;
    }

    public String getSimpleName(){
        ToStringType oldType = this.toStringType;
        this.toStringType=ToStringType.SIMPLE;
        String msg = this.toString();
        this.toStringType = oldType;
        return msg;
    }

    @Override
    public String toString() {
        return imie+" "+nazwisko;
    }
}
