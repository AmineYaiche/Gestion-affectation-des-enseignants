package managedBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class AdminSession implements  Serializable{
	
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
	public AdminSession(int idUtilisateur, String password) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.password = password;
	}
	public AdminSession() {
		super();
	}
        
        public String connect(){
		boolean b = AdminManager.isAdmin(idUtilisateur,password);
		return (b)?"ASuccess":"AFailure";
	}

}
