/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Integrante;
import com.entity.LiderPA;
import com.entity.Periodo;
import com.entity.Profesor;
import com.entity.ProgramaAcademico;
import com.entity.Proyecto_Aula;
import com.entity.Seccion;
import com.entity.Semestre;
import com.services.LiderPAServices;
import com.services.ProfesorServices;
import com.utilidades.GestorImagenes;
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
public class ProfesorController implements Serializable {

    //objetos de negocio
    private Profesor profesor = new Profesor();
    private Periodo periodo = new Periodo();//para almacenar el el periodo actual
    private LiderPA liderPa;// para conocer si el profesor es lider
    private ProgramaAcademico coordinadorPa;// para conocer si el profesor es coordinador
    ProfesorServices profser = new ProfesorServices();

    //Colecciones
    private List<Profesor> profesores = new LinkedList();
    private List<LiderPA> semestresLider = new LinkedList();//semestres en la cual un profesor es lider
    private List<Proyecto_Aula> proyectosSemestre = new LinkedList();

    //Servicios
    LiderPAServices lidpaser = new LiderPAServices();

    //controladores
    @ManagedProperty("#{proyectoAulaController}")
    private ProyectoAulaController proacon = new ProyectoAulaController();
    @ManagedProperty("#{matriculaController}")
    private MatriculaController matcont = new MatriculaController();

    //variables de control
    private String paginaActualP = "";
    private UploadedFile iprofesor;
    private boolean mostPanelSemestres;//mostrar los semestres en la cual un profesor es lider
    private boolean mostPanelProyectoAula;//para mostrar la informacion del proyecto seleccionado

    /**
     * Creates a new instance of ProfesorController
     */
    public ProfesorController() {
    }

    public void consultarProfesores() {
        setProfesores(profser.consultarTodo(Profesor.class));
    }

    public void limpiarDatos() {
        paginaActualP = "";
        profesor = new Profesor();
    }

    public void seleccionarLider(LiderPA lider) {
        liderPa = lider;
        proacon.setLider(lider);
        proyectosXSeccion(lider.getSeccion());
        mostPanelSemestres = false;

    }

    public void volverSemestres() {
        mostPanelSemestres = true;
    }

    public void proyectosXSeccion(Seccion s) {
        proyectosSemestre = new LinkedList();
        for (Proyecto_Aula p : proacon.getProyectos()) {
            if (p.getSeccion().getId().equals(s.getId())) {
                proyectosSemestre.add(p);
            }
        }
    }

    public void consultarProyecto(Proyecto_Aula pa) {
        proacon.setProyecto(pa);
//        System.out.println("" + proacon.getProyecto().validarProyectoParaAprobar());
        mostPanelProyectoAula = false;
    }

    public void esLiderPA() {
        semestresLider = null;
        semestresLider = lidpaser.obtenersemestresLiderPAXProfesor(profesor, periodo);
        //System.out.println(""+semestresLider.size());
        if (semestresLider.size() > 0) {
            mostPanelSemestres = true;
            liderPa = semestresLider.get(0);
            proacon.setLider(liderPa);
            proacon.consultarProyectosXPrograma_Periodo();
            proacon.obtenerIntegrantesXProyectos();
        }
    }

