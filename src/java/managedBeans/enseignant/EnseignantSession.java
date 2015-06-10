package managedBeans.enseignant;

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
public class EnseignantSession implements Serializable{
	private static final long serialVersionUID = 1L;
	
        private int id;
	private String login;
	private String password;
	public String getLogin() {
		return login;
	}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {	
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EnseignantSession(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	public EnseignantSession() {
		super();
	}
	
	public String connect(){
            SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
            Session session=sessionFact.openSession();
            
            Utilisateur u = (Utilisateur)session.createQuery("FROM Utilisateur WHERE login = :login").setParameter("login", login).uniqueResult();
            if(u == null) return "EFailure";
            id = u.getIdutilisateur();
            
            boolean b = EnseignantManager.isUtilisateur(login,password);

            if(b) return "ESuccess";

            login = null;
            password = null;
            return "EFailure";
	}
        
        public  String disconnect() {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index.xhtml?faces-redirect=true";
        }
}
