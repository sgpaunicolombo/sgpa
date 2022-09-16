/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Tipo_Entregable;
import com.services.Tipo_EntregableServices;
import java.io.Serializable;
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
public class Tipo_EntregableController implements Serializable{

    private Tipo_Entregable tipo_entregable = new Tipo_Entregable();

    private Tipo_EntregableServices tipenser = new Tipo_EntregableServices();

    private List<Tipo_Entregable> tipo_Entregables = new LinkedList();

    /**
     * Creates a new instance of PeriodoController
     */
    public Tipo_EntregableController() {
    }
   

    public void registrarTipo_Entregable() {
        if(tipo_entregable.validar()){
         tipo_entregable=tipenser.modificar(tipo_entregable);
            tipo_entregable = new Tipo_Entregable();
            consultarTipo_Entregable();
    
        }
    }
    public void eliminartipo_Entregable(Tipo_Entregable te){
        tipenser.eliminar(te);
          consultarTipo_Entregable();   
        
    }
        
       public void consultarTipo_Entregable(){
           setTipo_Entregables(tipenser.consultarTodo(Tipo_Entregable.class));
    }
       
      
        
        
           

    public Tipo_Entregable getTipo_entregable() {
        return tipo_entregable;
    }

    public void setTipo_entregable(Tipo_Entregable tipo_entregable) {
        this.tipo_entregable = tipo_entregable;
    }

    /**
     * @return the tipo_Entregables
     */
    public List<Tipo_Entregable> getTipo_Entregables() {
        return tipo_Entregables;
    }

    /**
     * @param tipo_Entregables the tipo_Entregables to set
     */
    public void setTipo_Entregables(List<Tipo_Entregable> tipo_Entregables) {
        this.tipo_Entregables = tipo_Entregables;
    }

 
        
    }

