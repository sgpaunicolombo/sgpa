/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.ImplDao;
import com.entity.Estudiante;
import com.entity.Matricula;
import com.entity.Periodo;
import com.implDao.IMatricula;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 *
 * @author Jcmm
 */
public class MatriculaServices extends ImplDao<Matricula, Long> implements IMatricula,Serializable{
    public Matricula obtenerMatriculaXPeriodo(Periodo p, Estudiante e){
        EntityManager em =ImplDao.getEntityManagger();
        Matricula mat=new Matricula();
        em.getTransaction().begin();        
        try{
        String q="select m from Matricula m where m.periodo.id = ?1 and m.estudiante.id= ?2";        
//        System.out.println(" Consulta: "+q);
        Query qu=em.createQuery(q)
                .setParameter(1, p.getId())
                .setParameter(2, e.getId());
        mat=(Matricula)qu.getSingleResult();
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
        
        return  mat;
    }
}
