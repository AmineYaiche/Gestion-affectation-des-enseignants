/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Administrateur;
import beans.Anneeuniversitaire;
import beans.Demande;
import beans.Enseignant;
import beans.LigneDemande;
import beans.LigneDemandeId;
import beans.Matiere;
import beans.Niveau;
import beans.Prog;
import beans.ProgId;
import beans.Utilisateur;
import java.util.List;
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
@ManagedBean
@RequestScoped

public class AdminManager {
    private Matiere matiere;
    private Prog programme;
    private Enseignant enseignant;
    private Utilisateur utilisateur;
    private Administrateur administrateur;
    private Demande demande;
    private LigneDemande ligneDemande;
    SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
    private Session session=sessionFact.openSession();
    private Niveau niveau;
    private Anneeuniversitaire annee;

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Prog getProgramme() {
        return programme;
    }

    public void setProgramme(Prog programme) {
        this.programme = programme;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Anneeuniversitaire getAnnee() {
        return annee;
    }

    public void setAnnee(Anneeuniversitaire annee) {
        this.annee = annee;
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
    
    public AdminManager() {
        this.administrateur=new Administrateur();
        this.enseignant=new Enseignant();
        this.utilisateur=new Utilisateur();
        this.programme=new Prog();
        this.matiere=new Matiere();
        this.annee=new Anneeuniversitaire();
        this.niveau=new Niveau();
        this.demande=new Demande();
        this.ligneDemande=new LigneDemande();
        
    }
 //=================================================================================
    //Gestion des demandes 
      public void confirmerDemande(LigneDemandeId num,String etat){
         session.beginTransaction();
          ligneDemande=(LigneDemande)session.load(LigneDemande.class, num);
         ligneDemande.setEtat(etat);
        float i=ligneDemande.getDemande().getEnseignant().getNbhe();
        int cours=ligneDemande.getNbc();
        int td =ligneDemande.getNbtd();
        int tp= ligneDemande.getNbtp();
        i=(float) ( (i+cours*1.83)+tp*0.67+td);
        
        ligneDemande.getDemande().getEnseignant().setNbhe(i);
        session.update(ligneDemande.getDemande().getEnseignant());
         session.update(ligneDemande);
         session.getTransaction().commit();
      }
    //Gestion Année Universitaire
     public void ajoutAnnee(){
         session.beginTransaction();
        session.save(annee);
        session.getTransaction().commit(); 
    }
     
     public void supprimerAnnee(String a){
        session.beginTransaction();
        annee=(Anneeuniversitaire) session.load(Anneeuniversitaire.class,a);
        session.delete(annee);
        session.getTransaction().commit();
        
    }
     
    
     
  //Gestion Section
    public void ajoutNiveau(){
         session.beginTransaction();
        session.save(niveau);
        session.getTransaction().commit(); 
    }
    public void supprimerNiveau(String niv){
         session.beginTransaction();
        niveau=(Niveau) session.load(Niveau.class, niv);
        session.delete(niveau);
        session.getTransaction().commit();
        
    }
 //Gestion des Matieres
    public void ajoutMatiere(){
       session.beginTransaction();
        session.save(matiere);
        session.getTransaction().commit(); 
    }
    
    public void supprimerMatiere(int codem){
          session.beginTransaction();
        matiere=(Matiere) session.load(Matiere.class, codem);
        session.delete(matiere);
        session.getTransaction().commit();
        
    }
    public Matiere getMatiere(int codem){
          session.beginTransaction();
        matiere=(Matiere) session.load(Matiere.class, codem);
        return matiere;
        
    }
    public void ajoutProgramme(){
        session.beginTransaction();
        session.save(programme);
        session.getTransaction().commit(); 
    }
    
    public void supprimerProgramme(ProgId progId){
        session.beginTransaction();
        programme=(Prog) session.load(Matiere.class, progId);
        session.delete(programme);
        session.getTransaction().commit();
    }
    
  //Gestion des Utilisateur
    
    public void ajoutEnseignant(){
      
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        System.out.println("3asslema");
        session.beginTransaction();
        session.save(utilisateur);
        enseignant.setUtilisateur(utilisateur);
        session.save(enseignant);
        session.getTransaction().commit();
       
    }
    
    public void ajoutAdmin(){
      
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        session.save(utilisateur);
        administrateur.setUtilisateur(utilisateur);
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
       
        utilisateur=(Utilisateur)session.load(Utilisateur.class, id);
        session.delete(enseignant);
        session.delete(utilisateur);
        
        session.getTransaction().commit();
       
    }
    
    public void upDateEnseignant(){
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
    
    public Utilisateur getAdmin(int id){
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
      
        List<Utilisateur> l = q.list();
        

        
        return l.size()> 0;
    }
    
    

       
}
