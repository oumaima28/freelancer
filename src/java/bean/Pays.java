/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author fatima
 */
@Entity
public class Pays implements Serializable {

    @OneToMany(mappedBy = "pays")
    private List<Recruteur> recruteurs;

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
@OneToMany(mappedBy = "pays")
    private List<Freelance> freelances;

    @OneToMany(mappedBy = "pays")
    private List<Admin> admins;

    public List<Recruteur> getRecruteurs() {
        return recruteurs;
    }

    public Pays() {
    }

    public Pays(Long id) {
        this.id = id;
    }

    public Pays(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    
    public void setRecruteurs(List<Recruteur> recruteurs) {
        this.recruteurs = recruteurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Freelance> getFreelances() {
        return freelances;
    }

    public void setFreelances(List<Freelance> freelances) {
        this.freelances = freelances;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pays{" + "id=" + id + ", nom=" + nom + '}';
    }

   
    
}
