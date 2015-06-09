package managedBeans;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@SessionScoped
public class AdminSession implements  Serializable{
	
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
		boolean b = AdminManager.isAdmin(login,password);
                if(b) return "ASuccess";
                
                login = null;
                password = null;
                return "AFailure";	
        }
        
        public  String disconnect() {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/index.xhtml?faces-redirect=true";
        }

}
