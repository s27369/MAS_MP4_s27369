package root.Osoby;

import root.ObjectPlus;
import root.ToStringType;

public class Osoba extends ObjectPlus {
    private String imie, nazwisko;

    protected ToStringType toStringType = ToStringType.SIMPLE;

    public Osoba(String imie, String nazwisko) {
        try {
            setImie(imie);
            setNazwisko(nazwisko);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        if(imie==null || imie.isBlank()){
            throw new IllegalArgumentException("Imie nie może być null ani puste");
        }
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        if(nazwisko==null || nazwisko.isBlank()){
            throw new IllegalArgumentException("nazwisko nie może być null ani puste");
        }
        this.nazwisko = nazwisko;
    }

    public void changeToStringType(){
        if(this.toStringType==ToStringType.SIMPLE)
            this.toStringType=ToStringType.DETAILED;
        else
            this.toStringType=ToStringType.SIMPLE;
    }
    public ToStringType changeToStringType(ToStringType type){
        ToStringType oldType = this.toStringType;
        this.toStringType=type;
        return oldType;
    }

    @Override
    public String toString() {
        return imie+" "+nazwisko;
    }
}
