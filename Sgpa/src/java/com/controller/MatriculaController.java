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
import com.services.MatriculaServices;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@SessionScoped
public class MatriculaController implements Serializable{

    private Matricula matricula = new Matricula();
    
    
    // servicios
    MatriculaServices matser = new MatriculaServices();
    
    
    public MatriculaController() {
    }
    
    public void agregarEstudianteMtricula(Estudiante estudiante){
        getMatricula().setEstudiante(estudiante);
    }
    
    public void agregarPrograma(ProgramaAcademico pa){
        getMatricula().setPrograma(pa);
    }
    public void agregarPeriodo(Periodo p){
        getMatricula().setPeriodo(p);
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
}
