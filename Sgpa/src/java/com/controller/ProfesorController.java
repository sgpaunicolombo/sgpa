/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Profesor;
import com.services.ProfesorServices;
import com.utilidades.GestorImagenes;
import com.utilidades.ImageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author jcmm
 */
@ManagedBean
@SessionScoped
public class ProfesorController implements Serializable {

    private Profesor profesor = new Profesor();

    ProfesorServices profser = new ProfesorServices();

    private List<Profesor> profesores = new LinkedList();

    //variables de control
    private String paginaActualP = "";
    private UploadedFile iprofesor;

    /**
     * Creates a new instance of ProfesorController
     */
    public ProfesorController() {
    }

    public void registrarprofesor() {
        profesor.setTipo("Profesor");
        profesor.setEstado("Activo");
        if (profesor.validarUsuario()) {
            profser.modificar(profesor);
            profesor = new Profesor();
            obtenerProfesores();
        }
    }
     public void actualizarprofesor() {        
        profesor.setEstado("Activo");
        if (profesor.validarUsuario()) {
            profesor=profser.modificar(profesor);           
        }
    }

    public void subirImagenProfesor() {
        try {  
//               File destFile= new File(event.getFile().getFileName());           
               System.out.println(""+iprofesor.getFileName());
               ServletContext servletContext = (ServletContext) 
               FacesContext.getCurrentInstance().getExternalContext().getContext();
               String path=servletContext.getRealPath("/imagenInicial.jpg").replace("imagenInicial.jpg", "Imagenes\\Perfiles\\");
               ImageUtils.copyFile(profesor.getId()+".jpg", iprofesor.getInputStream(),path);
               System.out.println(""+path);
                //getEstudiante().getEstudiante().setImagenC(path+event.getFile().getFileName()+".jpg");               
        } catch (IOException ex) {
            Logger.getLogger(ProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }	
    }
    
     public void gtipo_Entregable(){
        paginaActualP= "/Profesor/GestorTipo_Entregable.xhtml";
    }
     
     public void gtipo_Item(){
        paginaActualP= "/Profesor/GestorTipo_Item.xhtml";
    }

    
    
    public void miperfil() {
        paginaActualP = "/Profesor/PerfilProfesor.xhtml";
    }

//    public void probarPath(){
//        System.out.println(""+profesor.imagenPerfil());
//    }
    public void obtenerPrtofesor(Long id) {
        profesor = profser.consultar(Profesor.class, id);
    }

    public void obtenerProfesores() {
        profesores = profser.consultarTodo(Profesor.class);
    }

    /**
     * @return the profesor
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * @return the profesores
     */
    public List<Profesor> getProfesores() {
        return profesores;
    }

    /**
     * @param profesores the profesores to set
     */
    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    /**
     * @return the paginaActualP
     */
    public String getPaginaActualP() {
        return paginaActualP;
    }

    /**
     * @param paginaActualP the paginaActualP to set
     */
    public void setPaginaActualP(String paginaActualP) {
        this.paginaActualP = paginaActualP;
    }

    /**
     * @return the iprofesor
     */
    public UploadedFile getIprofesor() {
        return iprofesor;
    }

    /**
     * @param iprofesor the iprofesor to set
     */
    public void setIprofesor(UploadedFile iprofesor) {
        this.iprofesor = iprofesor;
    }

}