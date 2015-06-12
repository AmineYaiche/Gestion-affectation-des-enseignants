        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.enseignant;

import beans.Demande;
import beans.Enseignant;
import beans.LigneDemande;
import beans.LigneDemandeId;
import beans.Matiere;
import beans.Niveau;
import beans.Prog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author amine
 */
@ManagedBean
@ViewScoped
public class NouvelleDemande implements Serializable{
    private List<Matiere>[] matieres;
    private String section1;
    private String section2;
    private String section3;
    
    private String matiere1;
    private String matiere2;
    private String matiere3;
    
    private String nature1;
    private String nature2;
    private String nature3;
    
    private int nbrh1;
    private int nbrh2;
    private int nbrh3;
    
    private int idUtilisateur;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    
    
    SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
    private Session session;

    public String getMatiere1() {
        return matiere1;
    }

    public String getMatiere2() {
        return matiere2;
    }

    public String getMatiere3() {
        return matiere3;
    }

    public String getNature1() {
        return nature1;
    }

    public String getNature2() {
        return nature2;
    }

    public String getNature3() {
        return nature3;
    }

    public int getNbrh1() {
        return nbrh1;
    }

    public int getNbrh2() {
        return nbrh2;
    }

    public int getNbrh3() {
        return nbrh3;
    }

    public void setMatiere1(String matiere1) {
        this.matiere1 = matiere1;
    }

    public void setMatiere2(String matiere2) {
        this.matiere2 = matiere2;
    }

    public void setMatiere3(String matiere3) {
        this.matiere3 = matiere3;
    }

    public void setNature1(String nature1) {
        this.nature1 = nature1;
    }

    public void setNature2(String nature2) {
        this.nature2 = nature2;
    }

    public void setNature3(String nature3) {
        this.nature3 = nature3;
    }

    public void setNbrh1(int nbrh1) {
        this.nbrh1 = nbrh1;
    }

    public void setNbrh2(int nbrh2) {
        this.nbrh2 = nbrh2;
    }

    public void setNbrh3(int nbrh3) {
        this.nbrh3 = nbrh3;
    }
    
    
    
     public NouvelleDemande() {
         matieres = new List[3];
         for(int i = 0 ; i < 3; i ++){
             matieres[i] = new ArrayList<Matiere>();
         }
    }
    

    public void setMatieres(List<Matiere> matieres, int i) {
        this.matieres[i] = matieres;
    }

    public List<Matiere> getMatieres(int i) {
        return matieres[i];
    }


    public void setSection1(String section1) {
        this.section1 = section1;
    }

    public void setSection2(String section2) {
        this.section2 = section2;
    }

    public void setSection3(String section3) {
        this.section3 = section3;
    }

    public String getSection1() {
        return section1;
    }

    public String getSection2() {
        return section2;
    }

    public String getSection3() {
        return section3;
    }
    
    public List<Niveau> getAllSections(){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        
        Query q =  session.createQuery("FROM Niveau");
        List<Niveau> l=q.list();
        
        session.getTransaction().commit();
        session.close();
        
        return l;   
    }
    
    public void onSectionChange(int i){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession(); 
        session.beginTransaction();
        String section;
        
        section = (i == 0)?section1:(i==1)?section2:section3;
        
        matieres[i] = new ArrayList<Matiere>();
        if(section == null || section.equals("")) return;
        
        Query q = session.createQuery("FROM Prog WHERE section = :section ").setParameter("section", section);
        if(q != null){
            List<Prog> p  = q.list();
            if(p != null)
                for(Prog elem:p){
                    matieres[i].add(elem.getMatiere());
                }
        }
        session.getTransaction().commit();
        session.close();sessionFact.close();
        
    }
    
