/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author fatima
 */
@Entity
public class TechnologieSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     private String niveau;
    private int nbrAnneExper;
    @ManyToOne
    private Freelance freelance;
    @ManyToOne
    private Technologie technologie;

    public TechnologieSkill() {
    }

    public TechnologieSkill(Long id) {
        this.id = id;
    }

    public TechnologieSkill(Long id, String niveau, int nbrAnneExper) {
        this.id = id;
        this.niveau = niveau;
        this.nbrAnneExper = nbrAnneExper;
    }

    
    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getNbrAnneExper() {
        return nbrAnneExper;
    }

    public void setNbrAnneExper(int nbrAnneExper) {
        this.nbrAnneExper = nbrAnneExper;
    }

    public Freelance getFreelance() {
        return freelance;
    }

    public void setFreelance(Freelance freelance) {
        this.freelance = freelance;
    }

    public Technologie getTechnologie() {
        return technologie;
    }

    public void setTechnologie(Technologie technologie) {
        this.technologie = technologie;
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
        if (!(object instanceof TechnologieSkill)) {
            return false;
        }
        TechnologieSkill other = (TechnologieSkill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TechnologieSkill{" + "id=" + id + ", niveau=" + niveau + ", nbrAnneExper=" + nbrAnneExper + '}';
    }

   
    
}
