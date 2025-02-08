package com.progcon.pc_examenfebrero.Ejercicio1.controlador;

import com.progcon.pc_examenfebrero.Ejercicio1.servicio.AnalizadorResultados;
import com.progcon.pc_examenfebrero.Ejercicio1.servicio.LanzadorDado;
import com.progcon.pc_examenfebrero.Ejercicio1.modelo.Dado;
import com.progcon.pc_examenfebrero.Ejercicio1.modelo.CondicionEspecial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JuegoDados {

    public int[] tiradaDados(int numDados) {
        // Validación del número de dados
        if (numDados <= 0) {
            throw new IllegalArgumentException("El número de dados debe ser mayor que cero.");
        }

        ExecutorService executor = Executors.newFixedThreadPool(numDados); // Pool de hilos
        List<LanzadorDado> lanzadores = new ArrayList<>(); // Lista de lanzadores
        List<Integer> resultados = new ArrayList<>(); // Lista de resultados

        try {
            // Crear y ejecutar hilos
            for (int i = 0; i < numDados; i++) {
                Dado dado = new Dado(i + 1); // Crear dado
                CondicionEspecial condicion = new CondicionEspecial("Condición " + (i + 1)); // Crear condición
                LanzadorDado lanzador = new LanzadorDado(dado, condicion); // Crear lanzador
                lanzadores.add(lanzador);
                executor.execute(lanzador); // Ejecutar hilo
            }

            executor.shutdown(); // Finalizar ejecución
            executor.awaitTermination(1, TimeUnit.MINUTES); // Esperar a que terminen los hilos

            // Recoger los resultados
            for (LanzadorDado lanzador : lanzadores) {
                resultados.add(lanzador.getResultado());
            }

            // Análisis de resultados
            AnalizadorResultados analizador = new AnalizadorResultados();
            System.out.println("Resultados: " + resultados);
            System.out.println("Promedio: " + analizador.calcularPromedio(resultados));
            System.out.println("Moda: " + analizador.calcularModa(resultados));

            // Convertir resultados a un array y devolver
            return resultados.stream().mapToInt(Integer::intValue).toArray();

        } catch (Exception e) {
            System.err.println("Ocurrió un error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
            return new int[0]; // Devolver un array vacío en caso de error
        }
    }
}
