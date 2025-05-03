package root.StrukturyOrganizacyjne;

import root.ObjectPlus;
import root.ToStringType;

public abstract class StrukturaOrganizacyjna extends ObjectPlus {
    int numer;
    public ToStringType toStringType=ToStringType.DETAILED;
    protected static final String suffix = "Obrony Terytorialnej";

    public StrukturaOrganizacyjna(int numer) {
        setNumer(numer);
    }


    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        if (numer<1){
            throw new IllegalArgumentException("numBrygady musi być większy lub równy 1");
        }
        this.numer = numer;
    }

    public String getSimpleName(){
        ToStringType oldType = this.toStringType;
        this.toStringType=ToStringType.SIMPLE;
        String msg = this.toString();
        this.toStringType = oldType;
        return msg;
    }

    public ToStringType getToStringType() {
        return toStringType;
    }
}
