package root.StrukturyOrganizacyjne;

import root.Inne.Kontrakt;
import root.Osoby.Zolnierz;
import root.ToStringType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Brygada extends StrukturaOrganizacyjna {
    //brygada ma przypisane miasto którego broni i składa się z <=3 batalionów.
    //zolnierz moze zawrzec kontrakt z brygada

    //asocjacja zwykla: brygada 1-*batalion
    //asocjacja kwalifikowana: brygada 1-* kontrakt *-1 zolnierz
    private String typ, miasto;
    private List<Batalion> bataliony = new ArrayList<>();
    private List<Kontrakt> kontraktList = new ArrayList<>();

    public Brygada(int numer, String typ, String miasto) {
        super(numer);
        setTyp(typ);
        setMiasto(miasto);
    }
//-------------------------------------------------BATALION----------------------------------------------------------------
    public void addBatalion(Batalion batalion){
        if(batalion==null)
            throw new IllegalArgumentException("batalion nie może być null");
        if(this.bataliony.contains(batalion)){
            ToStringType typeBryg = this.changeToStringType(ToStringType.SIMPLE);
            ToStringType typeBat = batalion.changeToStringType(ToStringType.SIMPLE);
            System.out.println(this+ " ma już przypisany batalion "+batalion);
            this.changeToStringType(typeBryg);
            batalion.changeToStringType(typeBat);
            return ;
        }
        if(this.bataliony.size()==3){
            System.out.println("Brygada ma już maksymalną liczbę batalionów");
            return;
        }
        this.bataliony.add(batalion);
        batalion.setBrygadaMacierzysta(this);
    }

    public void removeBatalion(Batalion batalion){
        if(batalion==null) {
            throw new IllegalArgumentException("batalion nie może być null");
        }

        ToStringType typeBat = batalion.changeToStringType(ToStringType.SIMPLE);
        ToStringType typeBryg = this.changeToStringType(ToStringType.SIMPLE);

        if(this.bataliony.contains(batalion)){
            System.out.println("Usuwam batalion "+ batalion+ " z "+this);
            this.changeToStringType(typeBryg);
            batalion.changeToStringType(typeBat);

            this.bataliony.remove(batalion);
            batalion.removeBrygadaMacierzysta();
        }else{
            System.out.println("Brygada "+this+" nie ma przypisanego batalionu "+batalion);
            this.changeToStringType(typeBryg);
            batalion.changeToStringType(typeBat);
        }
    }
    public List<Batalion> getBataliony() {
        return Collections.unmodifiableList(bataliony);
    }

    //-------------------------------------------------ZOLNIERZ----------------------------------------------------------------
    public void addZolnierz(Zolnierz zolnierz, int naIleLat){
        new Kontrakt(zolnierz, this, naIleLat);
    }

    public void removeZolnierz(Zolnierz zolnierz){
        if (zolnierz == null) {
            throw new IllegalArgumentException("Zolnierz nie może być null");
        }
        List<Kontrakt> toRemove = kontraktList.stream().filter(z -> z.getZolnierz()==zolnierz).toList();
        toRemove.forEach(Kontrakt::removeFromExtent);
    }


    //-------------------------------------------------KONTRAKT----------------------------------------------------------------

    public void addKontrakt(Kontrakt kontrakt){
        if(kontrakt==null){
            throw new IllegalArgumentException("Kontrakt nie moze byc null");
        }
        if(!kontraktList.contains(kontrakt)){
            kontraktList.add(kontrakt);
            System.out.println("[Brygada] dodano kontrakt dla "+kontrakt.getZolnierz());
        }
    }
    public void removeKontrakt(Kontrakt kontrakt) {
        if(kontrakt==null){
            throw new IllegalArgumentException("Kontrakt nie moze byc null");
        }
        this.kontraktList.remove(kontrakt);
    }
    public List<Kontrakt> getKontraktList() {
        return Collections.unmodifiableList(kontraktList);
    }
    public int getKontraktAmount(){
        return kontraktList.size();
    }

//-----------------------------------------------------------------------------------------------------------------
    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        if(miasto==null || miasto.isBlank()){
            throw new IllegalArgumentException("miasto nie może być null ani blank");
        }
        this.miasto = miasto;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        if(typ==null || typ.isBlank()){
            throw new IllegalArgumentException("typ nie może być null ani blank");
        }
        this.typ = typ;
    }

//-----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        if(this.toStringType== ToStringType.SIMPLE){
            return numer + " " + typ + " Brygada " + suffix ;
        }else{
            String batalionyMsg="[";
            if(this.bataliony.isEmpty()) {
                batalionyMsg = "brak";
            }else{
                int i = 0;
                String end = ", ";
                for(Batalion b: bataliony){
                    if(i==bataliony.size()-1){
                        end="]";
                    }
                    ToStringType type = b.changeToStringType(ToStringType.SIMPLE);
                    batalionyMsg+=b.toString()+end;
                    b.changeToStringType(type);
                    i++;
                }
            }
            return "Brygada{" +
                    "numer=" + numer +
                    ", typ='" + typ + '\'' +
                    ", miasto='" + miasto + '\'' +
                    ", bataliony=" + batalionyMsg +
                    ", liczbaKontraktow=" + getKontraktAmount() +
                    '}';
        }
    }

    public void printKontraktList(){
        StringBuilder msg = new StringBuilder("Kontrakty:[\n");
        int i=0;
        String suf = ",\n";
        for (Kontrakt k: kontraktList){
            if(i==getKontraktAmount()-1){
                suf="]\n";
            }
            msg.append(k.toString()).append(suf);
            i+=1;
        }
        System.out.println(msg.toString());
    }

}
