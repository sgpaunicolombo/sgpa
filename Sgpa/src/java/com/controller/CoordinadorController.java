/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Coordinador;
import com.services.CoordinadorServices;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jcmm
 */
@ManagedBean
@SessionScoped
public class CoordinadorController implements Serializable{

    
    private List<Coordinador> coordinadores=new LinkedList();
    private Coordinador coordinador=new Coordinador();

   //servicios
    CoordinadorServices coordser=new CoordinadorServices();
    
   //variables de control
    private String paginaActualC = "";
    
    /**
     * Creates a new instance of CoordinadorController
     */
    public CoordinadorController() {
    }
    
      public void gprofesores(){
        paginaActualC="/Profesor/GestorProfesores.xhtml";
    }
      public void gmatriculas(){
        paginaActualC="/Coordinador/GestorMatricula.xhtml";
    }
    
    
    public void consultarCoordinadores(){
        setCoordinadores(coordser.consultarTodo(Coordinador.class));
    }

     public void registrar() {
        if (coordinador.validarUsuario()) {
            coordinador.setEstado("Activo");
            coordinador.setTipo("Coordinador");
            coordser.crear(coordinador);
            coordinador = new Coordinador();
            consultarCoordinadores();
        }
    }
    
    public void obtenerCoordinador(Long id){
        coordinador=coordser.consultar(Coordinador.class, id);
    }
     
     
    /**
     * @return the coordinadores
     */
    public List<Coordinador> getCoordinadores() {
        return coordinadores;
    }

    /**
     * @param coordinadores the coordinadores to set
     */
    public void setCoordinadores(List<Coordinador> coordinadores) {
        this.coordinadores = coordinadores;
    }

    /**
     * @return the coordinador
     */
    public Coordinador getCoordinador() {
        return coordinador;
    }

    /**
     * @param coordinador the coordinador to set
     */
    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    /**
     * @return the paginaActualC
     */
    public String getPaginaActualC() {
        return paginaActualC;
    }

    /**
     * @param paginaActualC the paginaActualC to set
     */
    public void setPaginaActualC(String paginaActualC) {
        this.paginaActualC = paginaActualC;
    }
    
}
