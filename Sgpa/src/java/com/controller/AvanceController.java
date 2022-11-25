/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Avance;
import com.entity.Entregable;
import com.entity.Fase;
import com.entity.Integrante;
import com.entity.Proyecto_Aula;
import com.services.AvanceServices;
import com.services.EntregableServices;
import com.utilidades.ImageUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Dimas
 */
@ManagedBean
@SessionScoped
public class AvanceController implements Serializable {

    private Avance avance = new Avance();
    private Proyecto_Aula proyecto=new Proyecto_Aula();
    private Integrante integrante=new Integrante();
    private List<Entregable> entregables = new LinkedList();

    @ManagedProperty("#{entregableController}")
    private EntregableController entrcon = new EntregableController();

    private int indexTab = 0;

    private boolean mostPEntregable = false;

    AvanceServices avaser = new AvanceServices();
    EntregableServices entser = new EntregableServices();

    /**
     * Creates a new instance of AvanceController
     */
    public AvanceController() {
    }

    public void irEntregable() {
        mostPEntregable = true;
        entrcon.getEntregable().setAvance(avance);
    }

    public void volverAvance() {
        mostPEntregable = false;
        entrcon.getEntregable().setAvance(new Avance());
    }

    public void agregarEntregable() {
        entrcon.agregarEntregable();
        entregables.add(entrcon.getEntregable());
        mostPEntregable = false;
        entrcon.setEntregable(new Entregable());
    }

    public void agregarFase(Fase f) {
        System.out.println("" + f.getNumero());
        indexTab = 1;
        getAvance().setFase(f);

    }

    public void almacenarAvance() {
        avance.setFechaEntrega(new Date());
        avance.setEstado("Entregado");
        avance.setProyecto(proyecto);
        avance = avaser.modificar(avance);
        for(int i=0;i<entregables.size();i++){
            entregables.get(i).setAvance(avance);
            entregables.set(i,entser.modificar(entregables.get(i)));
        } 
//        for (Entregable e : entregables) {
//            subirSoporteEntregable(e.getId().toString(), e.getDirArchivo());
//        }

    }

    public void subirSoporteEntregable(String id, InputStream is) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("/imagenInicial.jpg").replace("imagenInicial.jpg", "Imagenes\\Soportes\\");
        ImageUtils.copyFile(id + ".jpg", is, path);
        System.out.println("" + path);
    }

    /**
     * @return the avance
     */
    public Avance getAvance() {
        return avance;
    }

    /**
     * @param avance the avance to set
     */
    public void setAvance(Avance avance) {
        this.avance = avance;
    }

    /**
     * @return the indexTab
     */
    public int getIndexTab() {
        return indexTab;
    }

    /**
     * @param indexTab the indexTab to set
     */
    public void setIndexTab(int indexTab) {
        this.indexTab = indexTab;
    }

    /**
     * @return the entregables
     */
    public List<Entregable> getEntregables() {
        return entregables;
    }

    /**
     * @param entregables the entregables to set
     */
    public void setEntregables(List<Entregable> entregables) {
        this.entregables = entregables;
    }

    /**
     * @return the mostPEntregable
     */
    public boolean isMostPEntregable() {
        return mostPEntregable;
    }

    /**
     * @param mostPEntregable the mostPEntregable to set
     */
    public void setMostPEntregable(boolean mostPEntregable) {
        this.mostPEntregable = mostPEntregable;
    }

    /**
     * @return the entrcon
     */
    public EntregableController getEntrcon() {
        return entrcon;
    }

    /**
     * @param entrcon the entrcon to set
     */
    public void setEntrcon(EntregableController entrcon) {
        this.entrcon = entrcon;
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
     * @return the integrante
     */
    public Integrante getIntegrante() {
        return integrante;
    }

    /**
     * @param integrante the integrante to set
     */
    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

}
