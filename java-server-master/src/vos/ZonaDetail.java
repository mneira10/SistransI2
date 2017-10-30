package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ZonaDetail extends Zona {
	
	  @JsonProperty(value="nombre")
	    private String nombre;

	    @JsonProperty(value="cerrado")
	    private Boolean cerrado;

	    @JsonProperty(value="tipo")
	    private String tipo;

	    @JsonProperty(value="aptoDescap")
	    private Boolean aptoDescap;

	    @JsonProperty(value="capacidad")
	    private Integer capacidad;
	    
	    @JsonProperty(value="restaurantes")
	    private List<RestauranteDetail> restaurantes;

	    public ZonaDetail (@JsonProperty(value="nombre")String nombre,
                @JsonProperty(value="cerrado")Boolean cerrado,
                @JsonProperty(value="tipo")String tipo,
                @JsonProperty(value="aptoDescap")Boolean aptoDescap,
                @JsonProperty(value="capacidad")Integer capacidad,
                @JsonProperty(value="restaurantes")List<RestauranteDetail> restaurantes) {
        super(nombre, cerrado, tipo, aptoDescap, capacidad);
        this.nombre=nombre;
        this.cerrado=cerrado;
        this.tipo=tipo;
        this.aptoDescap=aptoDescap;
        this.capacidad=capacidad;
        this.restaurantes=restaurantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<RestauranteDetail> getRestaurantes(){
    	return restaurantes; 
    }
    
    public void setRestaurantes(List<RestauranteDetail> lista){
    	this.restaurantes=lista; 
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAptoDescap() {
        return aptoDescap;
    }

    public void setAptoDescap(Boolean aptoDescap) {
        this.aptoDescap = aptoDescap;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

}
