/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Integrante;
import com.entity.Matricula;
import com.entity.Periodo;
import com.entity.ProgramaAcademico;
import com.entity.Proyecto_Aula;
import com.implDao.IIntegrante;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Jcmm
 */
public class IntegranteServices extends ImplDao<Integrante, Long> implements IIntegrante,Serializable{
   public List<Integrante> obtenerIntegrantesProyectosXPeriodo_Programa(Periodo p,ProgramaAcademico pr){
         List<Integrante> integrantes=new LinkedList();
         try {             
             EntityManager em =getEntityManagger();
             em.getTransaction().begin();               
             String q="select i from Integrante i where i.proyecto.periodo.id = ?1 and i.proyecto.programa.id = ?2";        
             System.out.println(" Consulta: "+q);
             Query qu=em.createQuery(q)
                     .setParameter(1, p.getId())
                     .setParameter(2, pr.getId());
             integrantes=qu.getResultList();            
             
             em.close();             
         }catch(Exception ex){
             ex.printStackTrace();
         } 
         return integrantes;
    }
   
    public List<Integrante> obtenerIntegrantesProyecto(Proyecto_Aula pr){
         List<Integrante> integrantes=new LinkedList();
         try {             
             EntityManager em =getEntityManagger();
             em.getTransaction().begin();               
             String q="select i from Integrante i where i.proyecto.id = ?1";        
             System.out.println(" Consulta: "+q);
             Query qu=em.createQuery(q)
                     .setParameter(1, pr.getId());
             integrantes=qu.getResultList();            
             
             em.close();             
         }catch(Exception ex){
             ex.printStackTrace();
         } 
         return integrantes;
    }
   
   
   
   
    public Integrante obtenerIntegranteXMatricula(Matricula m){
        EntityManager em =ImplDao.getEntityManagger();
        Integrante inte=new Integrante();
        em.getTransaction().begin();        
        try{
        String q="select i from Integrante i where i.matricula.id = ?1";        
        Query qu=em.createQuery(q)
                .setParameter(1, m.getId());
        inte=(Integrante)qu.getSingleResult();
        }catch(javax.persistence.NoResultException nre){
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }finally{
            em.close();        
        }        
        return  inte;
    }
}
