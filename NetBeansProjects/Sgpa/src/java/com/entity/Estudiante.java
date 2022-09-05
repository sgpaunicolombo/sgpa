/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author jcmm
 */
@Entity
public class Estudiante extends Usuario implements Serializable {
    private String codigo;
    @OneToMany(mappedBy = "estudiante")
    private List<Matricula> matriculas;

    public Estudiante() {
    }

    public Estudiante(String codigo) {
        this.codigo = codigo;
    }

    public Estudiante(String codigo, Long id, String estado, String login, String password, String tipo, String tipo_ide, String identificacion, String email, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String telefono, String genero) {
        super(id, estado, login, password, tipo, tipo_ide, identificacion, email, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, genero);
        this.codigo = codigo;
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
        

}
