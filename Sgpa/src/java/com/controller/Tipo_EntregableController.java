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
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Dimas
 */
@ManagedBean
@SessionScoped
public class Tipo_EntregableController implements Serializable{

    private Tipo_Entregable tipo_entregable = new Tipo_Entregable();

    private Tipo_EntregableServices tipenser = new Tipo_EntregableServices();

    private List<Tipo_Entregable> tipos_Entregable = new LinkedList();

    /**
     * Creates a new instance of PeriodoController
     */
    public Tipo_EntregableController() {
    }
   

    public void registrarTipo_Entregable() {
        if(tipo_entregable.validar()){
         tipo_entregable=tipenser.modificar(tipo_entregable);
            tipo_entregable = new Tipo_Entregable();
            consultarTipos_Entregable();
    
        }
    }
    public void eliminartipo_Entregable(Tipo_Entregable te){
        tipenser.eliminar(te);
          consultarTipos_Entregable();   
        
    }
    
   
        
       public void consultarTipos_Entregable(){
           setTipos_Entregable(tipenser.consultarTodo(Tipo_Entregable.class));
    }
       
      

    public Tipo_Entregable getTipo_entregable() {
        return tipo_entregable;
    }

    public void setTipo_entregable(Tipo_Entregable tipo_entregable) {
        this.tipo_entregable = tipo_entregable;
    }

    /**
     * @return the tipos_Entregable
     */
    public List<Tipo_Entregable> getTipos_Entregable() {
        return tipos_Entregable;
    }

    /**
     * @param tipos_Entregable the tipos_Entregable to set
     */
    public void setTipos_Entregable(List<Tipo_Entregable> tipos_Entregable) {
        this.tipos_Entregable = tipos_Entregable;
    }

   

 
        
    }

