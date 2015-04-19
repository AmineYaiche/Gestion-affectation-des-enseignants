package beans;

import java.sql.Date;


public class Demande {
	
	
	private int idUtilisateur;
	private int numDemande;
	private Date dateDemande;
	private String etat; //prend les valeurs accepte ou refuse
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public int getNumDemande() {
		return numDemande;
	}
	public void setNumDemande(int numDemande) {
		this.numDemande = numDemande;
	}
	public Date getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}
	
	

}
