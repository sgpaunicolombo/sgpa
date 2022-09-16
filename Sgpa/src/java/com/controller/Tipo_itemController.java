/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Tipo_Entregable;
import com.entity.Tipo_Item;
import com.services.Tipo_EntregableServices;
import com.services.Tipo_ItemServices;
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
public class Tipo_itemController implements Serializable{

    private Tipo_Item tipo_item = new Tipo_Item();

    private Tipo_ItemServices tipiser = new Tipo_ItemServices();

    private List<Tipo_Item> tipo_Item = new LinkedList();

    /**
     * Creates a new instance of PeriodoController
     */
    public Tipo_itemController() {
    }
   

    public void registrarTipo_Item() {
        if(getTipo_item().validar()){
            setTipo_item(getTipiser().modificar(getTipo_item()));
            setTipo_item(new Tipo_Item());
            consultarTipo_Item();
    
        }
    }
    public void eliminartipo_Item(Tipo_itemController te){
        getTipiser().eliminar(te);
          consultarTipo_Item();   
        
    }
        
       public void consultarTipo_Item(){
           setTipo_Item(getTipiser().consultarTodo(Tipo_Item.class));
    }

    /**
     * @return the tipo_item
     */
    public Tipo_Item getTipo_item() {
        return tipo_item;
    }

    /**
     * @param tipo_item the tipo_item to set
     */
    public void setTipo_item(Tipo_Item tipo_item) {
        this.tipo_item = tipo_item;
    }

    /**
     * @return the tipiser
     */
    public Tipo_ItemServices getTipiser() {
        return tipiser;
    }

    /**
     * @param tipiser the tipiser to set
     */
    public void setTipiser(Tipo_ItemServices tipiser) {
        this.tipiser = tipiser;
    }

    /**
     * @return the tipo_Item
     */
    public List<Tipo_Item> getTipo_Item() {
        return tipo_Item;
    }

    /**
     * @param tipo_Item the tipo_Item to set
     */
    public void setTipo_Item(List<Tipo_Item> tipo_Item) {
        this.tipo_Item = tipo_Item;
    }
       
      
        
        
           

  
 
        
    }

