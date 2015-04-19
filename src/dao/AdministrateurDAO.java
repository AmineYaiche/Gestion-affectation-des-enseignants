package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.AnneeUniversitaire;
import beans.Enseignant;
import beans.Matiere;
import beans.Niveau;

public class AdministrateurDAO {
	DB bd = new DB();
	public boolean ajouterEnseignant(Enseignant e){
		
       String query = "insert into utilisateur values("+e.getIdUtilisateur()+"," +e.getNom()+","+e.getPrénom()+")";
			 if ( bd.updateQuery(query) <= 0){
			    return false ;
			} 
			 query = "insert into enseignant values("+e.getIdUtilisateur()+"," +e.getGrade()+","+e.getTypeContrat()+","+e.getDateEmbauche()+","+e.getNbhe()+")";
			 if ( bd.updateQuery(query) <= 0){
			    return false ;
			} 
			
		return true;
	}
	
	public boolean supprimerEnseignant(int idUtilisateur){
		String query = " delete from utilisateur where idutilisateur = "+idUtilisateur ;
		if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
	    query = " delete from enseignant where idutilisateur = "+idUtilisateur ;
		if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
	}
	
	public int modifierEnseignant(Enseignant e){
		
		String query = "select * from utilisateur where idutilisateur ="+e.getIdUtilisateur() ;
		ResultSet r = bd.executeQuery(query) ;
		int test ;
		
		if ( r != null){
		    test =  1 ;
		} else {
			test = -1 ;
		}
		
		// try to update if ! null
		
		try {
			
			r.first() ;

			if( !e.getNom().equals("") ){
				r.updateString("nom", e.getNom());
			}
			
			if( !e.getPrénom().equals("") ){
				r.updateString("prenom", e.getPrénom());
			}
			
			
	// update the selected row		
			r.updateRow(); 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			test = -1 ;
		}
		
		 query = "select * from enseignant where idutilisateur ="+e.getIdUtilisateur() ;
		 r = bd.executeQuery(query) ;
		 if ( r != null){
			    test =  1 ;
			} else {
				test = -1 ;
			}
			
			// try to update if ! null
			
			try {
				
				r.first() ;

				if( !e.getGrade().equals("") ){
					r.updateString("grade", e.getGrade());
				}
				
				if( !e.getTypeContrat().equals("") ){
					r.updateString("typecontrat", e.getTypeContrat());
				}
				
				if( !e.getDateEmbauche().equals("") ){
					r.updateDate("dateembauche", e.getDateEmbauche());
				}
				if( e.getNbhe()!=0 ){
					r.updateInt("nbhe", e.getNbhe());
				}
				
		// update the selected row		
				r.updateRow(); 
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				test = -1 ;
			}
		
		return test ;
	}
	
	
	
	public boolean ajouterMatiere(Matiere m){
		String query = "insert into matiere values("+m.getCodem()+"," +m.getLibelle()+")";
		 if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
	}
	
	public boolean supprimerMatiere(int codem){
		String query = " delete from matiere where codem = "+codem;
		if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
	}
	
	public int modifierMatiere(Matiere m){
		String query = "select * from matiere where codem ="+m.getCodem() ;
		ResultSet r = bd.executeQuery(query) ;
		int test ;
		
		if ( r != null){
		    test =  1 ;
		} else {
			test = -1 ;
		}
		
		// try to update if ! null
		
		try {
			
			r.first() ;

			if( !m.getLibelle().equals("") ){
				r.updateString("libelle", m.getLibelle());
			}
			
			
			
			
	// update the selected row		
			r.updateRow(); 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			test = -1 ;
		}
		return test;
	}
	
	public boolean ajouterNiveau(Niveau n){
		String query = "insert into niveau values("+n.getNumNiv()+","+n.getSection()+")";
		 if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
		
	}
	public int midifierNiveau(Niveau n){
		String query = "select * from niveau where numniv ="+n.getNumNiv() ;
		ResultSet r = bd.executeQuery(query) ;
		int test ;
		
		if ( r != null){
		    test =  1 ;
		} else {
			test = -1 ;
		}
		
		// try to update if ! null
		
		try {
			
			r.first() ;

			if( !n.getSection().equals("") ){
				r.updateString("section", n.getSection());
			}
			if( n.getNumNiv()!=0){
				r.updateInt("numNiv", n.getNumNiv());
			}
			
			
			
	// update the selected row		
			r.updateRow(); 
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			test = -1 ;
		}
		return test;
		
	}
	
	public boolean supprimerNiveau(int numNiv){
		String query = " delete from niveau where numniv = "+numNiv;
		if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
		
	}
	
	public boolean ajouterAU(AnneeUniversitaire a){
		String query = "insert into anneeuniversitaire values("+a.getAnneeUniversitaire()+")";
		 if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
	}
	
	public boolean supprimerAU(String a){
		String query = " delete from anneeuniversitaire where anneeuniversitaire = "+a;
		if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		return true;
	}
	
	public boolean validerDemande(int numD){
	
		String query = " UPDATE demande SET etat='accepte' WHERE etat = "+numD;
		if ( bd.updateQuery(query) <= 0){
		    return false ;
		} 
		
		return true;
	}
}
