package com.progcon.pc_examenfebrero.Ejercicio1.servicio;

import com.progcon.pc_examenfebrero.Ejercicio1.modelo.Dado;
import com.progcon.pc_examenfebrero.Ejercicio1.modelo.CondicionEspecial;

public class LanzadorDado implements Runnable {
    private final Dado dado;
    private final CondicionEspecial condicion;
    private int resultado;

    public LanzadorDado(Dado dado, CondicionEspecial condicion) {
        this.dado = dado;
        this.condicion = condicion;
    }

    @Override
    public void run() {
        int resultadoInicial = dado.lanzar();
        resultado = condicion.modificarResultado(resultadoInicial);
        System.out.println("Dado " + dado.getId() + ": Resultado inicial = " + resultadoInicial +
                ", Modificado por " + condicion.getNombre() + " = " + resultado);
    }

    public int getResultado() {
        return resultado;
    }
}
