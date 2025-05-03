package root;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public class ObjectPlusPlus extends ObjectPlus  implements Serializable {
    private Hashtable<String, HashMap<Object, ObjectPlusPlus>> powiazania = new Hashtable<String, HashMap<Object, ObjectPlusPlus>>();
    private List<String> roleXOR = new LinkedList<String>();
    public void dodajRoleXOR(String nazwaROliXor) { roleXOR.add(nazwaROliXor);}
    private static HashSet<ObjectPlusPlus> wszystkieCzesci = new HashSet<ObjectPlusPlus>();
    public ObjectPlusPlus(){
        super();
    }

    public void dodajPowiazanie_xor(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy) throws Exception{
        if(roleXOR.contains(nazwaRoli)){
            if(czyIstniejePowiazanie()) throw new Exception("Istnieje juz powiazanie w ramach rol obietych ograniczeniem XOR");
            dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy);
        }
    }
    private boolean czyIstniejePowiazanie(){
        for(String rola: roleXOR){
            if(this.czySaPowiazania(rola)) return true;
        }return false;
    }

    public boolean czySaPowiazania(String rola){
        if(this.powiazania.containsKey(rola)){
            HashMap<Object, ObjectPlusPlus> powiazaniaObiektu = powiazania.get(rola);
            return !powiazaniaObiektu.isEmpty();
        }
        return false;
    }

    //SUBSET
    public void dodajPowiazanie_subset(String nazwaRoli, String odwrotnaNazwaRoli, String nazwaRoliNadrzednej, ObjectPlusPlus obiektDocelowy) throws Exception{
        if(czyIstniejePowiazanie(nazwaRoliNadrzednej, obiektDocelowy)){
            //istnieje powiazanie do dodawanego obiektu w ramach roli nadrzednej wiec mozna utworzyc nowe powiazanie
            dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy);
        }else{
            //brak powiazania do dodawanego obiektu w ramach roli nadrzednej wiec wyjatek
            throw new Exception("Brak powiazania do dodawanego obiektu \'" + obiektDocelowy + "\' w ramach roli nadrzednej \'" + nazwaRoliNadrzednej +"\'");
        }
    }

    public boolean czyIstniejePowiazanie(String nazwaRoli, ObjectPlusPlus obiektDocelowy) throws Exception{ //do subset
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu = getPowiazaniaObiektu(nazwaRoli);
        if(powiazaniaObiektu.containsValue(obiektDocelowy)) return true;
        return false;
    }
    private void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy, Object kwalifikator, int licznik){
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;

        if(licznik < 1) return;
        if(powiazania.containsKey(nazwaRoli)){
            powiazaniaObiektu = powiazania.get(nazwaRoli);
        }
        else{
            powiazaniaObiektu = new HashMap<Object, ObjectPlusPlus>();
            powiazania.put(nazwaRoli, powiazaniaObiektu);
        }
        if(!powiazaniaObiektu.containsKey(kwalifikator)){
            powiazaniaObiektu.put(kwalifikator, obiektDocelowy); // dodaj pow. dla tego obiektu
            obiektDocelowy.dodajPowiazanie(odwrotnaNazwaRoli, nazwaRoli, this, this, licznik-1);
        }
    }

    public void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy, Object kwalifikator){
        dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, kwalifikator, 2);
     }

    public void dodajPowiazanie(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektDocelowy){
        dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektDocelowy, obiektDocelowy);
    }

    public void dodajCzesc(String nazwaRoli, String odwrotnaNazwaRoli, ObjectPlusPlus obiektCzesc) throws Exception{
        if(wszystkieCzesci.contains(obiektCzesc)) throw new Exception("Ta czesc jest juz powiazana z jakas caloscia");
        dodajPowiazanie(nazwaRoli, odwrotnaNazwaRoli, obiektCzesc);
        wszystkieCzesci.add(obiektCzesc);
    }

    public ObjectPlusPlus[] dajPowiazania(String nazwaRoli) throws Exception{
//        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;
//        if (!powiazania.containsKey(nazwaRoli)) throw new Exception("Brak powiazan dla roli "+ nazwaRoli);
//        powiazaniaObiektu = powiazania.get(nazwaRoli);
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu = getPowiazaniaObiektu(nazwaRoli);
        return (ObjectPlusPlus[]) powiazaniaObiektu.values().toArray(new ObjectPlusPlus[0]);
    }

    public void wyswietlPowiazania(String nazwaRoli, PrintStream stream) throws Exception{
//        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;
//        if (!powiazania.containsKey(nazwaRoli)) throw new Exception("Brak powiazan dla roli "+ nazwaRoli);
//        powiazaniaObiektu = powiazania.get(nazwaRoli);
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu = getPowiazaniaObiektu(nazwaRoli);
        Collection col = powiazaniaObiektu.values();
        stream.println(this.getClass().getSimpleName() + " powiazania w roli " + nazwaRoli+":");
        for(Object obj:col) stream.println("    " + obj);
    }

    public ObjectPlusPlus dajPowiazanyObiekt(String nazwaRoli, Object kwalifikator) throws Exception{
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu = getPowiazaniaObiektu(nazwaRoli);
        if(!powiazaniaObiektu.containsKey(kwalifikator)) throw new Exception("Brak powiazan dla kwalifikatora: " + kwalifikator);
        return powiazaniaObiektu.get(kwalifikator);
    }

    private HashMap<Object, ObjectPlusPlus> getPowiazaniaObiektu(String nazwaRoli) throws Exception{
        HashMap<Object, ObjectPlusPlus> powiazaniaObiektu;
        if (!powiazania.containsKey(nazwaRoli)) throw new Exception("Brak powiazan dla roli "+ nazwaRoli);
        return powiazania.get(nazwaRoli);
    }
}
