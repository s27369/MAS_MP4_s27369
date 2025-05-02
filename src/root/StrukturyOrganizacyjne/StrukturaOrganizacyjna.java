package root.StrukturyOrganizacyjne;

import root.ObjectPlus;
import root.ToStringType;

public abstract class StrukturaOrganizacyjna extends ObjectPlus {
    int numer;
    public ToStringType toStringType=ToStringType.SIMPLE;
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

    public void changeToStringType(){
        if(this.toStringType==ToStringType.SIMPLE)
            this.toStringType=ToStringType.DETAILED;
        else
            this.toStringType=ToStringType.SIMPLE;
    }
    public ToStringType changeToStringType(ToStringType type){
        ToStringType oldType = this.toStringType;
        this.toStringType=type;
        return oldType;
    }

    public ToStringType getToStringType() {
        return toStringType;
    }
}
