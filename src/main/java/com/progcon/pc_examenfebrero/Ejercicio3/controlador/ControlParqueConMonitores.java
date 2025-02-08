package com.progcon.pc_examenfebrero.Ejercicio3.controlador;

import com.progcon.pc_examenfebrero.Ejercicio3.modelo.Visitante;
import com.progcon.pc_examenfebrero.Ejercicio3.servicio.SistemaAccesoMonitor;
import com.progcon.pc_examenfebrero.Ejercicio3.servicio.SistemaAtraccionesMonitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlParqueConMonitores {
    public static void main(String[] args) {
        int numPuertas = 3;
        int numAtracciones = 2;
        int capacidadAtraccion = 5;
        int numVisitantes = 20;

        SistemaAccesoMonitor sistemaAcceso = new SistemaAccesoMonitor(numPuertas);
        SistemaAtraccionesMonitor sistemaAtracciones = new SistemaAtraccionesMonitor(numAtracciones, capacidadAtraccion);

        ExecutorService executor = Executors.newFixedThreadPool(numVisitantes);

        for (int i = 0; i < numVisitantes; i++) {
            executor.execute(() -> {
                try {
                    Visitante visitante = sistemaAcceso.registrarVisitante();
                    sistemaAcceso.accederParque(visitante);

                    // El visitante accede a dos atracciones aleatorias
                    for (int j = 0; j < 2; j++) {
                        int numAtraccion = (int) (Math.random() * numAtracciones);
                        sistemaAtracciones.accederAtraccion(visitante, numAtraccion);
                    }

                    System.out.println("Visitante " + visitante.getCodigo() + " terminó su visita al parque.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
