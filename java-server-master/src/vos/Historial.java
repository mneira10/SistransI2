package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Historial {


	/**
	 * Id del usuario
	 */
	@JsonProperty(value="idProducto")
	private Long idProducto;
	

	/**
	 * Apellidos del usuario
	 */
	@JsonProperty(value="idUsuarioRegistrado")
	private Long idUsuarioRegistrado;
	
	
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdUsuarioRegistrado() {
		return idUsuarioRegistrado;
	}

	public void setIdUsuarioRegistrado(Long idUsuarioRegistrado) {
		this.idUsuarioRegistrado = idUsuarioRegistrado;
	}
	
	public Historial (@JsonProperty(value="idProducto")Long idProducto, @JsonProperty(value="idUsuarioRegistrado")Long idUsuarioRegistrado) {
		super();
		this.idUsuarioRegistrado = idUsuarioRegistrado;
		this.idProducto = idProducto;
	}
}
