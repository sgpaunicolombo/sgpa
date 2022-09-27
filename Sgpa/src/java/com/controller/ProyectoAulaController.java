/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Integrante;
import com.entity.Item_Proyecto;
import com.entity.LiderPA;
import com.entity.Matricula;
import com.entity.Periodo;
import com.entity.ProgramaAcademico;
import com.entity.Proyecto_Aula;
import com.services.IntegranteServices;
import com.services.Item_ProyectoServices;
import com.services.MatriculaServices;
import com.services.Proyecto_AulaServices;
import java.io.Serializable;
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
public class ProyectoAulaController implements Serializable {

    //Objetos de negocio
    private Proyecto_Aula proyecto = new Proyecto_Aula();
    private LiderPA lider = new LiderPA();

    //colecciones
    private List<Item_Proyecto> itenes = new LinkedList();
    private List<Integrante> integrantes = new LinkedList();
    private List<Proyecto_Aula> proyectos = new LinkedList();

    Proyecto_AulaServices proaser = new Proyecto_AulaServices();
    Item_ProyectoServices itemser = new Item_ProyectoServices();
    IntegranteServices inteser = new IntegranteServices();
    MatriculaServices matser = new MatriculaServices();

    /**
     * Creates a new instance of ProyectoAulaController
     */
    public ProyectoAulaController() {
    }

    public void consultarProyectosXPrograma_Periodo() {
        proyectos = proaser.obtenerProyectosXPeriodo_Programa(lider.getPeriodo(), lider.getPrograma());
    }

    public void guardarPA() {
        proyecto.setEstado("Guardado");
        proyecto.setFecha_ingreso(new Date());
        if (validarIntegrantes()) {
            if (proyecto.esvalido()) {
                datosPeriodoPrograma();
                proyecto.generarCodigo();
                proyecto = proaser.modificar(proyecto);
                guardarIntegrates(proyecto);
                FacesUtil.addInfoMessage("Se ha creado un grupo de proyecto de aula");
                consultarProyectosXPrograma_Periodo();
                obtenerIntegrantesXProyectos();
                matser.obtenerMatriculasXperiodo(proyecto.getPeriodo());
                proyecto = new Proyecto_Aula();
                integrantes = new LinkedList();
            }
        }
    }

    public void obtenerIntegrantesXProyectos() {
        integrantes = inteser.obtenerIntegrantesProyectosXPeriodo_Programa(lider.getPeriodo(), lider.getPrograma());
        for (int i=0;i<proyectos.size();i++ ) {
            for (Integrante inte : integrantes) {
                if (inte.getProyecto().getId().equals(proyectos.get(i).getId())) {
                    proyectos.get(i).getIntegrantes().add(inte);
                    integrantes.remove(inte);
                }
            }
        }
    }

    public void datosPeriodoPrograma() {
        proyecto.setPrograma(lider.getPrograma());
        proyecto.setPeriodo(lider.getPeriodo());
        proyecto.setProfesorLider(lider);
        proyecto.setSemestre(lider.getSemestre());
        // System.out.println(proyecto.getPeriodo().getAnio()+" "+proyecto.getProfesorLider().getProfesor().getPrimerNombre());
    }

    public void guardarIntegrates(Proyecto_Aula p) {
        for (Integrante i : integrantes) {
            i.setProyecto(p);
            i.setFechaIngreso(new Date());
            i.setEstado("Activo");
            i.setRol("Estudiante");
            inteser.crear(i);
            modificarMatriculaAsignado(i.getMatricula());
        }
    }

    public void modificarMatriculaAsignado(Matricula m) {
        m.setEstadoPA("Asignado");
        m = matser.modificar(m);
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

    /**
     * @return the lider
     */
    public LiderPA getLider() {
        return lider;
    }

    /**
     * @param lider the lider to set
     */
    public void setLider(LiderPA lider) {
        this.lider = lider;
    }

    /**
     * @return the proyectos
     */
    public List<Proyecto_Aula> getProyectos() {
        return proyectos;
    }

    /**
     * @param proyectos the proyectos to set
     */
    public void setProyectos(List<Proyecto_Aula> proyectos) {
        this.proyectos = proyectos;
    }

}
