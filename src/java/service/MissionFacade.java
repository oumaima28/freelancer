/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Categorie;
import bean.Langue;
import bean.Mission;
import bean.Recruteur;
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
public class MissionFacade extends AbstractFacade<Mission> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MissionFacade() {
        super(Mission.class);
    }
     public List<Mission> search(Recruteur recruteur,  Double max, Double min, Langue langue, Categorie categorie, String etat) {
        String requette = "SELECT m FROM Mission m where 1=1";
       
        requette += SearchUtil.addConstraintMinMax("m", "maxBudget", max, min);
         requette += SearchUtil.addConstraint("m", "avancement", "=", etat);
        if (recruteur != null) {
            requette += SearchUtil.addConstraint("m", "recruteur.nom", "=", recruteur.getNom());
        }
        if (langue != null) {
            requette += SearchUtil.addConstraint("m", "langue.nom", "=", langue.getNom());
        }
        if (categorie != null) {
            requette += SearchUtil.addConstraint("m", "categorie.nom", "=", categorie.getNom());
        }

        return em.createQuery(requette).getResultList();
    }
}
