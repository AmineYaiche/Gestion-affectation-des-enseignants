package managedBeans.Admin;


import beans.Utilisateur;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



@ManagedBean
@SessionScoped
public class AdminSession implements  Serializable{
	
        private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	private String login;
	private String password;
	public String getLogin() {
		return login;
	}
	public void setLogin(String Login) {
		this.login = Login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminSession(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	public AdminSession() {
		super();
	}
        
        public String connect(){
            SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
            Session session=sessionFact.openSession();
            
            Utilisateur u = (Utilisateur)session.createQuery("FROM Utilisateur WHERE login = :login").setParameter("login", login).uniqueResult();
            if(u == null) return "AFailure";
            id = u.getIdutilisateur();
            
            boolean b = AdminManager.isAdmin(login,password);
            if(b) return "ASuccess";
            
            id = -1;
            login = null;
            password = null;
            return "AFailure";
	}
        
        public  String disconnect() {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index.xhtml?faces-redirect=true";
        }

}
