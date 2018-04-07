/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Freelance;
import bean.Pays;
import controler.util.SearchUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class FreelanceFacade extends AbstractFacade<Freelance> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FreelanceFacade() {
        super(Freelance.class);
    }
    
      public List<Freelance> search(Pays pays,  Double max, Double min, String name) {
        String requette = "SELECT f FROM Freelance f where 1=1";
       
        requette += SearchUtil.addConstraintMinMax("f", "tarif", max, min);
        if (pays != null) {
            requette += SearchUtil.addConstraint("f", "pays.nom", "=", pays.getNom());
        }
        requette += SearchUtil.addConstraint("f", "nom", "=", name);
       

        return em.createQuery(requette).getResultList();
    }
}
