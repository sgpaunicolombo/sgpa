/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Integrante;
import com.entity.Item_Proyecto;
import com.entity.Matricula;
import com.entity.Proyecto_Aula;
import com.services.Item_ProyectoServices;
import com.services.Proyecto_AulaServices;
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
public class ProyectoAulaController {

    //Objetos de negocio
    private Proyecto_Aula proyecto = new Proyecto_Aula();

    //colecciones
    private List<Item_Proyecto> itenes = new LinkedList();
    private List<Integrante> integrantes = new LinkedList();

    Proyecto_AulaServices proaser = new Proyecto_AulaServices();
    Item_ProyectoServices itemser = new Item_ProyectoServices();

    /**
     * Creates a new instance of ProyectoAulaController
     */
    public ProyectoAulaController() {
    }

    public void consultarProyecto() {

    }

    public void guardar() {
        proyecto.setEstado("Guardado");
        proyecto.setFecha_ingreso(new Date());
        if (validarIntegrantes()) {
            if (proyecto.esvalido()) {
                proaser.modificar(proyecto);
                
                FacesUtil.addInfoMessage("Se ha creado un grupo de proyecto de aula");
                proyecto = new Proyecto_Aula();    
                integrantes = new LinkedList();
           }
        }
    }

    public void publicar() {

    }

    public void quitarIntegrante(Integrante inte) {
        if (existeIntegrante(inte.getMatricula())) {
            integrantes.remove(inte);
        }
    }

    public boolean validarIntegrantes() {
        boolean valido = true;
        int nintegrantes = integrantes.size();
        if (nintegrantes > 5) {
            valido = false;
            FacesUtil.addErrorMessage("EL numero de integrantes es superior al reglamentado");
        }
        if (nintegrantes <= 0) {
            valido = false;
            FacesUtil.addErrorMessage("No hay integrantes asignados al grupo");
        }
        return valido;
    }

    public void agregarIntegrante(Matricula m) {
        Integrante integrante = new Integrante();
        integrante.setMatricula(m);
        integrante.setProyecto(proyecto);
        integrante.setFechaIngreso(new Date());
        integrante.setEstado("Activo");
        integrante.setRol("Estudiante");
        if (!existeIntegrante(m)) {
            integrantes.add(integrante);
        }
    }

    public boolean existeIntegrante(Matricula m) {
        boolean existe = false;
        if (integrantes.size() <= 0) {
            existe = false;
        } else {
            for (Integrante inte : integrantes) {
                if (inte.getMatricula().getId().equals(m.getId())) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    /**
     * @return the proyecto
     */
    public Proyecto_Aula getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(Proyecto_Aula proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @return the itenes
     */
    public List<Item_Proyecto> getItenes() {
        return itenes;
    }

    /**
     * @param itenes the itenes to set
     */
    public void setItenes(List<Item_Proyecto> itenes) {
        this.itenes = itenes;
    }

    /**
     * @return the integrantes
     */
    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    /**
     * @param integrantes the integrantes to set
     */
    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

}
