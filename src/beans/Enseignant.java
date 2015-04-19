package beans;

import java.sql.Date;

public class Enseignant extends Utilisateur{
	private String grade;
	private String typeContrat;
	private Date dateEmbauche;
	private int nbhe;
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTypeContrat() {
		return typeContrat;
	}
	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}
	public Date getDateEmbauche() {
		return dateEmbauche;
	}
	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	public int getNbhe() {
		return nbhe;
	}
	public void setNbhe(int nbhe) {
		this.nbhe = nbhe;
	}

}
