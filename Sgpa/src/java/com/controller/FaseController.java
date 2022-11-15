/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Fase;
import com.entity.ProgramaAcademico;
import com.services.FaseServices;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dimas
 */
@ManagedBean
@SessionScoped



public class FaseController {

   private List<Fase> fases = new LinkedList();
     
   FaseServices fas = new FaseServices(); 
   
    public void obtenerFases(ProgramaAcademico pa){
        System.out.println(""+pa.getId());
        setFases(fas.obtenerFases(pa));
    } 
   
  
    public FaseController() {
    }

    /**
     * @return the fases
     */
    public List<Fase> getFases() {
        return fases;
    }

    /**
     * @param fases the fases to set
     */
    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }
    
   
   
}
