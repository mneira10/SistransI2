package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoIndividual extends Producto {
    @JsonProperty(value="prodId")
    private Long prodId;
    @JsonProperty(value="categoria")
    private String categoria;
    @JsonProperty(value="grupo")
    private Integer grupo;


    public ProductoIndividual(	@JsonProperty(value="id")Long id, 
					    		@JsonProperty(value="nombre")String name,
					    		@JsonProperty(value="descrEsp")String descrEsp, 
					    		@JsonProperty(value="descrIng")String descrIng, 
					    		@JsonProperty(value="tPrep")Double tPrep, 
					    		@JsonProperty(value="costo")Double costo, 
					    		@JsonProperty(value="precio")Double precio, 
					    		@JsonProperty(value="restauranteId")Long restauranteId,
					    		@JsonProperty(value="prodId")Long prodId,
					    		@JsonProperty(value="categoria")String categoria,
					    		@JsonProperty(value="grupo")Integer grupo) {
    	super(id, name, descrEsp,descrIng,tPrep,costo,precio,restauranteId);
        this.prodId = prodId;
        this.categoria = categoria;
        this.grupo = grupo;
    }

    public ProductoIndividual(
    		@JsonProperty(value="prodId")Long prodId,
    		@JsonProperty(value="categoria")String categoria,
    		@JsonProperty(value="grupo")Integer grupo) {
	super();
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
