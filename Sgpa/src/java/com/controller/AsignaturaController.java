/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Area;
import com.entity.Asignatura;
import com.entity.Periodo;
import com.entity.ProgramaAcademico;
import com.entity.Seccion;
import com.entity.Semestre;
import com.services.AsignaturaServices;
import java.io.Serializable;
import com.services.SeccionServices;
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
    
    private List<Semestre> semestres = new LinkedList();
    private List<Asignatura> asignaturas=new LinkedList();
    private List<Area> areas = new LinkedList();
    private Asignatura asignatura=new Asignatura();
    private AsignaturaServices asigser=new AsignaturaServices();
    
    private List<Seccion> secciones = new LinkedList();
    private SeccionServices secser=new SeccionServices();
    private Seccion seccion = new Seccion();
    private ProgramaAcademico programa = new ProgramaAcademico();
    private Periodo periodo=new Periodo();
    
    @ManagedProperty("#{semestreController}")
    private SemestreController semcon=new SemestreController();
    @ManagedProperty("#{seccionController}")
    private SeccionController secccon=new SeccionController();
    
    private boolean mostPCsecc1=false;
    
    public AsignaturaController() {
    }
    /*
    public void agregarSemestre(Semestre s) {
        getAsignatura().setSemestre(s);
    }
**/
    
    public void agregarSeccion(Seccion s){
        getAsignatura().setSeccion(s);
    }
    
    public void obtenerseccionesPeriodo(ProgramaAcademico pa){
        mostPCsecc1=true;
        secciones=secser.obtenerSeccionesXPeriodo_Programa(pa, periodo);
        seccion.setPrograma(pa);
    }
    
    public void volverprogramas(){
        mostPCsecc1=false;
    }
    
    public void agregarArea(Area a) {
        getAsignatura().setArea(a);
    }
    public void agregarAsignatura(Asignatura a){
        asigser.crear(a);
    
    }
    

    public List<Semestre> getSemestres(){
        return semestres;
    }
            
    public List<Area> getAreas(){
        return areas;
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

    public boolean isMostPCsecc() {
        return mostPCsecc1;
    }

    public void setMostPCsecc(boolean mostPCsecc) {
        this.mostPCsecc1 = mostPCsecc;
    }

    public SeccionController getSecccon() {
        return secccon;
    }

    public void setSecccon(SeccionController secccon) {
        this.secccon = secccon;
    }

    public AsignaturaServices getAsigser() {
        return asigser;
    }

    public void setAsigser(AsignaturaServices asigser) {
        this.asigser = asigser;
    }

    public SeccionServices getSecser() {
        return secser;
    }

    public void setSecser(SeccionServices secser) {
        this.secser = secser;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public ProgramaAcademico getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
    
}
