/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author sgpaAdmin2
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Estudiante extends Usuario implements Serializable {

    @OneToOne(mappedBy = "id")
    private Estudiante estudianteId;
    @OneToOne
    private Estudiante id;
    Integer semestre;
    
    
}
