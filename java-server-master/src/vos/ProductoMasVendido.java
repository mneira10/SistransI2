package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoMasVendido extends Producto {

    @JsonProperty(value="zona")
    private String zona;
    @JsonProperty(value="cantVendido")
    private Long cantVendido;


    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Long getCantVendido() {
        return cantVendido;
    }

    public void setCantVendido(Long cantVendido) {
        this.cantVendido = cantVendido;
    }

    public ProductoMasVendido(Long id, String name, String descrEsp, String descrIng, Double tPrep, Double costo, Double precio, Long restauranteId, String zona, Long cantVendido) {
        super(id, name, descrEsp, descrIng, tPrep, costo, precio, restauranteId);
        this.zona = zona;
        this.cantVendido = cantVendido;
    }
}
