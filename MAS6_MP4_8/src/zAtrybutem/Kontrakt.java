package zAtrybutem;

import util.ObjectPlus;

import java.time.LocalDate;

public class Kontrakt extends ObjectPlus {
    private Pracownik pracownik;
    private Firma firma;
    private LocalDate date;

    public Kontrakt(Pracownik pracownik, Firma firma) {
        setPracownik(pracownik);
        setFirma(firma);
        this.date = LocalDate.now();

        pracownik.addKontrakt(this);
        firma.addKontrakt(this);
    }

    public void setPracownik(Pracownik pracownik) {
        //czy Nie null
        this.pracownik = pracownik;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        //czy Nie null
        this.firma = firma;
    }

    //rozłączanie tej asocjacji gdzie muszę znaleźć ten konktrakt u pracownika lub firmy
    //a następnie rozłączyć u każdego i usunąć ten obiekt z ekstensji

    @Override
    public String toString() {
        return "Kontrakt{" +
                "pracownik=" + pracownik.getImie() +
                ", firma=" + firma.getNazwa() +
                ", date=" + date +
                '}';
    }
}
