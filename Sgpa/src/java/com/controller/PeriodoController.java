/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Periodo;
import com.services.PeriodoServices;
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
public class PeriodoController implements Serializable{

    private Periodo periodo = new Periodo();

    PeriodoServices perser = new PeriodoServices();

    private List<Periodo> periodos = new LinkedList();

    /**
     * Creates a new instance of PeriodoController
     */
    public PeriodoController() {
    }

    public void registrarPeriodo() {
        periodo.setFecha(new Date());
        if (periodo.validar()) {
            periodo = perser.modificar(periodo);
            periodo = new Periodo();
            obtenerPeriodos();
        }
    }

    public void obtenerPeriodos() {
        periodos = perser.consultarTodo(Periodo.class);
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
     * @return the periodos
     */
    public List<Periodo> getPeriodos() {
        return periodos;
    }

    /**
     * @param periodos the periodos to set
     */
    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

}
