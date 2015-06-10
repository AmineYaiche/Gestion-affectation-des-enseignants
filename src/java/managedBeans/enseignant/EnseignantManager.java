/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.enseignant;

import beans.Demande;
import beans.Enseignant;
import beans.LigneDemande;
import beans.Prog;
import beans.Utilisateur;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@ManagedBean
@RequestScoped
public class EnseignantManager {
    private Enseignant enseignant;
    private Demande demande; 
    private LigneDemande  ligneDemande;
    
    
    SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
    private final Session session=sessionFact.openSession();
    public EnseignantManager() {
      
    }
    public void nouvelleDemande(){
        session.beginTransaction();
        demande.setEnseignant(enseignant);
        session.save(demande);
        session.getTransaction().commit();
    }
    
    public void nouvelleLigneDemande(){
        session.beginTransaction();
        ligneDemande.setDemande(demande);
        ligneDemande.setProg(null);
        session.save(ligneDemande);
        session.getTransaction().commit();
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public LigneDemande getLigneDemande() {
        return ligneDemande;
    }

    public void setLigneDemande(LigneDemande ligneDemande) {
        this.ligneDemande = ligneDemande;
    }
    
   
    
    public List<Prog> getMatieresFromSection(String section){
        session.beginTransaction();
        Query q =  session.createQuery("FROM Prog where section = :section").setParameter("section", section);
        List<Prog> l=q.list();
        session.getTransaction().commit();
        return l;
    }
    
    public static boolean isUtilisateur(String login , String pwd){
    	SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();

        session.beginTransaction();

        Query q =  session.createQuery("FROM Utilisateur WHERE login = :login "
        		+ "AND password = :pwd");

        q.setParameter("login", login);
        List<Utilisateur> l = q.setParameter("pwd", pwd).list();
        session.getTransaction().commit();
        return l.size() > 0 ;
    }
    
    public static boolean isEnsignant(String login, String pwd){
        if(! isUtilisateur(login , pwd)) return false;
        
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        
        Query q =  session.createQuery("FROM Enseignant WHERE login = :login ");
        		
        List<Utilisateur> l = q.setParameter("login", login).list();
        session.getTransaction().commit();
        return l.size() > 0 ;
        
        
    }
    
    public List<Demande> demandeEncours(){
        session.beginTransaction();

        Query q =  session.createQuery("FROM Demande WHERE idutilisateur = :id ");
        		

        q.setParameter("id", 1);
        List<Demande> l = q.list();
        session.getTransaction().commit();
        return l;
    }
    
    public List<LigneDemande> getLigneDemandeEncours(int numd){
        session.beginTransaction();

        Query q =  session.createQuery("FROM LigneDemande WHERE id.numd = :id and etat='ec' ");

        q.setParameter("id", numd);
        
        List<LigneDemande> l = (List<LigneDemande>)q.list();
        session.getTransaction().commit();
        return l;
    }
    
    public List<LigneDemande> getLigneDemandeAcceptees(int numd){
        session.beginTransaction();


        Query q =  session.createQuery("FROM LigneDemande WHERE id.numd = :id and etat='c' ");


        q.setParameter("id", numd);
        
        List<LigneDemande> l = q.list();
        session.getTransaction().commit();
        return l;
    } 
    
    public List allLigneDemande(){
        session.beginTransaction();

        Query q =  session.createQuery("select LigneDemande FROM Demande inner join LigneDemande WHERE idutilisateur = :id and LigneDemande.etat='encours'");
        		

        q.setParameter("id", 1);
        List<LigneDemande> l = q.list();
      
        session.getTransaction().commit();
        return l;
    }
    
    public void supprimerLigneDemande(LigneDemande l){
        session.beginTransaction();
      
        session.delete(l);
        session.getTransaction().commit();
    }
    
    
}
