/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Area;
import com.services.AreaServices;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jcmm
 */
@ManagedBean
@SessionScoped
public class AreaController implements Serializable {

    //Objetos de Negocio
    private Area area = new Area();
    

    //Colecciones
    private List<Area> areas = new LinkedList();

    //Servicios
    AreaServices ares = new AreaServices();

    //Variables de control
    private boolean mpanelLogin = true;
    private String paginaActual = "";
    private String paginaSU="";

    //controladores asociados
    @ManagedProperty("#{coordinadorController}")
    private CoordinadorController coorcon=new CoordinadorController();
    @ManagedProperty("#{programaController}")
    private ProgramaController procon=new ProgramaController();
    /**
     * Creates a new instance of AreaController
     */
    public AreaController() {
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public AreaServices getAres() {
        return ares;
    }

    public void setAres(AreaServices ares) {
        this.ares = ares;
    }

    public boolean isMpanelLogin() {
        return mpanelLogin;
    }

    public void setMpanelLogin(boolean mpanelLogin) {
        this.mpanelLogin = mpanelLogin;
    }

    public String getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(String paginaActual) {
        this.paginaActual = paginaActual;
    }

    public String getPaginaSU() {
        return paginaSU;
    }

    public void setPaginaSU(String paginaSU) {
        this.paginaSU = paginaSU;
    }

    public CoordinadorController getCoorcon() {
        return coorcon;
    }

    public void setCoorcon(CoordinadorController coorcon) {
        this.coorcon = coorcon;
    }

    public ProgramaController getProcon() {
        return procon;
    }

    public void setProcon(ProgramaController procon) {
        this.procon = procon;
    }
    
    
}
