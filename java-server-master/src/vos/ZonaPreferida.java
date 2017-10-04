package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ZonaPreferida {

	@JsonProperty(value="zonaNombre")
    private String zonaNombre;
	
	@JsonProperty(value="idUsuarioRegistrado")
	private Long idUsuarioRegistrado;

	public String getZonaNombre() {
		return zonaNombre;
	}

	public void setZonaNombre(String zonaNombre) {
		this.zonaNombre = zonaNombre;
	}

	public Long getIdUsuarioRegistrado() {
		return idUsuarioRegistrado;
	}

	public void setIdUsuarioRegistrado(Long idUsuarioRegistrado) {
		this.idUsuarioRegistrado = idUsuarioRegistrado;
	}
	
	public ZonaPreferida (@JsonProperty(value="idUsuarioRegistrado")Long idUsuarioRegistrado, @JsonProperty(value="zonaNombre")String zonaNombre) {
		super();
		this.idUsuarioRegistrado = idUsuarioRegistrado;
		this.zonaNombre = zonaNombre;
	}
}
