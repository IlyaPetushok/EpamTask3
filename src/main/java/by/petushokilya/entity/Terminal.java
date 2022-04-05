package by.petushokilya.entity;

import by.petushokilya.util.GeneratorId;

public class Terminal {
    private int id;

    public Terminal() {
        this.id = GeneratorId.generatorIdTerminal();
    }

    public int getId() {
        return id;
    }

}
