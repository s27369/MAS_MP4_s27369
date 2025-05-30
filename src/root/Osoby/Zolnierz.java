package root.Osoby;

import root.Inne.Kontrakt;
import root.StrukturyOrganizacyjne.Brygada;
import root.ToStringType;

import java.util.*;

public class Zolnierz extends Osoba {
    private static final List<String> STOPNIE = List.of("szeregowy", "kapral", "pułkownik");

    private static TreeSet<Zolnierz> hierarchia = new TreeSet<>(
            Comparator.comparingInt(z -> STOPNIE.size()-STOPNIE.indexOf(z.getStopien().toLowerCase()))
    );
    private String stopien;
    private List<Kontrakt> kontraktList = new ArrayList<>();


    public Zolnierz(String imie, String nazwisko, String pesel, String stopien) {
        super(imie, nazwisko, pesel);
        try {
            setStopien(stopien);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }

    }
//-------------------------------------------------KOMPOZYCJA----------------------------------------------------------------

    @Override
    public void removeFromExtent() {
        while(kontraktList.size()>0) kontraktList.get(0).removeFromExtent();
        super.removeFromExtent();
    }

    //-------------------------------------------------BRYGADA----------------------------------------------------------------
    public void addBrygada(Brygada brygada, int naIleLat){
        new Kontrakt(this, brygada, naIleLat);
    }
    public void removeBrygada(Brygada brygada){
        if (brygada == null) {
            throw new IllegalArgumentException("brygada nie może być null");
        }
        List<Kontrakt> toRemove = kontraktList.stream().filter(b -> b.getBrygada()==brygada).toList();
        toRemove.forEach(Kontrakt::removeFromExtent);
    }
//-------------------------------------------------KONTRAKT----------------------------------------------------------------

    public void addKontrakt(Kontrakt kontrakt){
        if (kontrakt==null){
            throw new IllegalArgumentException("Kontrakt nie może być null");
        }
        if(!kontraktList.contains(kontrakt)){
            kontraktList.add(kontrakt);
            System.out.println("[Zolnierz] dodano kontrakt dla "+kontrakt.getBrygada());
        }
    }

    public void removeKontrakt(Kontrakt kontrakt){ //usuwa przypisanie kontraktu do zolnierza
        if (kontrakt==null){
            throw new IllegalArgumentException("Kontrakt nie może być null");
        }
        kontraktList.remove(kontrakt);
    }

    public List<Kontrakt> getKontraktList() {
        return Collections.unmodifiableList(kontraktList);
    }
//-----------------------------------------------------------------------------------------------------------------

    public String getStopien() {
        return stopien;
    }

    public void setStopien(String stopien) {
        if(stopien==null || stopien.isBlank()){
            throw new IllegalArgumentException("stopien nie może być null ani pusty");
        }
        if(!STOPNIE.contains(stopien.toLowerCase())) throw new IllegalArgumentException("na potrzeby zadania dostępne stopnie to szeregowy, kapral, pułkownik");
        this.stopien = stopien;
    }

    public static Set<Zolnierz> getHierarchia(){
        return Collections.unmodifiableSet(hierarchia);
    }
    public static void addToHierarchia(Zolnierz z){
        hierarchia.add(z);
    }


//-----------------------------------------------------------------------------------------------------------------

    public String getSimpleName(){
        ToStringType oldType = this.toStringType;
        this.toStringType=ToStringType.SIMPLE;
        String msg = this.toString();
        this.toStringType = oldType;
        return msg;
    }

    @Override
    public String toString() {
        if(this.toStringType == ToStringType.SIMPLE)
            return stopien+" "+getImie()+" "+getNazwisko();
        String kontraktyMsg="[";
        if(this.kontraktList.isEmpty()) {
            kontraktyMsg = "brak";
        }else{
            int i = 0;
            String end = ", ";
            for(Kontrakt k: kontraktList){
                if(i==kontraktList.size()-1){
                    end="]";
                }
                kontraktyMsg+=k.toString()+end;
                i++;
            }
        }
        return "Zolnierz{" + super.toString() +", " +
                "stopien='" + stopien + '\'' +
                ", kontrakty=" + kontraktyMsg +
                '}';
    }

    public void printKontraktList(){
        StringBuilder msg = new StringBuilder("");
        int i=0;
        String suf = ",\n";
        for (Kontrakt k: kontraktList){
            if(i==getKontraktAmount()-1){
                suf=";\n";
            }
            msg.append(k.toString()).append(suf);
            i+=1;
        }
        System.out.println(msg.toString());
    }

    private int getKontraktAmount() {
        return kontraktList.size();
    }
}