    public String save(){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();

        boolean result = true;
        Demande d = new Demande();
        LigneDemande ligne = null;
        LigneDemandeId lId = null;
        List<LigneDemande> lLigne = new ArrayList<LigneDemande>();
        
        Enseignant e = new Enseignant();e.setIdutilisateur(idUtilisateur);
        d.setEnseignant(e);
        d.setDated(null);
        int id = (Integer)session.save(d);
        
        if(section1 != null){
            ligne = new LigneDemande();
            lId = new LigneDemandeId();
            lId.setSection(section1);
            lId.setLibelle(matiere1);
            
            ligne.setId(lId);
            ligne.setEtat("ec");
            if(nature1.equals("Cours")) ligne.setNbc(nbrh1);
            else if (nature1.equals("TD")) ligne.setNbtd(nbrh1);
            else if(nature1.equals("TP")) ligne.setNbtp(nbrh1);
            lId.setNumd(id);
            lLigne.add(ligne);
        }
        
        
        if(section2 != null){
            ligne = new LigneDemande();
            lId = new LigneDemandeId();
            lId.setSection(section2);
            lId.setLibelle(matiere2);
            ligne.setId(lId);
            
            ligne.setEtat("ec");
            if(nature1.equals("Cours")) ligne.setNbc(nbrh2);
            else if (nature1.equals("TD")) ligne.setNbtd(nbrh2);
            else if(nature1.equals("TP")) ligne.setNbtp(nbrh2);
            lId.setNumd(id);
            lLigne.add(ligne);

        }
        
        
        
        if(section3 != null){
            ligne = new LigneDemande();
            lId = new LigneDemandeId();
            lId.setSection(section3);
            lId.setLibelle(matiere3);
            ligne.setId(lId);
            
            ligne.setEtat("ec");
            if(nature1.equals("Cours")) ligne.setNbc(nbrh3);
            else if (nature1.equals("TD")) ligne.setNbtd(nbrh3);
            else if(nature1.equals("TP")) ligne.setNbtp(nbrh3);
            lId.setNumd(id);
            lLigne.add(ligne);
        }
    
        
        for(LigneDemande elem:lLigne){
            if(isRealizable(elem)){
                session.save(elem);
            }
            else{
                session.getTransaction().commit();
                session.close();sessionFact.close();
                System.out.println("NOOOOOOOOOOOOOOOOOON");
                return "Failure";
            }
        }
        
        
        session.getTransaction().commit();
        session.close();sessionFact.close();
        
        return "Success";
    }
    
    public boolean isRealizable(LigneDemande ligne){
        return progNotFull(ligne) && userNotFull(ligne);
    }
    
    public boolean userNotFull(LigneDemande ligne){
        float nbhe = EnseignantManager.getNbhe(idUtilisateur);
        float totaleHeureLigne = 0;
        if(ligne.getNbc() != null) totaleHeureLigne += ligne.getNbc();
        if(ligne.getNbtd() != null) totaleHeureLigne += ligne.getNbtd();
        if(ligne.getNbtp() != null) totaleHeureLigne += ligne.getNbtp();
        
        return nbhe >= totaleHeureLigne;
    }
    
    public boolean progNotFull(LigneDemande ligne){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();
        double vhcTot, vhtdTot, vtpTot, nbtp = 0, nbtd = 0 , nbc = 0;
        
        Query q = session.createQuery("FROM Prog WHERE section = :s AND libelle = :l");
        q.setParameter("s", ligne.getId().getSection());
        q.setParameter("l", ligne.getId().getLibelle());
        Prog p = (Prog) q.uniqueResult();
        vhcTot = p.getNbhc();vhtdTot = p.getNbhtd() ; vtpTot = p.getNbhtp();
        
        q = session.createQuery("FROM LigneDemande WHERE section = :s AND libelle = :l");
        q.setParameter("s", ligne.getId().getSection());
        q.setParameter("l", ligne.getId().getLibelle());
        List<LigneDemande> l = q.list();
        for(LigneDemande elem:l){
            if(elem.getNbc() != null) nbc += elem.getNbc();
            if(elem.getNbtd()!= null) nbc += elem.getNbtd();
            if(elem.getNbtp()!= null) nbc += elem.getNbtp();
        }
        
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println(nbc + " " +nbtd +" "+nbtp);
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println(vhcTot+ " " +vhtdTot +" "+vtpTot);
        
        
        session.getTransaction().commit();
        session.close();sessionFact.close();     
        
        if(ligne.getNbc() != null && nbc + ligne.getNbc() > vhcTot) return false;
        if(ligne.getNbtd()!= null && nbtd + ligne.getNbtd()> vhtdTot) return false;
        if(ligne.getNbtp()!= null && nbtp + ligne.getNbtp()> vtpTot) return false;
        
        return true;
    }

    private void diminuerNbr(LigneDemande ligne) {
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();

        int nbr = 0;
        
        if(ligne.getNbc() != null) nbr += ligne.getNbc();
        if(ligne.getNbtd() != null) nbr += ligne.getNbtd();
        if(ligne.getNbtp() != null) nbr += ligne.getNbtp();
        
        Enseignant  e =  (Enseignant) session.get(Enseignant.class , idUtilisateur);
        
        e.setNbhe(e.getNbhe() - nbr);
        
        session.update(e);
        session.getTransaction().commit();
        session.close();
        sessionFact.close();
    }
    
    
}
