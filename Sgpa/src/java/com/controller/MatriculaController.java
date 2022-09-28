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
import com.services.EstudianteServices;
import com.services.MatriculaServices;
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
    //servicios
    MatriculaServices matser = new MatriculaServices();
    EstudianteServices estser = new EstudianteServices();

    private List<Matricula> matriculas=new LinkedList();
    
    /**
     * Creates a new instance of MatriculaController
     */
    public MatriculaController() {

    }

    public void consultarEstudiantesMatriculadosXPeriodo(Periodo p){
        matriculas=matser.obtenerMatriculasXperiodo(p);
    }
    
    public void consultarMatriculaXEstudianteEnPeriodo(Estudiante e, Periodo p){
        matricula=matser.obtenerMatriculaXPeriodo(p, e);
    }
    
    public void agregarEstudiante(Estudiante e) {
        getMatricula().setEstudiante(e);
    }

    public void agregarPrograma(ProgramaAcademico pa) {
        getMatricula().setPrograma(pa);
    }

    public void agregarPeriodo(Periodo p) {
        getMatricula().setPeriodo(p);
    }

    public void matricular() {
        matricula.setEstado("Activa");
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

}
