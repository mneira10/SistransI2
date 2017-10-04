package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Zona {
    @JsonProperty(value="nombre")
    private String nombre;

    @JsonProperty(value="cerrado")
    private Boolean cerrado;

    @JsonProperty(value="tipo")
    private String tipo;

    @JsonProperty(value="aptoDescap")
    private Boolean aptoDescap;

    @JsonProperty(value="capacidad")
    private Integer capacidad;

    public Zona(@JsonProperty(value="nombre")String nombre,
                @JsonProperty(value="cerrado")Boolean cerrado,
                @JsonProperty(value="tipo")String tipo,
                @JsonProperty(value="aptoDescap")Boolean aptoDescap,
                @JsonProperty(value="capacidad")Integer capacidad) {
        this.nombre = nombre;
        this.cerrado = cerrado;
        this.tipo = tipo;
        this.aptoDescap = aptoDescap;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAptoDescap() {
        return aptoDescap;
    }

    public void setAptoDescap(Boolean aptoDescap) {
        this.aptoDescap = aptoDescap;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
