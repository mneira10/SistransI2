package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class IngredientesProductoIndividual {
    @JsonProperty(value="ingrediente")
    private Long ingrediente;
    @JsonProperty(value="producto")
    private Long producto;


    public IngredientesProductoIndividual(@JsonProperty(value="ingrediente")Long ingrediente,
                                          @JsonProperty(value="producto")Long producto) {
        this.ingrediente = ingrediente;
        this.producto = producto;
    }


    public Long getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Long ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Long getProducto() {
        return producto;
    }

    public void setProducto(Long producto) {
        this.producto = producto;
    }
}
