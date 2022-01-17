package modelo;

import java.io.Serializable;

public class Modelo implements Serializable {

    private String usuario;
    private String contrasenia;
    private String tipo_usuario;

    public Modelo() {

    }

    public Modelo(String usuario, String contrasenia, String tipo_usuario) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipo_usuario = tipo_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
