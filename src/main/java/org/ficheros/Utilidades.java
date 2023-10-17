package org.ficheros;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Utilidades {

    public static List<Funkos> listaFunkos(List<List<String>> csv){
        return csv.stream().map(parametros -> new Funkos(parametros.get(0), parametros.get(1), parametros.get(2), Double.parseDouble(parametros.get(3)), parametros.get(4)))
                .collect(Collectors.toList());
    }

    public static Funkos funkoMasCaro (List<Funkos> lista) {
        return lista.stream()
                .max(Comparator.comparingDouble(Funkos::precio)).get();
    }
    public static Double mediaPrecios (List<Funkos> lista){
        return lista.stream().collect(averagingDouble(f -> f.precio()));
    }
    public static Map<String,List<Funkos>> agruparPorModelos (List <Funkos> lista){
        return lista.stream().collect(groupingBy(Funkos::modelo));
    }
    public static Stream<Map.Entry<String, Long>> agruparPorModelosContar (List <Funkos> lista){
        return lista.stream().collect(Collectors.groupingBy(Funkos::modelo, Collectors.counting())).entrySet().stream();
    }
    public static void funkos2023 (List <Funkos> lista){
        lista.stream().filter(f -> f.fecha().startsWith("2023")).map(Funkos::nombre).forEach(System.out::println);
    }

    public static void backUp (List <Funkos> lista) {
        Path funkos = Path.of("funkos.csv");

        try (FileOutputStream FicheroSalida = new FileOutputStream(funkos.toFile());
             ObjectOutputStream ObjetoSalida = new ObjectOutputStream(FicheroSalida)) {

            ObjetoSalida.writeObject(lista);

            ObjetoSalida.close();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public static void restore(){
        Path funkos = Path.of("funkos.csv");
        try (FileInputStream FicheroEntrada = new FileInputStream(funkos.toFile());
             ObjectInputStream ObjetoEntrada = new ObjectInputStream(FicheroEntrada)) {

            List <Funkos> lista2 = (List<Funkos>) ObjetoEntrada.readObject();
            lista2.stream().forEach(System.out::println);

            ObjetoEntrada.close();

        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
