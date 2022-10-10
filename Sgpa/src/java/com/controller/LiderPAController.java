/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.LiderPA;
import com.entity.Periodo;
import com.entity.Profesor;
import com.entity.ProgramaAcademico;
import com.entity.Semestre;
import com.services.LiderPAServices;
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
public class LiderPAController implements Serializable {

    private LiderPA liderPA = new LiderPA();
    private Periodo periodo = new Periodo();//para almacenar el el periodo actual
//    private Semestre semestre=new Semestre();

    LiderPAServices lidser = new LiderPAServices();

    private List<LiderPA> lideresPA = new LinkedList();
    private List<Profesor> profesores = new LinkedList();

    //controladores
    @ManagedProperty("#{periodoController}")
    private PeriodoController percont = new PeriodoController();
    @ManagedProperty("#{programaController}")
    private ProgramaController progcont = new ProgramaController();
    @ManagedProperty("#{profesorController}")
    private ProfesorController profcont = new ProfesorController();

    public void agregarProfesor(Profesor p) {
        getLiderPA().setProfesor(p);
    }

    public void agregarPrograma(ProgramaAcademico pa) {
        getLiderPA().setPrograma(pa);
    }

    public void agregarPeriodo(Periodo p) {
        getLiderPA().setPeriodo(p);
    }

    public void seleccionarSemestre(Semestre s) {
        liderPA.setSemestre(s);
    }

    public void registrarLiderPA() {
//        liderPA.setSemestre(getSemestre());
        try {
            if (liderPA.validarliderPA()) {
                lidser.crear(liderPA);
                liderPA = new LiderPA();
            }
        }catch(java.lang.NullPointerException npe){
            
        }
    }

    public LiderPAController() {
    }

    public LiderPA getLiderPA() {
        return liderPA;
    }

    public void setLiderPA(LiderPA liderPA) {
        this.liderPA = liderPA;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<LiderPA> getLideresPA() {
        return lideresPA;
    }

    public void setLideresPA(List<LiderPA> lideresPA) {
        this.lideresPA = lideresPA;
    }

    public PeriodoController getPercont() {
        return percont;
    }

    public void setPercont(PeriodoController percont) {
        this.percont = percont;
    }

    public ProgramaController getProgcont() {
        return progcont;
    }

    public void setProgcont(ProgramaController progcont) {
        this.progcont = progcont;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public ProfesorController getProfcont() {
        return profcont;
    }

    public void setProfcont(ProfesorController profcont) {
        this.profcont = profcont;
    }

//    /**
//     * @return the semestre
//     */
//    public Semestre getSemestre() {
//        return semestre;
//    }
//
//    /**
//     * @param semestre the semestre to set
//     */
//    public void setSemestre(Semestre semestre) {
//        this.semestre = semestre;
//    }
//
//    
}
