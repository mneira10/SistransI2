package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoFacturado {

    /**
     * Id del usuario
     */
    @JsonProperty(value="id")
    private Long id;

    /**
     * Nombres del usuario
     */
    @JsonProperty(value="nombre")
    private String nombre;

    /**
     * tipo de identificacion del usuario
     */

    /**
     * tipo de identificacion del usuario
     */
    @JsonProperty(value="costoTotal")
    private Double costoTotal;

    /**
     * tipo de identificacion del usuario
     */
    @JsonProperty(value="precioTotal")
    private Double precioTotal;

    @JsonProperty(value="categoria")
    private String categoria;

    /**
     * tipo de identificacion del usuario
     */
    @JsonProperty(value="restauranteId")
    private Long restauranteId;

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




    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public ProductoFacturado(Long id, String nombre,  Double costoTotal, Double precioTotal, Long restauranteId, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.costoTotal = costoTotal;
        this.precioTotal = precioTotal;
        this.restauranteId = restauranteId;
    }
}
