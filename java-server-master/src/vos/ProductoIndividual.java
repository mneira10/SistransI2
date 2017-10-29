package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoIndividual extends Producto {
    @JsonProperty(value="prodId")
    private Long prodId;
    @JsonProperty(value="categoria")
    private String categoria;
    @JsonProperty(value="grupo")
    private Integer grupo;
    @JsonProperty(value="cantidadDisponible")
    private Integer cantidadDisponible;
    @JsonProperty(value="maximo")
    private Integer maximo;


    public ProductoIndividual(	@JsonProperty(value="id")Long id, 
					    		@JsonProperty(value="nombre")String name,
					    		@JsonProperty(value="descrEsp")String descrEsp, 
					    		@JsonProperty(value="descrIng")String descrIng, 
					    		@JsonProperty(value="tPrep")Double tPrep, 
					    		@JsonProperty(value="costo")Double costo, 
					    		@JsonProperty(value="precio")Double precio, 
					    		@JsonProperty(value="restauranteId")Long restauranteId,
					    		@JsonProperty(value="categoria")String categoria,
					    		@JsonProperty(value="grupo")Integer grupo,
					    		@JsonProperty(value="cantidadDisponible") Integer cantidadDisponible,
					    		@JsonProperty(value="maximo") Integer maximo) {
    	super(id, name, descrEsp,descrIng,tPrep,costo,precio,restauranteId);
        this.prodId = id;
        this.categoria = categoria;
        this.grupo = grupo;
        this.cantidadDisponible=cantidadDisponible;
        this.maximo=maximo;
    }

    public ProductoIndividual(
    		@JsonProperty(value="prodId")Long prodId,
    		@JsonProperty(value="categoria")String categoria,
    		@JsonProperty(value="grupo")Integer grupo,
    		@JsonProperty(value="cantidadDisponible") Integer cantidadDisponible,
    		@JsonProperty(value="maximo") Integer maximo) {
	super();
	this.prodId = prodId;
	this.categoria = categoria;
	this.grupo = grupo;
	this.cantidadDisponible=cantidadDisponible;
	this.maximo=maximo;
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

    public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public Integer getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }
}
