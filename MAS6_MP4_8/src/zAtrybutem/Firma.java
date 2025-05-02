package zAtrybutem;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class Firma extends ObjectPlus {
    private String nazwa;
    private List<Kontrakt> pracowniks = new ArrayList<>();

    public String getNazwa() {
        return nazwa;
    }

    public Firma(String nazwa) {
        this.nazwa = nazwa;
    }

    public void addPracownik(Pracownik pracownik){
        //BEZ TEGO JEST BAG
//        if(pracowniks.stream().noneMatch(k -> k.getPracownik() == pracownik)) {
            new Kontrakt(pracownik, this);
//        }
    }

    protected void addKontrakt(Kontrakt kontrakt) {
        if(kontrakt != null && !pracowniks.contains(kontrakt)) {
            pracowniks.add(kontrakt);
        }
    }
}
