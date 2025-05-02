package root.Inne;

import java.io.Serializable;

public class Adres implements Serializable {
    private String ulica;
    private int numUlica;
    private String miasto;

    public Adres(String ulica, int numUlica, String miasto) {
        setUlica(ulica);
        setNumUlica(numUlica);
        setMiasto(miasto);
    }

    @Override
    public String toString() {
        return ulica +" "+ numUlica +" "+ miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        if(ulica == null || ulica.isBlank()){
            throw new IllegalArgumentException("Ulica nie może być null ani pusta");
        }
        this.ulica = ulica;
    }

    public int getNumUlica() {
        return numUlica;
    }

    public void setNumUlica(int numUlica) {
        if(numUlica<1){
            throw new IllegalArgumentException("Numer ulicy nie może być <1");
        }
        this.numUlica = numUlica;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        if(miasto == null || miasto.isBlank()){
            throw new IllegalArgumentException("Miasto nie może być null ani puste");
        }
        this.miasto = miasto;
    }
}
