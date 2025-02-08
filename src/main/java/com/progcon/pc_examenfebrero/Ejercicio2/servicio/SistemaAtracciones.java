package com.progcon.pc_examenfebrero.Ejercicio2.servicio;

import com.progcon.pc_examenfebrero.Ejercicio2.modelo.Visitante;

import java.util.concurrent.Semaphore;

public class SistemaAtracciones {
    private final Semaphore[] atracciones;

    public SistemaAtracciones(int numAtracciones, int capacidadAtraccion) {
        atracciones = new Semaphore[numAtracciones];
        for (int i = 0; i < numAtracciones; i++) {
            atracciones[i] = new Semaphore(capacidadAtraccion, true);
        }
    }

    public void accederAtraccion(Visitante visitante, int numAtraccion) {
        try {
            System.out.println("Visitante " + visitante.getCodigo() + " esperando para entrar a la atracción " + numAtraccion + ".");
            atracciones[numAtraccion].acquire(); // Espera por espacio en la atracción
            System.out.println("Visitante " + visitante.getCodigo() + " está disfrutando de la atracción " + numAtraccion + ".");
            Thread.sleep(2000); // Simula tiempo en la atracción
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            atracciones[numAtraccion].release(); // Libera el espacio en la atracción
            System.out.println("Visitante " + visitante.getCodigo() + " salió de la atracción " + numAtraccion + ".");
        }
    }
}
