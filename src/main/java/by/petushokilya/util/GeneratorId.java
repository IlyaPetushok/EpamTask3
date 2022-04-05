package by.petushokilya.util;

public class GeneratorId {
    public static int idTruck=0;
    public static int idTerminal=0;

    public static int generatorIdTruck(){
        return ++idTruck;
    }

    public static int generatorIdTerminal(){
        return ++idTerminal;
    }
}
