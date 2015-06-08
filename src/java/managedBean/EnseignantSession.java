package managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



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
		
		boolean b = AdminManager.isUtilisateur(idUtilisateur,password);
		System.out.println("b : "+b);
		return (b)?"nouvelles_demandes":"encours";
		
	}

}
