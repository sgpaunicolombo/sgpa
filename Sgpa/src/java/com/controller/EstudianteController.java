/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Estudiante;
import com.services.EstudianteServices;
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
public class EstudianteController implements Serializable{

    private Estudiante estudiante = new Estudiante();

    EstudianteServices estser = new EstudianteServices();
    private List<Estudiante> estudiantes = new LinkedList();

    //variable de control
    private boolean mpanelInscripcion = true;
    private String paginaActualE = "";
    /**
     * Creates a new instance of EstudianteController
     */
    public EstudianteController() {
    }
       
    public void inscribirEstudiante() {
        estudiante.setTipo("Estudiante");
        estudiante.setEstado("Pre-Matricula");
        if (estudiante.validarUsuario()) {
            estser.modificar(estudiante);
            estudiante = new Estudiante();
            mpanelInscripcion = false;
        }
    }
    
    public void obtenerEstudiantes(){
        estudiantes=estser.consultarTodo(Estudiante.class);
    }

    public void obtenerEstudiante(Long id){
        estudiante=estser.consultar(Estudiante.class, id);
    }
    
    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @return the mpanelInscripcion
     */
    public boolean isMpanelInscripcion() {
        return mpanelInscripcion;
    }

    /**
     * @param mpanelInscripcion the mpanelInscripcion to set
     */
    public void setMpanelInscripcion(boolean mpanelInscripcion) {
        this.mpanelInscripcion = mpanelInscripcion;
    }

    /**
     * @return the paginaActualE
     */
    public String getPaginaActualE() {
        return paginaActualE;
    }

    /**
     * @param paginaActualE the paginaActualE to set
     */
    public void setPaginaActualE(String paginaActualE) {
        this.paginaActualE = paginaActualE;
    }

    /**
     * @return the estudiantes
     */
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

}
