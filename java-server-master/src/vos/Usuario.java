package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {
	
////Atributos

	/**
	 * Id del usuario
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * Nombres del usuario
	 */
	@JsonProperty(value="nombres")
	private String nombres;

	/**
	 * Apellidos del usuario
	 */
	@JsonProperty(value="apellidos")
	private String apellidos;
	
	/**
	 * tipo de identificacion del usuario
	 */
	@JsonProperty(value="tipoId")
	private String tipoId;
	
	@JsonProperty(value="email")
	private String email;
	
	
	/**
	 * Numero de identificacion del usuario
	 */
	@JsonProperty(value="numId")
	private Long numId;
	
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
	
	
	/**
	 * Metodo getter del atributo duration
	 * @return duracion del video en minutos
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * Metodo setter del atributo duration <b>post: </b> La duracion del video
	 * ha sido cambiado con el valor que entra como parametro
	 * @param duration - Duracion en minutos del video.
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Metodo getter del atributo duration
	 * @return duracion del video en minutos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Metodo setter del atributo duration <b>post: </b> La duracion del video
	 * ha sido cambiado con el valor que entra como parametro
	 * @param duration - Duracion en minutos del video.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Metodo getter del atributo duration
	 * @return duracion del video en minutos
	 */
	public String getTipoId() {
		return tipoId;
	}
	
	
	/**
	 * Metodo setter del atributo duration <b>post: </b> La duracion del video
	 * ha sido cambiado con el valor que entra como parametro
	 * @param duration - Duracion en minutos del video.
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Metodo getter del atributo duration
	 * @return duracion del video en minutos
	 */
	public Long getNumId() {
		return numId;
	}
	
	
	/**
	 * Metodo setter del atributo duration <b>post: </b> La duracion del video
	 * ha sido cambiado con el valor que entra como parametro
	 * @param duration - Duracion en minutos del video.
	 */
	public void setNumId(Long numId) {
		this.numId = numId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
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
	public Usuario (@JsonProperty(value="id")Long id, @JsonProperty(value="nombres")String name,@JsonProperty(value="apellidos")String apellidos, @JsonProperty(value="tipoId")String tipoId, @JsonProperty(value="numId")Long numId,@JsonProperty(value="email")String email) {
		super();
		this.id = id;
		this.nombres = name;
		this.apellidos = apellidos;
		this.tipoId= tipoId;
		this.numId= numId;
		this.email= email;
	}
	
}
