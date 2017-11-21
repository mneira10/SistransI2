package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RespuestaRequerimiento11A {
	
		@JsonProperty(value="idRestaurante")
		private Long idRestaurante;
	    @JsonProperty(value="frecuencia")
	    private int frecuencia;
	    @JsonProperty(value="diaSemana")
	    private int diaSemana;
	    
	    public RespuestaRequerimiento11A (@JsonProperty(value="idProducto")Long idRestaurante,
                @JsonProperty(value="frecuencia") int frecuencia,
                @JsonProperty(value="diaSemana") int diaSemana) {
	    	this.idRestaurante = idRestaurante;
	    	this.frecuencia = frecuencia;
	    	this.diaSemana = diaSemana;
	    }

		public Long getIdRestaurante() {
			return idRestaurante;
		}

		public void setIdRestaurante(Long idRestaurante) {
			this.idRestaurante = idRestaurante;
		}

		public int getFrecuencia() {
			return frecuencia;
		}

		public void setFrecuencia(int frecuencia) {
			this.frecuencia = frecuencia;
		}

		public int getDiaSemana() {
			return diaSemana;
		}

		public void setDiaSemana(int diaSemana) {
			this.diaSemana = diaSemana;
		}
	    	
}
