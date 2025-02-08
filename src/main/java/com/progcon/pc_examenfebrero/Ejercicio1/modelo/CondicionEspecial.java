package com.progcon.pc_examenfebrero.Ejercicio1.modelo;

import java.util.Random;

public class CondicionEspecial {
    private final String nombre;
    private final Random random;

    public CondicionEspecial(String nombre) {
        this.nombre = nombre;
        this.random = new Random();
    }

    public int modificarResultado(int resultado) {
        return Math.max(1, resultado + random.nextInt(3) - 1); // Ajuste entre -1 y +2
    }

    public String getNombre() {
        return nombre;
    }
}
