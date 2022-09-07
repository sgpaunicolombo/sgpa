/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;
// un cambio para que aja
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author jcmm
 */
@Entity
public class ReporteTutoria implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Profesor profesor;
    @ManyToOne
    private Tutoria tutoria;
    private String recomendaciones;
    private String compromisos;

    public ReporteTutoria() {
    }

    public ReporteTutoria(Long id, Profesor profesor, Tutoria tutoria, String recomendaciones, String compromisos) {
        this.id = id;
        this.profesor = profesor;
        this.tutoria = tutoria;
        this.recomendaciones = recomendaciones;
        this.compromisos = compromisos;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteTutoria)) {
            return false;
        }
        ReporteTutoria other = (ReporteTutoria) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ReporteTutoria[ id=" + getId() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
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
     * @return the tutoria
     */
    public Tutoria getTutoria() {
        return tutoria;
    }

    /**
     * @param tutoria the tutoria to set
     */
    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    /**
     * @return the recomendaciones
     */
    public String getRecomendaciones() {
        return recomendaciones;
    }

    /**
     * @param recomendaciones the recomendaciones to set
     */
    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    /**
     * @return the compromisos
     */
    public String getCompromisos() {
        return compromisos;
    }

    /**
     * @param compromisos the compromisos to set
     */
    public void setCompromisos(String compromisos) {
        this.compromisos = compromisos;
    }
    
}
