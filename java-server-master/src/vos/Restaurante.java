package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Restaurante {

    @JsonProperty(value="id")
    private long id;
    @JsonProperty(value="nombre")
    private String nombre;
    @JsonProperty(value="tipo")
    private String tipo;
    @JsonProperty(value="paginaWeb")
    private String paginaWeb;
    @JsonProperty(value="zonaID")
    private String zonaId;

    public Restaurante(long id, String nombre, String tipo, String paginaWeb, String zonaId) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.paginaWeb = paginaWeb;
        this.zonaId = zonaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getZonaId() {
        return zonaId;
    }

    public void setZonaId(String zonaId) {
        this.zonaId = zonaId;
    }
}
