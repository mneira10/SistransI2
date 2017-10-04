package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Item {
    @JsonProperty(value="id")
    private Long id;
    @JsonProperty(value="personalizacion")
    private String personalizacion;
    @JsonProperty(value="productoId")
    private Long productoId;
    @JsonProperty(value="carritoId")
    private Long carritoId;


    public Item(@JsonProperty(value="id")Long id,
                @JsonProperty(value="personalizacion")String personalizacion,
                @JsonProperty(value="productoId")Long productoId,
                @JsonProperty(value="carritoId")Long carritoId) {
        this.id = id;
        this.personalizacion = personalizacion;
        this.productoId = productoId;
        this.carritoId = carritoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonalizacion() {
        return personalizacion;
    }

    public void setPersonalizacion(String personalizacion) {
        this.personalizacion = personalizacion;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }
}
