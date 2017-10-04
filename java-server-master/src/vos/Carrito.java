package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Carrito {
	
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
	@JsonProperty(value="usuarioId")
	private Long usuarioId;
	
	/**
	 * tipo de identificacion del usuario
	 */
	@JsonProperty(value="costo")
	private Double costo;
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setPrecio(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

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
	public Carrito (@JsonProperty(value="id")Long id, @JsonProperty(value="nombre")String name, @JsonProperty(value="costo")Double costo, @JsonProperty(value="usuarioId")Long usuarioId) {
		super();
		this.id = id;
		this.nombre = name;
		this.costo= costo;
		this.usuarioId=usuarioId;
	}
	
}
