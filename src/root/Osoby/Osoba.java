package root.Osoby;

import root.ObjectPlus;
import root.StrukturyOrganizacyjne.Sztab;
import root.ToStringType;

public class Osoba extends ObjectPlus {
    private String imie, nazwisko, PESEL;
    private Sztab sztab;

    protected ToStringType toStringType = ToStringType.DETAILED;

    public Osoba(String imie, String nazwisko, String PESEL) {
        try {
            setImie(imie);
            setNazwisko(nazwisko);
            setPESEL(PESEL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            removeFromExtent();
        }
    }

    public Sztab getSztab() {return sztab;}

    public void setSztab(Sztab sztab) {
        if(sztab==null) throw new IllegalArgumentException("sztab nie może być null");
        this.sztab = sztab;
    }

    public void removeSztab(){
        if(this.sztab!=null){
            Sztab s = this.sztab;
            this.sztab=null;
            s.removeCzlonek(this);
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
        if(this.toStringType==ToStringType.SIMPLE) return imie+" "+nazwisko;
        else{
            return "Osoba{" +
                    "imie='" + imie + '\'' +
                    ", nazwisko='" + nazwisko + '\'' +
                    ", PESEL='" + PESEL + '\'' +
                    ", sztab=" + (sztab==null?"brak":sztab.getSimpleName())+
                    '}';
        }
    }
}
