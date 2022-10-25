/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Estudiante;
import com.entity.Item_Proyecto;
import com.entity.Matricula;
import com.entity.Periodo;
import com.entity.Proyecto_Aula;
import com.entity.Tipo_Item;
import com.services.EstudianteServices;
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
 * @author jcmm
 */
@ManagedBean
@SessionScoped
public class EstudianteController implements Serializable {

    //objetos de negocio
    private Estudiante estudiante = new Estudiante();
    private Periodo periodo = new Periodo();//para almacenar el el periodo actual

    //controlladores
    @ManagedProperty("#{matriculaController}")
    private MatriculaController matcont = new MatriculaController();
    @ManagedProperty("#{proyectoAulaController}")
    private ProyectoAulaController proacon = new ProyectoAulaController();
    @ManagedProperty("#{tipo_itemController}")
    private Tipo_itemController tipicon = new Tipo_itemController();

    //Servicios
    EstudianteServices estser = new EstudianteServices();

    //colecciones    
    private List<Estudiante> estudiantes = new LinkedList();

    //variable de control
    private boolean mpanelInscripcion = true;
    private String paginaActualE = "";
    private UploadedFile iestudiante;
    private boolean mpanelAItems = false;
    private boolean mpanelItem = false;

    public void consultarMatriculaEstudiante() {
        matcont.consultarMatriculaXEstudianteEnPeriodo(estudiante, periodo);
    }

    public void consultarProyectoXMatricula() {
        System.out.println(""+matcont.getMatricula());
        proacon.obtenerProyectoAulaXMatricula(matcont.getMatricula());
    }

    public void agregarItem() {
        mpanelAItems = true;
    }

    public void volverItems() {
        mpanelAItems = false;
    }

    public void seleccionarTItem(Tipo_Item ip) {
        //tipicon.setTipo_item(ip);
        proacon.getItem().setTipo(ip);
        mpanelItem = true;
        mpanelAItems = false;
    }

    public void registrarItem() {
        proacon.guardarItem(matcont.getMatricula());
        volverAItenes();
    }

    public void volverAItenes() {
        mpanelItem = false;
    }

    public void seleccionarItem(Item_Proyecto ite) {
        ite.setFechamodificacion(new Date());
        proacon.setItem(ite);
        mpanelItem = true;
        mpanelAItems = false;
    }

    public void eliminarItem(Item_Proyecto ite){
        proacon.itemser.eliminar(ite);
        proacon.getProyecto().getItenes_Proyecto().remove(ite);
    }
    
    /**
     * Creates a new instance of EstudianteController
     */
    public EstudianteController() {
    }

    public void limpiarDatos() {
        matcont.setMatricula(new Matricula());
        proacon.setProyecto(new Proyecto_Aula());
    }

    public void inscribirEstudiante() {
        estudiante.setTipo("Estudiante");
        estudiante.setEstado("Pre-Matricula");
        if (estudiante.validarUsuario()) {
            estser.modificar(estudiante);
            estudiante = new Estudiante();
            mpanelInscripcion = false;
        }
    }

    public void guardarProyectoAula() {
        proacon.guardarPA();
    }

    public void publicarProyectoAula(){
        proacon.publicarPA();
    }
    
    public void subirImagenProfesor() {
        try {
//               File destFile= new File(event.getFile().getFileName());           
            System.out.println("" + iestudiante.getFileName());
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = servletContext.getRealPath("/imagenInicial.jpg").replace("imagenInicial.jpg", "Imagenes\\Perfiles\\");
            ImageUtils.copyFile(estudiante.getId() + ".jpg", iestudiante.getInputStream(), path);
            System.out.println("" + path);
            //getEstudiante().getEstudiante().setImagenC(path+event.getFile().getFileName()+".jpg");               
        } catch (IOException ex) {
            Logger.getLogger(EstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void miperfil() {
        paginaActualE = "/Estudiante/PerfilEstudiante.xhtml";
    }

    public void g_propuesta() {
        paginaActualE = "/Estudiante/Gestor_Propuestas.xhtml";
    }
    public void g_proyecto() {
        paginaActualE = "/Estudiante/Gestor_Proyecto_Aula.xhtml";
    }

    public void obtenerEstudiantes() {
        estudiantes = estser.consultarTodo(Estudiante.class);
    }

    public void obtenerEstudiante(Long id) {
        estudiante = estser.consultar(Estudiante.class, id);
    }

    /**
     * @return the estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * @param estudiante the estudiante to set
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * @return the mpanelInscripcion
     */
    public boolean isMpanelInscripcion() {
        return mpanelInscripcion;
    }

    /**
     * @param mpanelInscripcion the mpanelInscripcion to set
     */
    public void setMpanelInscripcion(boolean mpanelInscripcion) {
        this.mpanelInscripcion = mpanelInscripcion;
    }

    /**
     * @return the paginaActualE
     */
    public String getPaginaActualE() {
        return paginaActualE;
    }

    /**
     * @param paginaActualE the paginaActualE to set
     */
    public void setPaginaActualE(String paginaActualE) {
        this.paginaActualE = paginaActualE;
    }

    /**
     * @return the estudiantes
     */
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * @return the iestudiante
     */
    public UploadedFile getIestudiante() {
        return iestudiante;
    }

    /**
     * @param iestudiante the iestudiante to set
     */
    public void setIestudiante(UploadedFile iestudiante) {
        this.iestudiante = iestudiante;
    }

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the matcont
     */
    public MatriculaController getMatcont() {
        return matcont;
    }

    /**
     * @param matcont the matcont to set
     */
    public void setMatcont(MatriculaController matcont) {
        this.matcont = matcont;
    }

    /**
     * @return the proacon
     */
    public ProyectoAulaController getProacon() {
        return proacon;
    }

    /**
     * @param proacon the proacon to set
     */
    public void setProacon(ProyectoAulaController proacon) {
        this.proacon = proacon;
    }

    /**
     * @return the mpanelAItems
     */
    public boolean isMpanelAItems() {
        return mpanelAItems;
    }

    /**
     * @param mpanelAItems the mpanelAItems to set
     */
    public void setMpanelAItems(boolean mpanelAItems) {
        this.mpanelAItems = mpanelAItems;
    }

    /**
     * @return the mpanelItem
     */
    public boolean isMpanelItem() {
        return mpanelItem;
    }

    /**
     * @param mpanelItem the mpanelItem to set
     */
    public void setMpanelItem(boolean mpanelItem) {
        this.mpanelItem = mpanelItem;
    }

    /**
     * @return the tipicon
     */
    public Tipo_itemController getTipicon() {
        return tipicon;
    }

    /**
     * @param tipicon the tipicon to set
     */
    public void setTipicon(Tipo_itemController tipicon) {
        this.tipicon = tipicon;
    }

}
