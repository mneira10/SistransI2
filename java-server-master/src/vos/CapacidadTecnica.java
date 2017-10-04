package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CapacidadTecnica {
    @JsonProperty(value="nombre")
    private String nombre;
    @JsonProperty(value="descripcion")
    private String descripcion;
    @JsonProperty(value="zona")
    private String zona;

    public CapacidadTecnica(@JsonProperty(value="nombre")String nombre,
                            @JsonProperty(value="descripcion")String descripcion,
                            @JsonProperty(value="zona")String zona) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.zona = zona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
