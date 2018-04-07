package controler;

import bean.Freelance;
import bean.Pays;
import bean.Recruteur;
import bean.User;
import controler.util.HashageUtil;
import controler.util.JsfUtil;
import controler.util.JsfUtil.PersistAction;
import controler.util.SessionUtil;
import java.io.IOException;
import service.UserFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private service.UserFacade ejbFacade;
    private List<User> items = null;
    private User selected;

    //for inscription
    private String nom;
    private String prenom;
    private double tel;
    private Pays pays;
    private String role;
    private Recruteur recruteur;
    private Freelance freelance;
    @EJB
    private service.RecruteurFacade recruteurFacade;
    @EJB
    private service.FreelanceFacade freelanceFacade;
    
    
    public UserController() {
    }

    public void sinscrire() throws IOException{
        String pass = ejbFacade.getGeneratePass();
        selected.setPasseword(HashageUtil.sha256(pass));
        selected.setMdpChanged(true);
        
        User clone = clone(selected);
        
        int res = ejbFacade.sendEmail( selected.getLogin() , nom , clone , pass);
        System.out.println("res ="+res);
        if ( res > 0 )
        {
        ejbFacade.create(selected);
        }
        if(role.equals("r")){
            recruteur.setNom(nom);
            recruteur.setPrenom(prenom);
            recruteur.setTel(tel);
            recruteur.setPays(pays);
            selected.setType(1); // si recruteur le type vaut 1
            recruteur.setUser(selected);
            recruteurFacade.create(recruteur);
        }
        if(role.equals("f")){
            freelance.setNom(nom);
            freelance.setPrenom(prenom);
            freelance.setTel(tel);
            freelance.setPays(pays);
            selected.setType(0);
            freelance.setUser(selected);
            freelanceFacade.create(freelance);
        }

       
        vider();
        SessionUtil.setAttribute("connected", selected);
    }
    
    public void vider(){
        nom="";
        prenom="";
        pays=null;
        selected=null;
        
    }
    
    public User clone(User user)
    {
        User clone = new User();
        clone.setBlocked(user.getBlocked());
        clone.setDevices(user.getDevices());
        clone.setLogin(user.getLogin());
        clone.setPasseword(user.getPasseword());
        clone.setType(user.getType());
        clone.setNbrCnx(user.getNbrCnx());
        return clone;
    }
    
    public User getSelected() {
        if(selected == null){
            selected = new User();
        }
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public Recruteur getRecruteur() {
        if(recruteur == null){
            recruteur = new Recruteur();
        }
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }

    public Freelance getFreelance() {
        if(freelance == null){
            freelance = new Freelance();
        }
        return freelance;
    }

    public void setFreelance(Freelance freelance) {
        this.freelance = freelance;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Pays getPays() {
        if(pays == null){
            pays = new Pays();
        }
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public User getUser(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getLogin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }

}
