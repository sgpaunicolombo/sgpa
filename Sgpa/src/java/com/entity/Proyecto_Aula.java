/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import com.controller.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author jcmm
 */
@Entity
public class Proyecto_Aula implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String estado;//Propuesta - Aprobado - Cancelado - Produccion -  Para Sustentar - Finalizado
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_ingreso;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_aprobacion;
    @ManyToOne
    private Profesor coordinadorPA;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_finalizacion;
    @ManyToOne
    private LiderPA profesorLider;
    private String titulo;
    private String problematica;
    private String codigo;
    private String semestre;
    @ManyToOne
    private ProgramaAcademico programa;
    @ManyToOne
    private Periodo periodo;
    @OneToMany(mappedBy = "proyecto")
    private List<Item_Proyecto> item_Proyectos;
    @OneToMany(mappedBy = "proyecto")
    private List<Integrante> integrantes;
    @OneToMany(mappedBy = "proyecto")
    private List<Avance> avances;
    @OneToMany(mappedBy = "proyecto")
    private List<Tutor> tutors;
    @OneToMany(mappedBy = "proyecto")
    private List<Tutoria> tutorias;

    public Proyecto_Aula() {
    }

    public Proyecto_Aula(Long id, String estado, Date fecha_inicio, Date fecha_ingreso, Date fecha_aprobacion, Profesor coordinadorPA, Date fecha_finalizacion, LiderPA profesorLider, String titulo, String problematica, String codigo, String semestre, ProgramaAcademico programa, Periodo periodo, List<Item_Proyecto> item_Proyectos, List<Integrante> integrantes, List<Avance> avances, List<Tutor> tutors, List<Tutoria> tutorias) {
        this.id = id;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_aprobacion = fecha_aprobacion;
        this.coordinadorPA = coordinadorPA;
        this.fecha_finalizacion = fecha_finalizacion;
        this.profesorLider = profesorLider;
        this.titulo = titulo;
        this.problematica = problematica;
        this.codigo = codigo;
        this.semestre = semestre;
        this.programa = programa;
        this.periodo = periodo;
        this.item_Proyectos = item_Proyectos;
        this.integrantes = integrantes;
        this.avances = avances;
        this.tutors = tutors;
        this.tutorias = tutorias;
    }

    
    
    public boolean esvalido(){
        boolean valido=true;
        try{
        if(this.periodo.getId()<=0){
            valido=false;
            FacesUtil.addErrorMessage("No se le ha asignado el periodo al grupo");
        } 
        if(this.programa.getId()<=0){
            valido=false;
            FacesUtil.addErrorMessage("No se le ha asignado el Programa academico al grupo");
        } if(this.semestre.equals("")){
            valido=false;
            FacesUtil.addErrorMessage("No se le ha asignado el Semestre al grupo");
        } 
        if( this.getProfesorLider().getId()<=0){
            valido=false;
            FacesUtil.addErrorMessage("El proyecto no posee Profesor Lider ");
        } if(this.estado.equals("")){
            valido=false;
            FacesUtil.addErrorMessage("El estado del proyecto no puede ser Null");
        }   
        }catch(java.lang.NullPointerException npe){
            
        }
        return valido;
    }
    
  

     public void generarCodigo() {
        Random aleatorio=new Random();
        this.codigo = this.getPrograma().getCodigo() + this.semestre + this.getPeriodo().getAnio()+aleatorio.nextInt(1000);
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
        if (!(object instanceof Proyecto_Aula)) {
            return false;
        }
        Proyecto_Aula other = (Proyecto_Aula) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Proyecto_Aula[ id=" + getId() + " ]";
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the fecha_inicio
     */
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @return the fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the fecha_aprobacion
     */
    public Date getFecha_aprobacion() {
        return fecha_aprobacion;
    }

    /**
     * @param fecha_aprobacion the fecha_aprobacion to set
     */
    public void setFecha_aprobacion(Date fecha_aprobacion) {
        this.fecha_aprobacion = fecha_aprobacion;
    }

    /**
     * @return the coordinadorPA
     */
    public Profesor getCoordinadorPA() {
        return coordinadorPA;
    }

    /**
     * @param coordinadorPA the coordinadorPA to set
     */
    public void setCoordinadorPA(Profesor coordinadorPA) {
        this.coordinadorPA = coordinadorPA;
    }

    /**
     * @return the fecha_finalizacion
     */
    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    /**
     * @param fecha_finalizacion the fecha_finalizacion to set
     */
    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the problematica
     */
    public String getProblematica() {
        return problematica;
    }

    /**
     * @param problematica the problematica to set
     */
    public void setProblematica(String problematica) {
        this.problematica = problematica;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the semestre
     */
    public String getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

  

    /**
     * @return the programa
     */
    public ProgramaAcademico getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(ProgramaAcademico programa) {
        this.programa = programa;
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
     * @return the profesorLider
     */
    public LiderPA getProfesorLider() {
        return profesorLider;
    }

    /**
     * @param profesorLider the profesorLider to set
     */
    public void setProfesorLider(LiderPA profesorLider) {
        this.profesorLider = profesorLider;
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

}
