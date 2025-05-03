package root;

import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable {
    private static Map<Class, List> extent = new HashMap<>();

    public ObjectPlus(){addToExtent();}
    private static String filename = "extent.ser";
    public static void saveExtent() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(extent);
        }
    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            extent = (Map<Class, List>) ois.readObject();
        }
    }
    public static <T> List<T> getExtentFromClass(Class<T> c){
        extent.computeIfAbsent(c, a -> new ArrayList<>());
        return Collections.unmodifiableList(extent.get(c));
    }
    protected void addToExtent(){
        List list = extent.computeIfAbsent(this.getClass(), a-> new ArrayList<>());
        list.add(this);
//        System.out.println("added to extent:"+this);
    }
    public void removeFromExtent() {
        List list = extent.get(this.getClass());
        if(list!=null){
            list.remove(this);
        }else
            System.out.println("Lista nie istnieje");
    }

/*    public static <T> void printExtent(Class<T> cls){
        System.out.println(getExtentFromClass(cls));
    }*/
public static void printExtent() {
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-EXTENT=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    for (Map.Entry<Class, List> entry : extent.entrySet()) {
        Class<?> clazz = entry.getKey();
        List<?> list = entry.getValue();

        System.out.println("Class: " + clazz.getSimpleName());
        for (Object obj : list) {
            System.out.println("  -> " + obj);
        }
    }
    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
}
}
