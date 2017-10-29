package vos;

import java.util.Date;

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
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdUsuarioRegistrado() {
		return idUsuarioRegistrado;
	}

	public void setIdUsuarioRegistrado(Long idUsuarioRegistrado) {
		this.idUsuarioRegistrado = idUsuarioRegistrado;
	}
	
	public Historial (@JsonProperty(value="idProducto")Long idProducto, @JsonProperty(value="idUsuarioRegistrado")Long idUsuarioRegistrado, @JsonProperty(value="fecha") Date fecha) {
		super();
		this.idUsuarioRegistrado = idUsuarioRegistrado;
		this.idProducto = idProducto;
		this.fecha=fecha;
	}
}
