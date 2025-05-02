package root.Inne;

import root.ObjectPlus;
import root.Osoby.Zolnierz;
import root.ToStringType;

public class KsiazeczkaWojskowa extends ObjectPlus {
    private String numerSeryjny;
    private Zolnierz zolnierz;
    private ToStringType toStringType = ToStringType.SIMPLE;

    public KsiazeczkaWojskowa(String numerSeryjny, Zolnierz zolnierz){
        try{
            setNumerSeryjny(numerSeryjny);
            setZolnierz(zolnierz);
        }catch(Exception e){
            e.printStackTrace();
            removeFromExtent();
        }

    }

    @Override
    public void removeFromExtent() {
        if(this.zolnierz!=null){
            Zolnierz z = this.zolnierz;
            this.zolnierz = null;
            z.removeKsiazeczkaWojskowa();
        }
        super.removeFromExtent();
    }

    public String getNumerSeryjny() {
        return numerSeryjny;
    }

    private void setNumerSeryjny(String numerSeryjny) {
        if(numerSeryjny==null) throw new IllegalArgumentException("Numer seryjny nie moze byc null");
        this.numerSeryjny = numerSeryjny;
    }

    public Zolnierz getZolnierz() {
        return zolnierz;
    }
    private void setZolnierz(Zolnierz zolnierz) {
        if(zolnierz==null){
            throw new IllegalArgumentException("zolnierz nie moze byc null");
        }
        this.zolnierz = zolnierz;
    }


    @Override
    public String toString() {
        ToStringType zolTyp = this.zolnierz.changeToStringType(ToStringType.SIMPLE);
        String zolMsg = this.zolnierz.toString();
        this.zolnierz.changeToStringType(zolTyp);
        return "Ksiazeczka Wojskowa nr. "+ this.numerSeryjny + " nal. do "+ zolMsg;
    }
}
