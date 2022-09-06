/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Coordinador;
import com.entity.ProgramaAcademico;
import com.services.ProgramaAcademicoServices;
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
public class ProgramaController implements Serializable{

    private ProgramaAcademico programa = new ProgramaAcademico();

    private List<ProgramaAcademico> programas=new LinkedList();
    
    ProgramaAcademicoServices paserv=new ProgramaAcademicoServices();
    
    /**
     * Creates a new instance of ProgramaController
     */
    public ProgramaController() {
    }

    public void consultarprograma(ProgramaAcademico p){
        programa=p;
    }
    
    public void consultarProgramas(){
        programas=paserv.consultarTodo(ProgramaAcademico.class);
    }
    
    public void agregarCoordinador(Coordinador c){
        programa.setCoordinador(c);
    }
    
    public void registrar(){
        if(programa.validar()){
            programa.setEstado("Activo");
            programa=paserv.modificar(programa);
            programa = new ProgramaAcademico();
            consultarProgramas();
        }
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
     * @return the programas
     */
    public List<ProgramaAcademico> getProgramas() {
        return programas;
    }

    /**
     * @param programas the programas to set
     */
    public void setProgramas(List<ProgramaAcademico> programas) {
        this.programas = programas;
    }

}
