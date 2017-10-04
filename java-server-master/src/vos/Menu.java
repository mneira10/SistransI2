package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu {
    @JsonProperty(value="id_producto")
    private Long id_producto;

    public Menu(@JsonProperty(value="id_producto") Long id_producto) {
        this.id_producto = id_producto;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }
}
