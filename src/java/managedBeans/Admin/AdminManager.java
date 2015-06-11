/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.Admin;

import beans.Administrateur;
import beans.Anneeuniversitaire;
import beans.Demande;
import beans.Enseignant;
import beans.LigneDemande;
import beans.Matiere;
import beans.Niveau;
import beans.Prog;
import beans.ProgId;
import beans.Utilisateur;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import managedBeans.enseignant.EnseignantManager;
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
    private Session session;
    private Niveau niveau;
    private Anneeuniversitaire annee;

    
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
    
    
 //=================================================================================
    //Gestion des demandes 
      public void confirmerDemande(LigneDemande l,String etat){
        //  SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
           Session session=sessionFact.openSession();
         session.beginTransaction();
          
         l.setEtat(etat);
        if (etat.equals("c")){
        float i=l.getDemande().getEnseignant().getNbhe();
        int cours=l.getNbc();
        int td =l.getNbtd();
        int tp= l.getNbtp();
        i=(float) ( (i+cours*1.83)+tp*0.67+td);
        
        l.getDemande().getEnseignant().setNbhe(i);
        session.merge(l.getDemande().getEnseignant());
        //session.getTransaction().commit();
        }
        
         session.merge(l);
         
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
     
    public List<Niveau> allNiveau(){
        session=sessionFact.openSession();
        session.beginTransaction();
        Query q=session.createQuery("From Niveau");
        List<Niveau> l=(List<Niveau>)q.list();
        session.close();
        return l;
    }
    public void ajoutNiveau(){
        session=sessionFact.openSession();
         session.beginTransaction();
         
        session.save(niveau);
        session.getTransaction().commit(); 
        session.close();
    }
    public void supprimerNiveau(String niv){
         session.beginTransaction();
        niveau=(Niveau) session.load(Niveau.class, niv);
        session.delete(niveau);
        session.getTransaction().commit();
        session.close();
        
    }
 //Gestion des Matieres
    public void ajoutMatiere(){
       session=sessionFact.openSession();
       session.beginTransaction();
        session.save(matiere);
        session.getTransaction().commit(); 
        session.close();
    }
    
    public void supprimerMatiere(String libelle){
          session.beginTransaction();
        matiere=(Matiere) session.load(Matiere.class, libelle);
        session.delete(matiere);
        session.getTransaction().commit();
        session.close();
        
    }
    public Matiere getMatiere(String libelle){
          session=sessionFact.openSession();
          session.beginTransaction();
        matiere=(Matiere) session.load(Matiere.class, libelle);
        session.close();
        return matiere;
        
    }
    
    public List<Matiere> allMatiere(){
        session=sessionFact.openSession();
        session.beginTransaction();
        Query q=session.createQuery("From Matiere");
       List<Matiere> l=(List<Matiere>)q.list();
        session.close();
        return l;
    }
    public void ajoutProgramme(){
        session=sessionFact.openSession();
        session.beginTransaction();
        programme.getId().setLibelle(programme.getMatiere().getLibelle());
        programme.getId().setSection(programme.getNiveau().getSection());
        session.save(programme);
        session.getTransaction().commit(); 
        session.close();
    }
    
    public void supprimerProgramme(ProgId progId){
        session.beginTransaction();
        programme=(Prog) session.load(Matiere.class, progId);
        session.delete(programme);
        session.getTransaction().commit();
        session.close();
    }
    
  //Gestion des Utilisateur
    
    public void ajoutEnseignant(){
      
       
        Session session=sessionFact.openSession();
       
        session.beginTransaction();
        utilisateur.setIdutilisateur(6);
        utilisateur.setEnseignant(enseignant);
        session.save(utilisateur);
        enseignant.setUtilisateur(utilisateur);
       // session.save(enseignant);
        session.getTransaction().commit();
        session.close();
       
    }
    
    public void ajoutAdmin(){
      
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        session.save(utilisateur);
        administrateur.setUtilisateur(utilisateur);
        session.save(administrateur);
        session.getTransaction().commit();
        session.close();
       
    }
    
    public void SupprimerAdmin(int id){
      
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        administrateur=(Administrateur) session.load(Administrateur.class, id);
        session.delete(administrateur);
        session.getTransaction().commit();
        session.close();
       
    }
    
    public void SupprimerEnseignant(int id){
    
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
       
        utilisateur=(Utilisateur)session.load(Utilisateur.class, id);
        session.delete(enseignant);
        session.delete(utilisateur);
        
        session.getTransaction().commit();
        session.close();
       
    }
    
    public void upDateEnseignant(){
         SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        session.update(enseignant);
        session.update(utilisateur);
        session.getTransaction().commit();
        session.close();
    }
    
    public Enseignant getEnseignant(int id){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        enseignant=(Enseignant)session.load(Enseignant.class, id);
        session.getTransaction().commit();
        session.close();
        return enseignant;
        
    }
    
    public Utilisateur getUtilisateur(int id){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        utilisateur=(Utilisateur)session.load(Utilisateur.class, id);
        session.getTransaction().commit();
        session.close();
        return utilisateur;
    }
    
    public List<Utilisateur> getAllUtilisateur(){
     // SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
      Session session=sessionFact.openSession(); 
      session.beginTransaction();
      Query q=session.createQuery("From Utilisateur");
      List<Utilisateur> l=q.list();

      session.getTransaction().commit();
     
     // session.close();
      return l;
    }
    
    public Utilisateur getAdmin(int id){
       return utilisateur=getUtilisateur(id);
    }
    
     public List<Demande> demandeEncours(int id){
        // SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();

        Query q =  session.createQuery("FROM Demande WHERE idutilisateur = :id ");
        		

        q.setParameter("id", id);
        List<Demande> l = q.list();
        session.getTransaction().commit();
        session.close();
        return l;
    }
    
    
    public static boolean isAdmin(String login, String pwd){
        if(! EnseignantManager.isUtilisateur(login , pwd)) return false;
        int id = EnseignantManager.getIdByLogin(login);
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        
        Query q =  session.createQuery("FROM Administrateur WHERE idutilisateur = :id ");
        		
        List<Administrateur> l = q.setParameter("id", id).list();
        session.getTransaction().commit();
        session.close();
        return l.size() > 0 ;
        
    }
    
    //Gestion des programmes
    public List<Prog> allPrograms(){
        session=sessionFact.openSession();
        session.beginTransaction();
        Query q=session.createQuery("From Prog");
        List<Prog> l=(List<Prog>)q.list();
        session.close();
        return l;
    }
       
}
