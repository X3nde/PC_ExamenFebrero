package com.progcon.pc_examenfebrero.modelo;

import java.util.Random;

public class Dado {
    private final int id;
    private final Random random;

    public Dado(int id) {
        this.id = id;
        this.random = new Random();
    }

    public int lanzar() {
        return random.nextInt(6) + 1; // Número entre 1 y 6
    }

    public int getId() {
        return id;
    }
}
