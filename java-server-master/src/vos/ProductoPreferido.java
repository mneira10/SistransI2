package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoPreferido {
    @JsonProperty(value="usuarioRegistrado")
    private Long usuarioRegistrado;
    @JsonProperty(value="proucto")
    private Long producto;


    public ProductoPreferido(@JsonProperty(value="usuarioRegistrado")Long usuarioRegistrado,
                             @JsonProperty(value="proucto")Long producto) {
        this.usuarioRegistrado = usuarioRegistrado;
        this.producto = producto;
    }


    public Long getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(Long usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }
}
