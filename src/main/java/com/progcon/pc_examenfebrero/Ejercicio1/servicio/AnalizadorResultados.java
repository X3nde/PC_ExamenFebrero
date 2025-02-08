package com.progcon.pc_examenfebrero.Ejercicio1.servicio;

import java.util.List;
import java.util.stream.Collectors;

public class AnalizadorResultados {
    public double calcularPromedio(List<Integer> resultados) {
        return resultados.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public int calcularModa(List<Integer> resultados) {
        return resultados.stream()
                .collect(Collectors.groupingBy(r -> r, Collectors.counting()))
                .entrySet().stream()
                .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .map(e -> e.getKey()).orElse(0);
    }
}
