package org.ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Ejercicio2 {
    public static void main(String[] args) throws IOException {
        Path funkos = Path.of("funkos.csv");
        String COMMA_DELIMITER = ",";

        List<List<String>> csv = Files.lines(Paths.get("/home/samseg/Escritorio/AccesoDatos-2023-2024-main/UD02-Ficheros/01-ManejoFicheros/src/main/resources/funkos.csv")).skip(1)
                .map(linea -> Arrays.asList(linea.split(COMMA_DELIMITER)))
                .toList();

        System.out.println("");
        List<Funkos> listaFunkos = Utilidades.listaFunkos(csv);
        Funkos funkoParaRicos = Utilidades.funkoMasCaro(listaFunkos);
        System.out.println("|-Funko más caro-|");
        System.out.println(funkoParaRicos.nombre());
        System.out.println("");
        Double mediaFunkos = Utilidades.mediaPrecios(listaFunkos);
        System.out.println("|-Media de los precios-|");
        System.out.println(mediaFunkos);
        System.out.println("");
        System.out.println("|-Funkos agrupados por modelos-|");
        Map<String , List<Funkos>> FunkosAgrupados = Utilidades.agruparPorModelos(listaFunkos);


        FunkosAgrupados.forEach((modelo, fun) -> {
            System.out.println("MODELO:"+ modelo);
            fun.forEach(f -> {
                System.out.println(f.nombre());
            });
            System.out.println("");
        });
    }
}