    public boolean habilitarLider() {
        if (semestresLider.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void consultarMatriculasXPeriodo() {
        matcont.consultarEstudiantesMatriculadosXPeriodo(periodo);
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
            profesor = profser.modificar(profesor);
        }
    }

    public void guardarProyectoAula() {
        proacon.getProyecto().setSeccion(liderPa.getSeccion());
        proacon.crearPA();
    }

    public void aprobarProyectoAula() {
        if (proacon.getProyecto().validarProyectoParaAprobar()) {
            proacon.getProyecto().setFecha_aprobacion(new Date());
            proacon.aprobarPA();
        }
    }

    public void aplazarProyectoAula() {
        proacon.getProyecto().setFecha_aprobacion(new Date());
        proacon.aplazarPA();
    }

    public void desvincularIntegrante(Integrante inte, Proyecto_Aula pa) {
        proacon.eliminarIntegrante(inte, pa);
        matcont.consultarEstudiantesMatriculadosXPeriodo(periodo);
    }

    public void eliminarProyectoAula(Proyecto_Aula pa) {
        proacon.eliminarProyecto(pa);
        matcont.consultarEstudiantesMatriculadosXPeriodo(periodo);
    }

    public void subirImagenProfesor() {
        try {
//               File destFile= new File(event.getFile().getFileName());           
            System.out.println("" + iprofesor.getFileName());
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = servletContext.getRealPath("/imagenInicial.jpg").replace("imagenInicial.jpg", "Imagenes\\Perfiles\\");
            ImageUtils.copyFile(profesor.getId() + ".jpg", iprofesor.getInputStream(), path);
            System.out.println("" + path);
            //getEstudiante().getEstudiante().setImagenC(path+event.getFile().getFileName()+".jpg");               
        } catch (IOException ex) {
            Logger.getLogger(ProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gtipo_Entregable() {
        paginaActualP = "/Profesor/GestorTipo_Entregable.xhtml";
    }

    public void gtipo_Item() {
        paginaActualP = "/Profesor/GestorTipo_Item.xhtml";
    }

    public void ggrupos() {
        paginaActualP = "/Profesor/GestorGrupos.xhtml";
    }

    public void gproyectosaula() {
        mostPanelSemestres = true;
        mostPanelProyectoAula = true;
        paginaActualP = "/Profesor/GUIProyectosAula.xhtml";
    }

    public void gproflider() {
        paginaActualP = "/Profesor/GestorProfesorLider.xhtml";
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
     * @return the liderPa
     */
    public LiderPA getLiderPa() {
        return liderPa;
    }

    /**
     * @return the coordinadorPa
     */
    public ProgramaAcademico getcoordinadorPa() {
        return coordinadorPa;
    }

    /**
     * @param liderPa the liderPa to set
     */
    public void setLiderPa(LiderPA liderPa) {
        this.liderPa = liderPa;
    }

    public ProgramaAcademico getCoordinadorPa() {
        return coordinadorPa;
    }

    public void setCoordinadorPa(ProgramaAcademico coordinadorPa) {
        this.coordinadorPa = coordinadorPa;
    }

    /**
     * @return the semestresLider
     */
    public List<LiderPA> getSemestresLider() {
        return semestresLider;
    }

    /**
     * @param semestresLider the semestresLider to set
     */
    public void setSemestresLider(List<LiderPA> semestresLider) {
        this.semestresLider = semestresLider;
    }

    /**
     * @return the mostPanelSemestres
     */
    public boolean isMostPanelSemestres() {
        return mostPanelSemestres;
    }

    /**
     * @param mostPanelSemestres the mostPanelSemestres to set
     */
    public void setMostPanelSemestres(boolean mostPanelSemestres) {
        this.mostPanelSemestres = mostPanelSemestres;
    }

    /**
     * @return the proyectosSemestre
     */
    public List<Proyecto_Aula> getProyectosSemestre() {
        return proyectosSemestre;
    }

    /**
     * @param proyectosSemestre the proyectosSemestre to set
     */
    public void setProyectosSemestre(List<Proyecto_Aula> proyectosSemestre) {
        this.proyectosSemestre = proyectosSemestre;
    }

    /**
     * @return the mostPanelProyectoAula
     */
    public boolean isMostPanelProyectoAula() {
        return mostPanelProyectoAula;
    }

    /**
     * @param mostPanelProyectoAula the mostPanelProyectoAula to set
     */
    public void setMostPanelProyectoAula(boolean mostPanelProyectoAula) {
        this.mostPanelProyectoAula = mostPanelProyectoAula;
    }

}
