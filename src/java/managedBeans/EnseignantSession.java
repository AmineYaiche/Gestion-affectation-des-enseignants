package managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@SessionScoped
public class EnseignantSession implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	public String getIdUtilisateur() {
		return login;
	}
	public void setIdUtilisateur(String login) {
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
