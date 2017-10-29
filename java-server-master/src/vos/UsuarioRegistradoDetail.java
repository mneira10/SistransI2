package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioRegistradoDetail extends UsuarioRegistrado {
	
	@JsonProperty(value="productosPreferidos")
	private List<Producto> productosPreferidos;
	@JsonProperty(value="zonasPreferidas")
	private List<Zona> zonasPreferidas;
	@JsonProperty(value="historial")
	private List<Producto> historial;
	@JsonProperty(value="usuario_id")
    private Long usuario_id;
    @JsonProperty(value="login")
    private String login;

	public UsuarioRegistradoDetail(@JsonProperty("")Long usuario_id, @JsonProperty("")String login, String password, String tipo, List<Producto> productosPreferidos, List<Zona> zonasPreferidas,@JsonProperty(value="historial")List<Producto> historial) {
		super(usuario_id, login, password, tipo);
		this.productosPreferidos=productosPreferidos;
		this.zonasPreferidas=zonasPreferidas;
		this.historial=historial;
	}
	
	public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Producto> getProductosPreferidos() {
        return productosPreferidos;
    }

    public void setProductosPreferidos(List<Producto> productosPreferidos) {
        this.productosPreferidos = productosPreferidos;
    }

	public List<Zona> getZonasPreferidas() {
		return zonasPreferidas;
	}

	public void setZonasPreferidas(List<Zona> zonasPreferidas) {
		this.zonasPreferidas = zonasPreferidas;
	}

	public List<Producto> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Producto> historial) {
		this.historial = historial;
	}
}
