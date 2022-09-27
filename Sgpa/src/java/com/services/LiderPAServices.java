/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Asignatura;
import com.entity.LiderPA;
import com.entity.Periodo;
import com.entity.Profesor;
import com.implDao.IAsignatura;
import com.implDao.ILiderPA;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Jcmm
 */
public class LiderPAServices extends ImplDao<LiderPA, Long> implements ILiderPA,Serializable{
    public LiderPA obtenerLiderPAXProfesor(Profesor p,Periodo pe){
        EntityManager em =ImplDao.getEntityManagger();
        LiderPA lid=new LiderPA();
        em.getTransaction().begin();        
        try{
        String q="select lp from LiderPA lp where lp.periodo.id = ?1 and lp.profesor.id= ?2";        
//        System.out.println(" Consulta: "+q);
        Query qu=em.createQuery(q)
                .setParameter(1, pe.getId())
                .setParameter(2, p.getId());
        lid=(LiderPA)qu.getSingleResult();
//        System.out.println(" Usuario: "+usu.getTipo());
        }catch(javax.persistence.NoResultException ner){
            
        }
        catch(Exception ex){
            ex.printStackTrace();
            //FacesUtil.addErrorMessage("Error Inicio Session",ex.getMessage() );
        }
        finally{
            em.close();        
        }
        
        return  lid;
    }
    
}
