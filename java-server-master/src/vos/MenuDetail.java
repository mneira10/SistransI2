package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class MenuDetail extends Menu {
	
	@JsonProperty(value="id_producto")
	private Long id_producto;
	
	@JsonProperty(value="productos")
	private List<Producto> productos;
	
	public MenuDetail(@JsonProperty(value="nombre")String name,
			@JsonProperty(value="descrEsp")String descrEsp, 
			@JsonProperty(value="descrIng")String descrIng, 
			@JsonProperty(value="tPrep")Double tPrep, 
			@JsonProperty(value="costo")Double costo, 
			@JsonProperty(value="precio")Double precio, 
			@JsonProperty(value="restauranteId")Long restauranteId,
			@JsonProperty(value="id_producto") Long id_producto,
			@JsonProperty(value="productos") List<Producto> productos) {
		super(name, descrEsp,descrIng,tPrep,costo,precio,restauranteId, id_producto);
		this.productos=productos;
	}

	public MenuDetail(@JsonProperty(value="id_producto") Long id_producto,
			@JsonProperty(value="productos") List<Producto> productos) {
		super(id_producto);
		this.productos=productos;
	}
	
	public Long getId_producto() {
		return id_producto;
	}

	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
