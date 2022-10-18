/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Periodo;
import com.entity.ProgramaAcademico;
import com.entity.Seccion;
import com.entity.Semestre;
import com.services.SeccionServices;
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
public class SeccionController implements Serializable{

    private Seccion seccion=new Seccion();
    private Periodo periodo=new Periodo();
//    ProgramaAcademico programa=new ProgramaAcademico();
    
    private List<Seccion> secciones=new LinkedList();
    
    SeccionServices secser=new SeccionServices();
    
    private boolean mostPCsecc=false;
    
    /**
     * Creates a new instance of SeccionController
     */
    public SeccionController() {
    }

    public void obtenerseccionesPeriodo(ProgramaAcademico pa){
        mostPCsecc=true;
        secciones=secser.obtenerSeccionesXPeriodo_Programa(pa, periodo);
        seccion.setPrograma(pa);
    }
    
   
    
    public void volverprogramas(){
        mostPCsecc=false;
    }
    
    public void seleccionarSemestre(Semestre sem){
        seccion.setSemestre(sem);
    }
    
    public void registrar(){
        seccion.setPeriodo(periodo);
        secser.modificar(seccion);
        obtenerseccionesPeriodo(seccion.getPrograma());
        seccion=new Seccion();
    }
    
    /**
     * @return the seccion
     */
    public Seccion getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    /**
     * @return the secciones
     */
    public List<Seccion> getSecciones() {
        return secciones;
    }

    /**
     * @param secciones the secciones to set
     */
    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the mostPCsecc
     */
    public boolean isMostPCsecc() {
        return mostPCsecc;
    }

    /**
     * @param mostPCsecc the mostPCsecc to set
     */
    public void setMostPCsecc(boolean mostPCsecc) {
        this.mostPCsecc = mostPCsecc;
    }
    
}
