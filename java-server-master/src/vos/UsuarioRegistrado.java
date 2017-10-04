package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioRegistrado {
    @JsonProperty(value="usuario_id")
    private Long usuario_id;
    @JsonProperty(value="login")
    private String login;
    @JsonProperty(value="password")
    private String password;

    public UsuarioRegistrado(@JsonProperty(value="usuario_id")Long usuario_id,
                             @JsonProperty(value="login")String login,
                             @JsonProperty(value="password")String password) {
        this.usuario_id = usuario_id;
        this.login = login;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
