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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author fatima
 */
@Entity
public class Freelance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private double tel;
    private double tarif;
    @ManyToOne
    private Devise devise;
    @ManyToOne
    private Diplome diplome;
    @ManyToOne
    private Pays pays;
    @ManyToOne
    private Admin admin;
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "freelance")
    private List<NotificationCategorie> notificationCategories;

    @OneToMany(mappedBy = "freelance")
    private List<TechnologieSkill> technologieSkills;

    @OneToMany(mappedBy = "freelance")
    private List<Review> reviews;

    @OneToMany(mappedBy = "freelance")
    private List<LangueSkill> langueSkills;

    @OneToMany(mappedBy = "freelance")
    private List<Commentaire> commentaires;
    @OneToMany(mappedBy = "freelance")
    private List<Paiement> paiements;

    public Freelance() {
    }

    public Freelance(Long id) {
        this.id = id;
    }

    public Freelance(Long id, String nom, String prenom, double tel, double tarif) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.tarif = tarif;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getTel() {
        return tel;
    }

    public void setTel(double tel) {
        this.tel = tel;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NotificationCategorie> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(List<NotificationCategorie> notificationCategories) {
        this.notificationCategories = notificationCategories;
    }

    public List<TechnologieSkill> getTechnologieSkills() {
        return technologieSkills;
    }

    public void setTechnologieSkills(List<TechnologieSkill> technologieSkills) {
        this.technologieSkills = technologieSkills;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<LangueSkill> getLangueSkills() {
        return langueSkills;
    }

    public void setLangueSkills(List<LangueSkill> langueSkills) {
        this.langueSkills = langueSkills;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
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
        if (!(object instanceof Freelance)) {
            return false;
        }
        Freelance other = (Freelance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Freelance{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", tarif=" + tarif + '}';
    }

    

}
