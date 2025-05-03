package root.StrukturyOrganizacyjne;

import root.Osoby.Osoba;
import root.ToStringType;

import java.util.*;

public class Sztab {
    String nazwa;
    ToStringType toStringType = ToStringType.DETAILED;
    List<Osoba> czlonkowie = new ArrayList<>();
    Map<String, Osoba> dowodztwo = new HashMap<>(); //SUBSET czlonkowie //rola: osoba
    List<StrukturaOrganizacyjna> podlegajaceJednostki = new ArrayList<>();

    public Sztab(String nazwa) {
        setNazwa(nazwa);
    }

    public void addDowodztwo(Osoba osoba, String rola){
        if(osoba==null) throw new IllegalArgumentException("osoba nie moze byc null");
        if(!czlonkowie.contains(osoba)) throw new IllegalArgumentException("Dowództwo musi być subsetem członków");
        if(dowodztwo.containsValue(osoba))return;
        this.dowodztwo.put(rola, osoba);
    }

    public void addCzlonek(Osoba czlonek) {
        if(czlonek==null) throw new IllegalArgumentException("Czlonek nie moze byc null");
        if(czlonkowie.contains(czlonek)) return;
        this.czlonkowie = czlonkowie;
    }

    public void addPodlegajacaJednostka(StrukturaOrganizacyjna jednostka) {
        if(jednostka==null) throw new IllegalArgumentException("jednostka nie moze byc null");
        if(podlegajaceJednostki.contains(jednostka)) return;
        if(jednostka instanceof Batalion){
            if(((Batalion) jednostka).getTyp()!=BatalionTyp.SAMODZIELNY) throw new IllegalArgumentException("By móc dołączyć batalion pod sztab musi on być typu BatalionTyp.SAMODZIELNY");
        }
        this.podlegajaceJednostki.add(jednostka);
    }

    public void setNazwa(String nazwa) {
        if(nazwa==null || nazwa.isBlank()) throw new IllegalArgumentException("nazwa nie może być null ani blank");
        this.nazwa = nazwa;
    }

    public List<Osoba> getCzlonkowie() {return Collections.unmodifiableList(czlonkowie);}
    public List<StrukturaOrganizacyjna> getPodlegajaceJednostki() {return Collections.unmodifiableList(podlegajaceJednostki);}
    public Map<String, Osoba> getDowodztwo() {return Collections.unmodifiableMap(dowodztwo);}
    public String getNazwa() {return nazwa;}
    public String getSimpleName(){
        ToStringType oldType = this.toStringType;
        this.toStringType=ToStringType.SIMPLE;
        String msg = this.toString();
        this.toStringType = oldType;
        return msg;
    }

    private String czlonkowieToString(){
        if(this.czlonkowie.size()==0) return "brak";
        String result = "[";
        String sep = ", ";
        for (int i = 0; i < this.czlonkowie.size()-1; i++) {
            result+=this.czlonkowie.get(i).getSimpleName()+sep;
        }
        return result + this.czlonkowie.get(this.czlonkowie.size()-1) + "]";
    }

    private String dowodztwoToString(){
        if(this.dowodztwo.size()==0) return "brak";
        String result = "[";
        String sep = ", ";
        for (Map.Entry<String, Osoba> pair:getDowodztwo().entrySet()) {
            result+=pair.getKey()+": "+pair.getValue().getSimpleName()+sep;
        }
        result = result.substring(0, result.length()-1);
        return result + "]";
    }

    private String jednostkiToString(){
        if(this.podlegajaceJednostki.size()==0) return "brak";
        String result = "[";
        String sep = ", ";
        for (int i = 0; i < this.podlegajaceJednostki.size()-1; i++) {
            result+=this.podlegajaceJednostki.get(i).getSimpleName()+sep;
        }
        return result + "]";
    }

    @Override
    public String toString() {
        if(this.toStringType==ToStringType.SIMPLE) return this.nazwa;
        return "Sztab{" +
                "dowodztwo=" + dowodztwoToString() +
                ", czlonkowie=" + czlonkowieToString() +
                ", podlegajaceJednostki=" + jednostkiToString() +
                '}';
    }
}
