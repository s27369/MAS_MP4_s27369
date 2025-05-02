package zAtrybutem;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class Pracownik extends ObjectPlus {
    private final String imie;
    private final List<Kontrakt> firmaList = new ArrayList<>();

    public String getImie() {
        return imie;
    }

    public Pracownik(String imie) {
        this.imie = imie;
    }

    public void addFirma(Firma firma) {
//        if (firmaList.stream().noneMatch(k -> k.getFirma() == firma)) {
            new Kontrakt(this, firma);
//        }
    }

    protected void addKontrakt(Kontrakt kontrakt) {
        if (kontrakt != null && !firmaList.contains(kontrakt)) {
            firmaList.add(kontrakt);
        }
    }
}
