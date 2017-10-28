package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto {
	
////Atributos

	/**
	 * Id del usuario
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * Nombres del usuario
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Apellidos del usuario
	 */
	@JsonProperty(value="descrEsp")
	private String descrEsp;
	
	/**
	 * Apellidos del usuario
	 */
	@JsonProperty(value="descrIng")
	private String descrIng;
	
	/**
	 * tipo de identificacion del usuario
	 */
	@JsonProperty(value="tPrep")
	private Double tPrep;
	
	/**
	 * tipo de identificacion del usuario
	 */
	@JsonProperty(value="costo")
	private Double costo;
	
	/**
	 * tipo de identificacion del usuario
	 */
	@JsonProperty(value="precio")
	private Double precio;
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescrEsp() {
		return descrEsp;
	}

	public void setDescrEsp(String descrEsp) {
		this.descrEsp = descrEsp;
	}

	public String getDescrIng() {
		return descrIng;
	}

	public void setDescrIng(String descrIng) {
		this.descrIng = descrIng;
	}

	public Double gettPrep() {
		return tPrep;
	}

	public void settPrep(Double tPrep) {
		this.tPrep = tPrep;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getRestauranteId() {
		return restauranteId;
	}

	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}


	/**
	 * tipo de identificacion del usuario
	 */
	@JsonProperty(value="restauranteId")
	private Long restauranteId;

	/**
	 * Metodo getter del atributo duration
	 * @return duracion del video en minutos
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo setter del atributo duration <b>post: </b> La duracion del video
	 * ha sido cambiado con el valor que entra como parametro
	 * @param duration - Duracion en minutos del video.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
////Constructor

	/**
	 * Metodo constructor de la clase usuario
	 * <b>post: </b> Crea el usuario con los valores que entran como parametro
	 * @param id - Id del usuario.
	 * @param nombres - Nombres del usuario. name != null
	 * @param apellidos - Apellidos del usuario. apellido != null
	 * @param tipoId - Tipo de identificacion del usuario. tipoId != null
	 * @param numId - Numero de identificacion del usuario. numId != null
	 * 
	 */
	public Producto (@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String name,@JsonProperty(value="descrEsp")String descrEsp, @JsonProperty(value="descrIng")String descrIng, @JsonProperty(value="tPrep")Double tPrep, @JsonProperty(value="costo")Double costo, @JsonProperty(value="precio")Double precio, @JsonProperty(value="restauranteId")Long restauranteId) {
		super();
		this.id = id;
		this.nombre = name;
		this.descrEsp = descrEsp;
		this.descrIng = descrIng;
		this.tPrep= tPrep;
		this.costo= costo;
		this.precio=precio;
		this.restauranteId=restauranteId;
	}
	public Producto() {
		
	}
}
