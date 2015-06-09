package managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@SessionScoped
public class EnseignantSession implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idUtilisateur;
	private String password;
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getPassword() {	
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EnseignantSession(int idUtilisateur, String password) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.password = password;
	}
	public EnseignantSession() {
		super();
	}
	
	
	public String connect(){
		boolean b = EnseignantManager.isEnseignant(idUtilisateur,password);
		if(b) return "ESuccess";
                
                idUtilisateur = -1;
                password = null;
                return "EFailure";
	}
        
        
        public  String disconnect() {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index.xhtml?faces-redirect=true";
        }
}
