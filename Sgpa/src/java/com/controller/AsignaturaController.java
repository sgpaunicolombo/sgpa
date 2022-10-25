/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Area;
import com.entity.Asignatura;
import com.entity.Semestre;
import com.services.AsignaturaServices;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sgpaAdmin2
 */
@ManagedBean
@SessionScoped
public class AsignaturaController implements Serializable{
    
    private List<Asignatura> asignaturas=new LinkedList();
    private Asignatura asignatura=new Asignatura();
    AsignaturaServices asigser=new AsignaturaServices();
    
    @ManagedProperty("#{semestreController}")
    private SemestreController semcon=new SemestreController();
    
    public AsignaturaController() {
    }
    /*
    public void agregarSemestre(Semestre s) {
        getAsignatura().setSemestre(s);
    }
**/
    public void agregarArea(Area a) {
        getAsignatura().setArea(a);
    }
    

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public SemestreController getSemcon() {
        return semcon;
    }

    public void setSemcon(SemestreController semcon) {
        this.semcon = semcon;
    }
    
}
