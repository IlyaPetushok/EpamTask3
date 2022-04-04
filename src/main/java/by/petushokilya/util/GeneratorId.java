package by.petushokilya.util;

public class GeneratorId {
    public static int id=0;

    public static int generatorId(){
        return ++id;
    }
}
