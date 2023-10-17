package org.ficheros;

import java.io.Serializable;

public record Funkos(String COD, String nombre, String modelo, double precio, String fecha) implements Serializable {
    
}
