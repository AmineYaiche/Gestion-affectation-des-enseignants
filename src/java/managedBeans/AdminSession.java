package managedBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class AdminSession implements  Serializable{
	
	private Long idUtilisateur;
	private String password;
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminSession(Long idUtilisateur, String password) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.password = password;
	}
	public AdminSession() {
		super();
	}

}
