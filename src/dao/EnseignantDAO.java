package dao;

import beans.Demande;
import beans.LigneDemande;

public class EnseignantDAO {
	DB bd = new DB();
	public boolean enregistrerDemande(Demande d,LigneDemande l){
		 String query = "insert into demande values("+d.getNumDemande()+"," +d.getIdUtilisateur()+","+d.getDateDemande()+","+d.getEtat()+")";
		 if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		 query = "insert into ligne_Demande values("+l.getNumDemande()+","+l.getCodem()+","+l.getNature()+")";
		 if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		
	return true;
	
	}

}
