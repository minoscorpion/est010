/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author rafae
 */
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer usuarioID;
    
    private String login;
    
    private String senha;
    
     public Usuarios() {
    }

    public Usuarios(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Usuarios(Integer usuarioID, String login, String senha) {
        this.usuarioID = usuarioID;
        this.login = login;
        this.senha = senha;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioID != null ? usuarioID.hashCode() : 0);
        return hash;
    }
}
