package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Menu extends Producto{

	@JsonProperty(value="id_producto")
	private Long id_producto;

	public Menu(@JsonProperty(value="id")Long id, 
			@JsonProperty(value="nombre")String name,
			@JsonProperty(value="descrEsp")String descrEsp, 
			@JsonProperty(value="descrIng")String descrIng, 
			@JsonProperty(value="tPrep")Double tPrep, 
			@JsonProperty(value="costo")Double costo, 
			@JsonProperty(value="precio")Double precio, 
			@JsonProperty(value="restauranteId")Long restauranteId,
			@JsonProperty(value="id_producto") Long id_producto) {
		super(id, name, descrEsp,descrIng,tPrep,costo,precio,restauranteId);
		this.id_producto = id_producto;
	}

	public Menu(@JsonProperty(value="id_producto") Long id_producto) {
		super();
		this.id_producto = id_producto;
	}
	
	public Long getId_producto() {
		return id_producto;
	}

	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	}
}
