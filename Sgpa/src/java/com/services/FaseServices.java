/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Fase;
import com.entity.ProgramaAcademico;
import com.implDao.IFase;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Jcmm
 */
public class FaseServices extends ImplDao<Fase, Long> implements IFase,Serializable{
   
     public List<Fase> obtenerFases(ProgramaAcademico pa){
         List<Fase> fase=new LinkedList();
         try {             
             EntityManager em =getEntityManagger();
             em.getTransaction().begin();               
             String q="select f from Fase f where f.programa.id = ?1";        
             System.out.println(" Consulta: "+q);
             Query qu=em.createQuery(q)
                     .setParameter(1, pa.getId());
                     
             fase=qu.getResultList();
             
             
             em.close();             
         }catch(Exception ex){
             ex.printStackTrace();
         } 
         return fase;
    }
    
}
