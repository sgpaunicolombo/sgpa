/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Matricula;
import com.entity.Periodo;
import com.entity.ProgramaAcademico;
import com.entity.Proyecto_Aula;
import com.implDao.IProyecto_Aula;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Jcmm
 */
public class Proyecto_AulaServices extends ImplDao<Proyecto_Aula, Long> implements IProyecto_Aula,Serializable{
      public List<Proyecto_Aula> obtenerProyectosXPeriodo_Programa(Periodo p,ProgramaAcademico pr){
         List<Proyecto_Aula> proyectos=new LinkedList();
         try {             
             EntityManager em =getEntityManagger();
             em.getTransaction().begin();               
             String q="select pa from Proyecto_Aula pa where pa.periodo.id = ?1 and pa.programa.id = ?2";        
             System.out.println(" Consulta: "+q);
             Query qu=em.createQuery(q)
                     .setParameter(1, p.getId())
                     .setParameter(2, pr.getId());
             proyectos=qu.getResultList();            
             
             em.close();             
         }catch(Exception ex){
             ex.printStackTrace();
         } 
         return proyectos;
    }
}
