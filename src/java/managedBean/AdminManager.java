/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Administrateur;
import beans.Enseignant;
import beans.Utilisateur;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author hedi
 */
@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class AdminManager {

    private Enseignant enseignant;
    private Utilisateur utilisateur;
    private Administrateur administrateur;


	public AdminManager() {
        this.administrateur=new Administrateur();
        this.enseignant=new Enseignant();
        this.utilisateur=new Utilisateur();
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }
    
    
    public void ajoutEnseignant(){
      
        
		SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        session.save(utilisateur);
        session.save(enseignant);
        session.getTransaction().commit();
       
    }
    
    public void ajoutAdmin(){
      
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        session.save(utilisateur);
        session.save(administrateur);
        session.getTransaction().commit();
       
    }
    
    public void SupprimerAdmin(int id){
      
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        administrateur=(Administrateur) session.load(Administrateur.class, id);
        session.delete(administrateur);
        session.getTransaction().commit();
       
    }
    
    public void SupprimerEnseignant(int id){
    
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        enseignant=(Enseignant) session.load(Enseignant.class, id);
        utilisateur=(Utilisateur)session.load(Utilisateur.class, id);
        session.delete(utilisateur);
        session.delete(enseignant);
        session.getTransaction().commit();
       
    }
    
    public void updateEnseignant(){
         SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        session.update(enseignant);
        session.update(utilisateur);
        session.getTransaction().commit();
    }
    
    public Enseignant getEnseignant(int id){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        enseignant=(Enseignant)session.load(Enseignant.class, id);
        session.getTransaction().commit();
        return enseignant;
    }
    
    public Utilisateur getUtilisateur(int id){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        utilisateur=(Utilisateur)session.load(Utilisateur.class, id);
        session.getTransaction().commit();
        return utilisateur;
    }
    
    public Utilisateur getAdmin(int id){ // didn't get it !! (amine)
       return utilisateur=getUtilisateur(id);
    }
    
    public static boolean isUtilisateur(int id , String pwd){
    	SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();

        session.beginTransaction();

        Query q =  session.createQuery("FROM Utilisateur WHERE idutilisateur = :id "
        		+ "AND password = :pwd");

        q.setParameter("id", id);
        q.setParameter("pwd", pwd);
      
        System.out.println("result : "+q.uniqueResult());
        
        
        return false;
    	
    	
    }
    
    
    
}
