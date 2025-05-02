package root.Inne;

import root.ObjectPlus;
import root.Osoby.Zolnierz;
import root.StrukturyOrganizacyjne.Brygada;
import root.ToStringType;

import java.time.LocalDate;

public class Kontrakt extends ObjectPlus {
    private LocalDate startDate, endDate;
    private Zolnierz zolnierz;
    private Brygada brygada;

    public Kontrakt(Zolnierz zolnierz, Brygada brygada, int naIleLat) {
        try{
            setStartDate(LocalDate.now());
            setEndDate(this.startDate.plusYears(naIleLat));
            setZolnierz(zolnierz);
            setBrygada(brygada);

            this.zolnierz.addKontrakt(this);
            this.brygada.addKontrakt(this);
        }catch(Exception e){
            e.printStackTrace();
            removeFromExtent();
        }

    }

    @Override
    public void removeFromExtent() {
        this.zolnierz.removeKontrakt(this);
        this.brygada.removeKontrakt(this);
        super.removeFromExtent();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate==null){
            throw new IllegalArgumentException("startDate nie moze byc null");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate==null){
            throw new IllegalArgumentException("endDate nie moze byc null");
        }
        this.endDate = endDate;
    }

    public Zolnierz getZolnierz() {
        return zolnierz;
    }

    public void setZolnierz(Zolnierz zolnierz) {
        if(zolnierz==null){
            throw new IllegalArgumentException("zolnierz nie moze byc null");
        }
        this.zolnierz = zolnierz;
    }

    public Brygada getBrygada() {
        return brygada;
    }

    public void setBrygada(Brygada brygada) {
        if(brygada==null){
            throw new IllegalArgumentException("brygada nie moze byc null");
        }
        this.brygada = brygada;
    }

    @Override
    public String toString() {
        ToStringType typB = this.brygada.changeToStringType(ToStringType.SIMPLE);
        String brygadaStr = this.brygada.toString();
        ToStringType typZ = this.zolnierz.changeToStringType(ToStringType.SIMPLE);
        String zolnierzStr = this.zolnierz.toString();
        this.brygada.changeToStringType(typB);
        this.zolnierz.changeToStringType(typZ);
        return "Kontrakt między " + zolnierzStr + " a " +brygadaStr + " na okres [" + this.startDate + " do " + this.endDate+ "]";
    }
}
