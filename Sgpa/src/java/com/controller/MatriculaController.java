/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Estudiante;
import com.entity.Matricula;
import com.entity.Periodo;
import com.entity.ProgramaAcademico;
import com.entity.Seccion;
import com.entity.Semestre;
import com.services.EstudianteServices;
import com.services.MatriculaServices;
import com.services.SeccionServices;
import java.io.Serializable;
import java.util.Date;
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
public class MatriculaController implements Serializable {

    //objetos de negocio
    private Matricula matricula = new Matricula();
    private Semestre semestre = new Semestre();
    private Periodo periodo = new Periodo();
    private ProgramaAcademico programa=new ProgramaAcademico();

    //servicios
    MatriculaServices matser = new MatriculaServices();
    EstudianteServices estser = new EstudianteServices();
    SeccionServices secser = new SeccionServices();

    private List<Matricula> matriculas = new LinkedList();
    private List<Seccion> secciones = new LinkedList();

    /**
     * Creates a new instance of MatriculaController
     */
    public MatriculaController() {

    }

    public void consultarEstudiantesMatriculadosXPeriodo(Periodo p) {
        matriculas = matser.obtenerMatriculasXperiodo(p);
    }

    public void consultarMatriculaXEstudianteEnPeriodo(Estudiante e, Periodo p) {
        matricula = matser.obtenerMatriculaXPeriodo(p, e);
    }

    public void agregarEstudiante(Estudiante e) {
        getMatricula().setEstudiante(e);
    }

    public void agregarPrograma(ProgramaAcademico pa) {
        setPrograma(pa);
    }

    public void agregarPeriodo(Periodo p) {
        setPeriodo(p);        
    }
    public void seleccionarSemestre(Semestre s) {
        setSemestre(s);
        secciones=secser.obtenerSeccionesXSemestre_Periodo_Programa(getSemestre(), getPrograma(), getPeriodo());
    }

    public void seleccionarSeccion(Seccion s){
        matricula.setSeccion(s);
    }
    
    public void matricular() {
        matricula.setEstado("Academica");
        matricula.setEstadoPA("Libre");
        matricula.setFecha(new Date());
        if (matricula.validarMatricula()) {
            matser.crear(matricula);
            matricula.getEstudiante().generarCodigo(matricula);
            matricula.setEstudiante(estser.modificar(matricula.getEstudiante()));
            matricula = new Matricula();
        }
    }

    
    /**
     * @return the matricula
     */
    public Matricula getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the matriculas
     */
    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * @param matriculas the matriculas to set
     */
    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
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
     * @return the semestre
     */
    public Semestre getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
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
     * @return the programa
     */
    public ProgramaAcademico getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
    }

}
