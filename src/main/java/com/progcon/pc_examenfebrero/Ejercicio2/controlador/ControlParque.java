package com.progcon.pc_examenfebrero.Ejercicio2.controlador;

import com.progcon.pc_examenfebrero.Ejercicio2.modelo.Visitante;
import com.progcon.pc_examenfebrero.Ejercicio2.servicio.SistemaAcceso;
import com.progcon.pc_examenfebrero.Ejercicio2.servicio.SistemaAtracciones;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlParque {
    public static void main(String[] args) {
        int numPuertas = 3;
        int numAtracciones = 2;
        int capacidadAtraccion = 5;
        int numVisitantes = 20;

        SistemaAcceso sistemaAcceso = new SistemaAcceso(numPuertas);
        SistemaAtracciones sistemaAtracciones = new SistemaAtracciones(numAtracciones, capacidadAtraccion);

        ExecutorService executor = Executors.newFixedThreadPool(numVisitantes);

        for (int i = 0; i < numVisitantes; i++) {
            executor.execute(() -> {
                Visitante visitante = sistemaAcceso.registrarVisitante();
                sistemaAcceso.accederParque(visitante);

                // El visitante accede a dos atracciones aleatorias
                for (int j = 0; j < 2; j++) {
                    int numAtraccion = (int) (Math.random() * numAtracciones);
                    sistemaAtracciones.accederAtraccion(visitante, numAtraccion);
                }

                System.out.println("Visitante " + visitante.getCodigo() + " terminó su visita al parque.");
            });
        }

        executor.shutdown();
    }
}
