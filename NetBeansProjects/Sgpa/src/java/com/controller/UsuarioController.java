/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Usuario;
import com.services.UsuarioServices;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jcmm
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

    //Objetos de Negocio
    private Usuario usuario = new Usuario();
    

    //Colecciones
    private List<Usuario> usuarios = new LinkedList();

    //Servicios
    UsuarioServices ususer = new UsuarioServices();

    //Variables de control
    private boolean mpanelLogin = true;
    private String paginaActual = "";
    private String paginaSU="";

    //controladores asociados
    @ManagedProperty("#{coordinadorController}")
    private CoordinadorController coorcon=new CoordinadorController();
    @ManagedProperty("#{programaController}")
    private ProgramaController procon=new ProgramaController();
    
    
    
    
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

            }
            if (getUsuario().getTipo().equals("Super")) {
                coorcon.consultarCoordinadores();
                procon.consultarProgramas();
                paginaActual = "/GUISuperUsuario.xhtml";

            }
            mpanelLogin = false;

        }
    }

    public void gusuarios(){
        paginaSU="/General/GestorUsuarios.xhtml";
    }
      public void gprogramas(){
        paginaSU="/General/GestorProgramas.xhtml";
    }
       public void gperiodos(){
        paginaSU="/General/GestorPeriodos.xhtml";
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

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the paginaSU
     */
    public String getPaginaSU() {
        return paginaSU;
    }

    /**
     * @param paginaSU the paginaSU to set
     */
    public void setPaginaSU(String paginaSU) {
        this.paginaSU = paginaSU;
    }

    /**
     * @return the coorcon
     */
    public CoordinadorController getCoorcon() {
        return coorcon;
    }

    /**
     * @param coorcon the coorcon to set
     */
    public void setCoorcon(CoordinadorController coorcon) {
        this.coorcon = coorcon;
    }

    /**
     * @return the procon
     */
    public ProgramaController getProcon() {
        return procon;
    }

    /**
     * @param procon the procon to set
     */
    public void setProcon(ProgramaController procon) {
        this.procon = procon;
    }

}
