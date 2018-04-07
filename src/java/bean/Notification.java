/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author fatima
 */
@Entity
public class Notification implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     private String text;
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateNotification;
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateVue;
     @OneToMany(mappedBy = "notification")
    private List<NotificationCategorie> notificationCategories;

    public Notification() {
    }

    public Notification(Long id) {
        this.id = id;
    }

    public Notification(Long id, String text, Date dateNotification, Date dateVue) {
        this.id = id;
        this.text = text;
        this.dateNotification = dateNotification;
        this.dateVue = dateVue;
    }

     
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    public Date getDateVue() {
        return dateVue;
    }

    public void setDateVue(Date dateVue) {
        this.dateVue = dateVue;
    }

    public List<NotificationCategorie> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(List<NotificationCategorie> notificationCategories) {
        this.notificationCategories = notificationCategories;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", text=" + text + ", dateNotification=" + dateNotification + ", dateVue=" + dateVue + '}';
    }

    
    
}
