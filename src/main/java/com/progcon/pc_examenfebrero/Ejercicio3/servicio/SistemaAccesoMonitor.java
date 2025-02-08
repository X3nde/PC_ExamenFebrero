package com.progcon.pc_examenfebrero.Ejercicio3.servicio;

import com.progcon.pc_examenfebrero.Ejercicio3.modelo.Visitante;

import java.util.LinkedList;
import java.util.Queue;

public class SistemaAccesoMonitor {
    private final int numPuertas;
    private final Queue<Visitante> colaVisitantes;
    private int puertasDisponibles;
    private int codigoGenerador;

    public SistemaAccesoMonitor(int numPuertas) {
        this.numPuertas = numPuertas;
        this.puertasDisponibles = numPuertas;
        this.colaVisitantes = new LinkedList<>();
        this.codigoGenerador = 1;
    }

    public synchronized Visitante registrarVisitante() {
        int codigo = codigoGenerador++;
        return new Visitante(codigo);
    }

    public synchronized void accederParque(Visitante visitante) throws InterruptedException {
        colaVisitantes.add(visitante);
        while (colaVisitantes.peek() != visitante || puertasDisponibles <= 0) {
            wait(); // Espera hasta que sea su turno y haya una puerta libre
        }
        colaVisitantes.poll();
        puertasDisponibles--;
        System.out.println("Visitante " + visitante.getCodigo() + " accedió al parque.");
        Thread.sleep(1000); // Simula tiempo de acceso
        liberarPuerta();
    }

    public synchronized void liberarPuerta() {
        puertasDisponibles++;
        notifyAll(); // Notifica a los visitantes en espera
    }
}
