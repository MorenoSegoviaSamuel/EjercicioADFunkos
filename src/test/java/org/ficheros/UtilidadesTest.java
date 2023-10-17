package org.ficheros;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UtilidadesTest {


    private static List<Funkos> funkos;

    @BeforeAll
    public static void CrearLista() {funkos = new ArrayList<>();
       funkos.add(new Funkos("1","a","x",15,"2023-10-2"));
       funkos.add(new Funkos("2","b","y",5,"2020-10-2"));
       funkos.add(new Funkos("3","c","x",10,"2022-10-2"));;

    }

    @Test
    void testFunkoMasCaro() {
        Funkos funkoCaro = Utilidades.funkoMasCaro(funkos);
        assertEquals(funkos.get(0),funkoCaro);
    }

    @Test
    void testMediaPrecios() {
        double media = Utilidades.mediaPrecios(funkos);
        assertEquals(10, media);
    }

    @Test
    void testAgruparPorModelos() {
        Map<String,List<Funkos>> funkosAgrupados = Utilidades.agruparPorModelos(funkos);
        assertEquals(2, funkosAgrupados.get("x").size());
        assertEquals(1, funkosAgrupados.get("y").size());
    }

    @Test
    void testAgruparPorModelosContar() {
        Stream<Map.Entry<String, Long>> result = Utilidades.agruparPorModelosContar(funkos);
        List<Map.Entry<String, Long>> resultList = result.collect(Collectors.toList());

        // Verificar el contenido del Stream resultante
        assertEquals(2, resultList.size());
        assertEquals("x", resultList.get(0).getKey());
        assertEquals(2, resultList.get(0).getValue());
        assertEquals("y", resultList.get(1).getKey());
        assertEquals(1, resultList.get(1).getValue());
    }

    @Test
    void testFunkos2023() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Utilidades.funkos2023(funkos);
        String output = outputStreamCaptor.toString().trim();

        assertEquals("a", output);
    }
}
