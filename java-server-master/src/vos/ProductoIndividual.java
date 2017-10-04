package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoIndividual {
    @JsonProperty(value="prodId")
    private Long prodId;
    @JsonProperty(value="categoria")
    private String categoria;
    @JsonProperty(value="grupo")
    private Integer grupo;


    public ProductoIndividual(@JsonProperty(value="prodId")Long prodId,
                              @JsonProperty(value="categoria")String categoria,
                              @JsonProperty(value="grupo")Integer grupo) {
        this.prodId = prodId;
        this.categoria = categoria;
        this.grupo = grupo;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }
}
