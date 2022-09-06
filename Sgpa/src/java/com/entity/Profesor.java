/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author jcmm
 */
@Entity
public class Profesor extends Usuario implements Serializable {
    private String perfil;
    private String idioma2;
    private String idioma3;
    private String tipocontrato;
    private String nivelacademico;
    @OneToMany(mappedBy = "coordinadorPA")
    private List<Proyecto_Aula> proyecto_Aulas;
    @OneToMany(mappedBy = "profesor")
    private List<Tutor> tutors;
    @OneToMany(mappedBy = "coordinador")
    private List<Tutoria> tutorias;
    @OneToMany(mappedBy = "profesor")
    private List<ReporteTutoria> reporteTutorias;

    public Profesor() {
    }

    public Profesor(String perfil, String idioma2, String idioma3, String tipocontrato, String nivelacademico, Long id, String estado, String login, String password, String tipo, String tipo_ide, String identificacion, String email, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String genero) {
        super(id, estado, login, password, tipo, tipo_ide, identificacion, email, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, genero);
        this.perfil = perfil;
        this.idioma2 = idioma2;
        this.idioma3 = idioma3;
        this.tipocontrato = tipocontrato;
        this.nivelacademico = nivelacademico;
    }

    public Profesor(String perfil, String idioma2, String idioma3, String tipocontrato, String nivelacademico) {
        this.perfil = perfil;
        this.idioma2 = idioma2;
        this.idioma3 = idioma3;
        this.tipocontrato = tipocontrato;
        this.nivelacademico = nivelacademico;
    }

    
    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the idioma2
     */
    public String getIdioma2() {
        return idioma2;
    }

    /**
     * @param idioma2 the idioma2 to set
     */
    public void setIdioma2(String idioma2) {
        this.idioma2 = idioma2;
    }

    /**
     * @return the idioma3
     */
    public String getIdioma3() {
        return idioma3;
    }

    /**
     * @param idioma3 the idioma3 to set
     */
    public void setIdioma3(String idioma3) {
        this.idioma3 = idioma3;
    }

    /**
     * @return the tipocontrato
     */
    public String getTipocontrato() {
        return tipocontrato;
    }

    /**
     * @param tipocontrato the tipocontrato to set
     */
    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    /**
     * @return the nivelacademico
     */
    public String getNivelacademico() {
        return nivelacademico;
    }

    /**
     * @param nivelacademico the nivelacademico to set
     */
    public void setNivelacademico(String nivelacademico) {
        this.nivelacademico = nivelacademico;
    }
    
  
}
