package root.StrukturyOrganizacyjne;

import root.Inne.Adres;
import root.Inne.Pojazd;
import root.ToStringType;

import java.util.HashMap;
import java.util.Map;

public class Batalion extends StrukturaOrganizacyjna {
    //każdy batalion ma swoją jednostkę.
    //batalion moze miec przypisane pojazdy

    //asocjacja zwykla: brygada 1-*batalion
    //asocjacja kwalifikowana: pojazd *-1 batalion
    private Adres adresJednostki;
    private Brygada brygadaMacierzysta;
    private Map<String, Pojazd> pojazdMap = new HashMap<>();

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
//    ------------------------------BRYGADA MACIERZYSTA------------------------------

    public Brygada getBrygadaMacierzysta() {
        return brygadaMacierzysta;
    }

    public void setBrygadaMacierzysta(Brygada brygadaMacierzysta) {
        if(brygadaMacierzysta==null) {//czy argument to null
            throw new IllegalArgumentException("brygadaMacierzysta nie może być null");
        }
        if(this.brygadaMacierzysta!=null) {//czy jest juz przypisana brygada mac.
            ToStringType batType = this.changeToStringType(ToStringType.SIMPLE);
            ToStringType brygType = this.brygadaMacierzysta.changeToStringType(ToStringType.SIMPLE);
            System.out.println("Brygada macierzysta dla "+this+" jest już przypisana: "+this.brygadaMacierzysta);
            this.changeToStringType(batType);
            this.brygadaMacierzysta.changeToStringType(brygType);
            return ;
        }
        ToStringType batType = this.changeToStringType(ToStringType.SIMPLE);
        ToStringType brygType = brygadaMacierzysta.changeToStringType(ToStringType.SIMPLE);
        System.out.println("W ramach "+brygadaMacierzysta+ " uformowano "+this);
        this.changeToStringType(batType);
        brygadaMacierzysta.changeToStringType(brygType);
        this.brygadaMacierzysta = brygadaMacierzysta;
        brygadaMacierzysta.addBatalion(this);
    }

    public void removeBrygadaMacierzysta(){
        if(this.brygadaMacierzysta == null){
            ToStringType batType = this.changeToStringType(ToStringType.SIMPLE);
            System.out.println("Batalion "+this+" nie ma przypisanej brygady macierzystej");
            this.changeToStringType(batType);
        }else{
            ToStringType batType = this.changeToStringType(ToStringType.SIMPLE);
            ToStringType brygType = this.brygadaMacierzysta.changeToStringType(ToStringType.SIMPLE);
            System.out.println("Usuwam przypisanie "+this+ " do "+this.brygadaMacierzysta);
            this.changeToStringType(batType);
            this.brygadaMacierzysta.changeToStringType(brygType);

            Brygada brygCopy = this.brygadaMacierzysta;
            this.brygadaMacierzysta=null;
            brygCopy.removeBatalion(this);
        }
    }
//    ------------------------------POJAZD------------------------------
    public void addPojazd(Pojazd pojazd){
        if(pojazd==null){
            throw new IllegalArgumentException("Pojazd nie moze byc null");
        }
        if(this.pojazdMap.containsKey(pojazd.getNazwa())){
//            System.out.println("Pojazd jest już przypisany do tego batalionu");
            return;
        }

        pojazdMap.put(pojazd.getNazwa(), pojazd);

        if(pojazd.getBatalion() !=this)
            pojazd.setBatalion(this);
    }
    public void removePojazd(String nazwa){
        Pojazd pojazd = findPojazdByNazwa(nazwa);
        if(pojazd==null){
            System.out.println("Nie znaleziono pojazdu "+nazwa);
            return;
        }
        pojazdMap.remove(pojazd.getNazwa());
        if(pojazd.getBatalion() == this)
            pojazd.removeBatalion();
    }
    public Pojazd findPojazdByNazwa(String nazwa){
        if(nazwa==null){
            throw new IllegalArgumentException("Nazwa nie moze byc null");
        }
        return this.pojazdMap.get(nazwa);
    }
//    ------------------------------ADRES------------------------------

    public Adres getAdresJednostki() {
        return adresJednostki;
    }

    public void setAdresJednostki(Adres adresJednostki) {
        if(adresJednostki==null){
            throw new IllegalArgumentException("adresJednostki nie może być null");
        }
        this.adresJednostki = adresJednostki;
    }

    @Override
    public String toString() {
        if(this.toStringType==ToStringType.SIMPLE){
            return numer + " Batalion";
        }else{
            String brygadaMsg;
            if(this.brygadaMacierzysta==null){
                brygadaMsg = "brak";
            }else{
                ToStringType type = this.brygadaMacierzysta.getToStringType();
                this.brygadaMacierzysta.changeToStringType(ToStringType.SIMPLE);
                brygadaMsg = brygadaMacierzysta.toString();
                this.brygadaMacierzysta.changeToStringType(type);
            }

            String msg = "Batalion{" +
                    "numer=" + numer +
                    ", brygadaMacierzysta=" + brygadaMsg +
                    ", adresJednostki=" + adresJednostki +
                    ", pojazdMap=" + pojazdMap +
                    '}';
            return msg;
        }
    }

}
