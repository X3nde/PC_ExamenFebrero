package com.progcon.pc_examenfebrero.Ejercicio3.servicio;

import com.progcon.pc_examenfebrero.Ejercicio3.modelo.Visitante;

import java.util.LinkedList;
import java.util.Queue;

public class SistemaAtraccionesMonitor {
    private final int capacidadPorAtraccion;
    private final Queue<Visitante>[] colasAtracciones;

    @SuppressWarnings("unchecked")
    public SistemaAtraccionesMonitor(int numAtracciones, int capacidadPorAtraccion) {
        this.capacidadPorAtraccion = capacidadPorAtraccion;
        this.colasAtracciones = new Queue[numAtracciones];
        for (int i = 0; i < numAtracciones; i++) {
            colasAtracciones[i] = new LinkedList<>();
        }
    }

    public synchronized void accederAtraccion(Visitante visitante, int numAtraccion) throws InterruptedException {
        Queue<Visitante> cola = colasAtracciones[numAtraccion];
        cola.add(visitante);
        while (cola.peek() != visitante || capacidadPorAtraccion <= 0) {
            wait(); // Espera hasta que sea su turno y haya espacio
        }
        cola.poll();
        System.out.println("Visitante " + visitante.getCodigo() + " está disfrutando de la atracción " + numAtraccion + ".");
        Thread.sleep(2000); // Simula tiempo en la atracción
        liberarAtraccion(numAtraccion);
    }

    public synchronized void liberarAtraccion(int numAtraccion) {
        capacidadPorAtraccion++;
        notifyAll(); // Notifica a todos los visitantes en espera
    }
}
