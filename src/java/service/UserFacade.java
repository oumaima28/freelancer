/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;
import controler.util.EmailUtil;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

     public String getGeneratePass() {
        String SALTCHARS = "abcdefjhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
     
     public int sendEmail(String to, String name, User user, String pass) {

        if (user == null) {
            return -1;
        } else {
            String message = "Hello ";
            String subject = "Welcome Mail to " + user.getLogin();
            message
                    += name + "<br/><br/><br/>"
                    + " Welcome to GotoDOC, we are happy to join you our web application. <br/>"
                    + "Your Username :" + user.getLogin()
                    + "<br/>"
                    + "Your Password :" + pass
                    + "<br/>"
                    + "This is a Random Password, You have to change it as soon as possible for security reasons"
                    + "<br/>"
                    + "Have a Good Day";
            try {
                /*String from, String password, String message, String to, String subject*/
                EmailUtil.sendMail(null, null, message, to, subject);
            } catch (MessagingException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
                return -2;
            }

            return 1;
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
