/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Pays;
import bean.Recruteur;
import bean.User;
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
public class RecruteurFacade extends AbstractFacade<Recruteur> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecruteurFacade() {
        super(Recruteur.class);
    }
    public List<Recruteur> search(Pays pays,User user) {
        String requette = "SELECT r FROM Recruteur r where 1=1";
       
        if (pays != null) {
            requette += SearchUtil.addConstraint("r", "pays.nom", "=", pays.getNom());
        }
        if (user != null) {
            requette += SearchUtil.addConstraint("r", "user.login", "=", user.getLogin());
        }
        
        return em.createQuery(requette).getResultList();
    }
}
