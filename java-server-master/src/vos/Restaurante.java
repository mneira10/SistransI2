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
    @JsonProperty(value="idAdmin")
    private long idAdmin;

    public Restaurante(@JsonProperty(value="id")long id,
                       @JsonProperty(value="nombre")String nombre,
                       @JsonProperty(value="tipo")String tipo,
                       @JsonProperty(value="paginaWeb")String paginaWeb,
                       @JsonProperty(value="zonaID")String zonaId,
                       @JsonProperty(value="idAdmin")long idAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.paginaWeb = paginaWeb;
        this.zonaId = zonaId;
        this.idAdmin=idAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(long id) {
        this.idAdmin = id;
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
