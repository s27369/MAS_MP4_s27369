package root.StrukturyOrganizacyjne;

import root.Inne.Adres;
//import root.Inne.Pojazd;
import root.Osoby.Zolnierz;
import root.ToStringType;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Batalion extends StrukturaOrganizacyjna {
    //każdy batalion ma swoją jednostkę.
    //batalion moze miec przypisane pojazdy

    //asocjacja zwykla: brygada 1-*batalion

    private Adres adresJednostki;


    public Batalion(int numer, Adres adresJednostki) {
        super(numer);
        try{
            setAdresJednostki(adresJednostki);
        }catch (Exception e){
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public Batalion(int numer, Adres adresJednostki, Brygada brygadaMacierzysta) {
        this(numer, adresJednostki);
        try{
            setBrygadaMacierzysta(brygadaMacierzysta);
        }catch (Exception e){
            e.printStackTrace();
            removeFromExtent();
        }
    }
//=======================================XOR===========================================
    private BatalionTyp typ;
    private Brygada brygadaMacierzysta;
    private Sztab sztab;
//    ------------------------------BRYGADA MACIERZYSTA------------------------------
    public void setBrygadaMacierzysta(Brygada brygadaMacierzysta) {
        if(brygadaMacierzysta==null) throw new IllegalArgumentException("brygadaMacierzysta nie może być null");
        if(this.typ!=null) throw new IllegalStateException("Sztab jest już ustawiony");
        this.brygadaMacierzysta = brygadaMacierzysta;
        this.typ = BatalionTyp.ZWYKLY;//XOR
        brygadaMacierzysta.addBatalion(this);
        System.out.println("W ramach "+brygadaMacierzysta.getSimpleName()+ " uformowano "+this.getSimpleName());
    }

    public void removeBrygadaMacierzysta(){
        if(this.brygadaMacierzysta == null){
            System.out.println("Batalion "+this.getSimpleName()+" nie ma przypisanej brygady macierzystej");
        }else{
            System.out.println("Usuwam przypisanie "+this+ " do "+this.brygadaMacierzysta);

            Brygada brygCopy = this.brygadaMacierzysta;
            this.brygadaMacierzysta=null;
            brygCopy.removeBatalion(this);
        }
    }
    public Brygada getBrygadaMacierzysta() {return brygadaMacierzysta;}
//----------------------------------SZTAB--------------------------------


    public Sztab getSztab() {return sztab;}

    public void setSztab(Sztab sztab) {
        if(sztab==null) throw new IllegalArgumentException("sztab nie może być null");
        if(this.typ!=null) throw new IllegalStateException("BrygadaMacierzysta już ustawiona");
        this.typ = BatalionTyp.SAMODZIELNY;
        this.sztab = sztab;
        this.sztab.addPodlegajacaJednostka(this);
        System.out.println(this.getSimpleName()+" od teraz podlega pod sztab "+sztab.getSimpleName());
    }

    //    ------------------------------ADRES------------------------------
    public Adres getAdresJednostki() {return adresJednostki;}

    public void setAdresJednostki(Adres adresJednostki) {
        if(adresJednostki==null){
            throw new IllegalArgumentException("adresJednostki nie może być null");
        }
        this.adresJednostki = adresJednostki;
    }

    public BatalionTyp getTyp() {return typ;}

    @Override
    public String toString() {
        if(this.toStringType==ToStringType.SIMPLE){
            return numer + " Batalion";
        }else{
            String superiorMsg = typ==BatalionTyp.ZWYKLY?", brygadaMacierzysta=" + brygadaMacierzysta.getSimpleName():", sztab="+sztab.getSimpleName();
            String msg = "Batalion{" +
                    "numer=" + numer +
                    ", typ=" + typ +
                    superiorMsg +
                    ", adresJednostki=" + adresJednostki +
                    '}';
            return msg;
        }
    }

}
