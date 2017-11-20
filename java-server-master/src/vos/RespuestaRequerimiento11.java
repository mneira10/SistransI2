package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class RespuestaRequerimiento11 {
	
		@JsonProperty(value="idProducto")
		private Long idProducto;
	    @JsonProperty(value="cantidad")
	    private int cantidad;
	    @JsonProperty(value="diaSemana")
	    private int diaSemana;
	    
	    public RespuestaRequerimiento11 (@JsonProperty(value="idProducto")Long idProducto,
                @JsonProperty(value="cantidad") int cantidad,
                @JsonProperty(value="diaSemana") int diaSemana) {
	    	this.idProducto = idProducto;
	    	this.cantidad = cantidad;
	    	this.diaSemana = diaSemana;
	    }

		public Long getIdProducto() {
			return idProducto;
		}

		public void setIdProducto(Long idProducto) {
			this.idProducto = idProducto;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public int getDiaSemana() {
			return diaSemana;
		}

		public void setDiaSemana(int diaSemana) {
			this.diaSemana = diaSemana;
		}
	    	
}
