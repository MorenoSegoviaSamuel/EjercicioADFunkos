package org.ficheros;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
}
