package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingrediente {
    @JsonProperty(value="id")
    private Long id;
    @JsonProperty(value="nombre")
    private String nombre;
    @JsonProperty(value="descrEsp")
    private String descrEsp;
    @JsonProperty(value="descrIng")
    private String descrIng;
    @JsonProperty(value="grupo")
    private Integer grupo;
    @JsonProperty(value="restauranteId")
    private Long restauranteId;


    public Ingrediente(Long id, String nombre, String descrEsp, String descrIng, Integer grupo, Long restauranteId) {
        this.id = id;
        this.nombre = nombre;
        this.descrEsp = descrEsp;
        this.descrIng = descrIng;
        this.grupo = grupo;
        this.restauranteId = restauranteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrEsp() {
        return descrEsp;
    }

    public void setDescrEsp(String descrEsp) {
        this.descrEsp = descrEsp;
    }

    public String getDescrIng() {
        return descrIng;
    }

    public void setDescrIng(String descrIng) {
        this.descrIng = descrIng;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }
}
