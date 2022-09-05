/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Usuario;
import com.services.UsuarioServices;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jcmm
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario usuario = new Usuario();

    UsuarioServices ususer = new UsuarioServices();

    private boolean mpanelLogin = true;
    private String paginaActual = "";

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    public void iniciar() {
        setUsuario(ususer.ingresar(getUsuario().getLogin(), getUsuario().getPassword()));
        if (!getUsuario().getIdentificacion().equals("")) {
            if (getUsuario().getTipo().equals("Coordinador")) {
                paginaActual = "/Coordinador/GUICoordinador.xhtml";
               
            }
             if (getUsuario().getTipo().equals("Estudiante")) {
                paginaActual = "/Estudiante/GUIEstudiante.xhtml";
                
            }
              if (getUsuario().getTipo().equals("Profesor")) {
                paginaActual = "/Profesor/GUIProfesor.xhtml";
               
            } mpanelLogin = false;

        }
    }

    public void salir() {
        paginaActual = "";
        mpanelLogin = true;
        usuario = new Usuario();
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the mpanelLogin
     */
    public boolean isMpanelLogin() {
        return mpanelLogin;
    }

    /**
     * @param mpanelLogin the mpanelLogin to set
     */
    public void setMpanelLogin(boolean mpanelLogin) {
        this.mpanelLogin = mpanelLogin;
    }

    /**
     * @return the paginaActual
     */
    public String getPaginaActual() {
        return paginaActual;
    }

    /**
     * @param paginaActual the paginaActual to set
     */
    public void setPaginaActual(String paginaActual) {
        this.paginaActual = paginaActual;
    }

}
