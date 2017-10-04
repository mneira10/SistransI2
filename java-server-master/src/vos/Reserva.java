package vos;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Reserva {
    @JsonProperty(value="id")
    private Long id;
    @JsonProperty(value="fechaInicio")
    private Date fechaInicio;
    @JsonProperty(value="fechaFin")
    private Date fechaFin;
    @JsonProperty(value="numComensales")
    private Integer numComensales;
    @JsonProperty(value="usrReg")
    private  Long usrReg;
    @JsonProperty(value="zona")
    private String zona;
    @JsonProperty(value="menu")
    private Long menu;


    public Reserva(@JsonProperty(value="id")Long id,
                   @JsonProperty(value="fechaInicio")Date fechaInicio,
                   @JsonProperty(value="fechaFin")Date fechaFin,
                   @JsonProperty(value="numComensales")Integer numComensales,
                   @JsonProperty(value="usrReg")Long usrReg,
                   @JsonProperty(value="zona")String zona,
                   @JsonProperty(value="menu")Long menu) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numComensales = numComensales;
        this.usrReg = usrReg;
        this.zona = zona;
        this.menu = menu;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(Integer numComensales) {
        this.numComensales = numComensales;
    }

    public Long getUsrReg() {
        return usrReg;
    }

    public void setUsrReg(Long usrReg) {
        this.usrReg = usrReg;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Long getMenu() {
        return menu;
    }

    public void setMenu(Long menu) {
        this.menu = menu;
    }
}
