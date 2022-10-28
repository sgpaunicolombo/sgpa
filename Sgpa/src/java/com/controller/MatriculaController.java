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
    private ProgramaAcademico programa = new ProgramaAcademico();

    //servicios
    MatriculaServices matser = new MatriculaServices();
    EstudianteServices estser = new EstudianteServices();
    SeccionServices secser = new SeccionServices();

    //colecciones
    private List<Matricula> matriculas = new LinkedList();
    private List<Seccion> secciones = new LinkedList();
    private List<Matricula> matriculasXSeccion = new LinkedList();

    //variables de control
    private int activeIndex = 0;
    
    /**
     * Creates a new instance of MatriculaController
     */
    public MatriculaController() {

    }

    public void obtenerMatriculasXSeccion(Seccion s) {
        matriculasXSeccion = new LinkedList();
        for (Matricula m : matriculas) {
            if (m.getSeccion().getId().equals(s.getId())) {
                matriculasXSeccion.add(m);
            }
        }
    }

    public void consultarEstudiantesMatriculadosXPeriodo(Periodo p) {
        matriculas = matser.obtenerMatriculasXperiodo(p);
    }

    public void consultarMatriculaXEstudianteEnPeriodo(Estudiante e, Periodo p) {
        matricula = matser.obtenerMatriculaXPeriodo(p, e);
    }

    public void agregarEstudiante(Estudiante e) {
        getMatricula().setEstudiante(e);
        activeIndex = 1;
    }

    public void agregarPrograma(ProgramaAcademico pa) {
        setPrograma(pa);
        activeIndex = 2;
    }

    public void agregarPeriodo(Periodo p) {
        setPeriodo(p);
        activeIndex = 3;
    }

    public void seleccionarSemestre(Semestre s) {
        setSemestre(s);
        secciones = secser.obtenerSeccionesXSemestre_Periodo_Programa(getSemestre(), getPrograma(), getPeriodo());
    }

    public void seleccionarSeccion(Seccion s) {
        matricula.setSeccion(s);
    }

    public void matricular() {        
        Matricula mat=matser.obtenerMatriculaXPeriodo(periodo, matricula.getEstudiante());
        matricula.setEstado("Academica");
        matricula.setEstadoPA("Libre");
        matricula.setFecha(new Date());
//        System.out.println(""+matricula.getId()+" "+matricula.getEstado());
        try {
//            System.out.println(matricula.getSeccion().getPrograma().getNombre()+" "+matricula.getId()+" "+matricula.getEstado()+" "+matricula.getEstudiante().toString());
            if (mat.getId() > 0) {
                FacesUtil.addErrorMessage("El estudiante ya esta matriculado en la seccion: " + matricula.getSeccion().getDenominacion());
            } else {
//                System.out.println(""+matricula.getId()+" "+matricula.getEstado()+" "+matricula.getEstudiante().toString());
                if (matricula.validarMatricula()) {                    
//                    matricula.setId(null);
                    matser.modificar(matricula);
                    matricula.getEstudiante().generarCodigo(matricula);
                    matricula.setEstudiante(estser.modificar(matricula.getEstudiante()));
                    matricula = new Matricula();
                }
            }
            activeIndex = 0;
        } catch (java.lang.NullPointerException npe) {

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

    /**
     * @return the matriculasXSeccion
     */
    public List<Matricula> getMatriculasXSeccion() {
        return matriculasXSeccion;
    }

    /**
     * @param matriculasXSeccion the matriculasXSeccion to set
     */
    public void setMatriculasXSeccion(List<Matricula> matriculasXSeccion) {
        this.matriculasXSeccion = matriculasXSeccion;
    }

    /**
     * @return the activeIndex
     */
    public int getActiveIndex() {
        return activeIndex;
    }

    /**
     * @param activeIndex the activeIndex to set
     */
    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

}
