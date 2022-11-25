/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Entregable;
import com.entity.Tipo_Entregable;
import com.utilidades.ImageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Dimas
 */
@ManagedBean
@SessionScoped
public class EntregableController implements Serializable {

    private UploadedFile ientregable;

    private Entregable entregable = new Entregable();

    private List<Tipo_Entregable> Tipo_Entregable = new LinkedList();

    public EntregableController() {
    }

    public void seleccionarTipoE(Tipo_Entregable tipo) {
        getEntregable().setTipo(tipo);
    }

    
    public void agregarEntregable(){
        try {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("/imagenInicial.jpg").replace("imagenInicial.jpg", "Imagenes\\Soportes\\");
        getEntregable().setDirArchivo(getIentregable().getInputStream());   
        getEntregable().setFechaEntrega(new Date());
         } catch (IOException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void subirSoporteEntregable() {
        try {
//               File destFile= new File(event.getFile().getFileName());           
//            System.out.println("" + iestudiante.getFileName());
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = servletContext.getRealPath("/imagenInicial.jpg").replace("imagenInicial.jpg", "Imagenes\\Soportes\\");
            ImageUtils.copyFile(getEntregable().getId() + ".jpg", getIentregable().getInputStream(), path);
            System.out.println("" + path);
            //getEstudiante().getEstudiante().setImagenC(path+event.getFile().getFileName()+".jpg");               
        } catch (IOException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the ientregable
     */
    public UploadedFile getIentregable() {
        return ientregable;
    }

    /**
     * @param ientregable the ientregable to set
     */
    public void setIentregable(UploadedFile ientregable) {
        this.ientregable = ientregable;
    }

    /**
     * @return the entregable
     */
    public Entregable getEntregable() {
        return entregable;
    }

    /**
     * @param entregable the entregable to set
     */
    public void setEntregable(Entregable entregable) {
        this.entregable = entregable;
    }

    /**
     * @return the Tipo_Entregable
     */
    public List<Tipo_Entregable> getTipo_Entregable() {
        return Tipo_Entregable;
    }

    /**
     * @param Tipo_Entregable the Tipo_Entregable to set
     */
    public void setTipo_Entregable(List<Tipo_Entregable> Tipo_Entregable) {
        this.Tipo_Entregable = Tipo_Entregable;
    }
}
