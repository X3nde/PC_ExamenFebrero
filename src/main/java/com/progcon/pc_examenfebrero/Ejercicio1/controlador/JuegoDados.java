package com.progcon.pc_examenfebrero.Ejercicio1.controlador;

import com.progcon.pc_examenfebrero.Ejercicio1.controlador.modelo.Dado;
import com.progcon.pc_examenfebrero.Ejercicio1.controlador.modelo.CondicionEspecial;
import com.progcon.pc_examenfebrero.Ejercicio1.controlador.servicio.LanzadorDado;
import com.progcon.pc_examenfebrero.Ejercicio1.controlador.servicio.AnalizadorResultados;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JuegoDados {
    private final int numDados;

    public JuegoDados(int numDados) {
        this.numDados = numDados;
    }

    public void jugar() {
        ExecutorService executor = Executors.newFixedThreadPool(numDados);
        List<LanzadorDado> lanzadores = new ArrayList<>();
        List<Integer> resultados = new ArrayList<>();

        // Crear dados y condiciones
        for (int i = 0; i < numDados; i++) {
            Dado dado = new Dado(i + 1);
            CondicionEspecial condicion = new CondicionEspecial("Condición " + (i + 1));
            LanzadorDado lanzador = new LanzadorDado(dado, condicion);
            lanzadores.add(lanzador);
            executor.execute(lanzador);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {}

        for (LanzadorDado lanzador : lanzadores) {
            resultados.add(lanzador.getResultado());
        }

        AnalizadorResultados analizador = new AnalizadorResultados();
        System.out.println("Promedio: " + analizador.calcularPromedio(resultados));
        System.out.println("Moda: " + analizador.calcularModa(resultados));
    }
}
