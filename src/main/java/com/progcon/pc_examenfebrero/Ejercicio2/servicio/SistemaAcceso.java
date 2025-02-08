package com.progcon.pc_examenfebrero.Ejercicio2.servicio;

import com.progcon.pc_examenfebrero.Ejercicio2.modelo.Visitante;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SistemaAcceso {
    private final Semaphore puertas;
    private final AtomicInteger codigoGenerador;

    public SistemaAcceso(int numPuertas) {
        this.puertas = new Semaphore(numPuertas, true);
        this.codigoGenerador = new AtomicInteger(1);
    }

    public Visitante registrarVisitante() {
        int codigo = codigoGenerador.getAndIncrement();
        return new Visitante(codigo);
    }

    public void accederParque(Visitante visitante) {
        try {
            System.out.println("Visitante " + visitante.getCodigo() + " esperando para acceder al parque.");
            puertas.acquire(); // Espera por una puerta libre
            System.out.println("Visitante " + visitante.getCodigo() + " accedió al parque.");
            Thread.sleep(1000); // Simula el tiempo de acceso
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            puertas.release(); // Libera la puerta
        }
    }
}
