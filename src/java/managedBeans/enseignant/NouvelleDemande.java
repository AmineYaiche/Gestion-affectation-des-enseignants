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
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.weld.util.collections.ArraySet;

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
        session.close();
        
    }
    
    public String save(){
        SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
        Session session=sessionFact.openSession();
        session.beginTransaction();

        boolean result = true;
        Demande d = new Demande();
        LigneDemande ligne = new LigneDemande();
        Set<LigneDemande> s = new ArraySet<LigneDemande>();
        
        if(section1 != null){
            LigneDemandeId id = new LigneDemandeId();
            id.setSection(section1);
            id.setLibelle(matiere1);
            
            ligne.setId(id);
            ligne.setEtat("ec");
            if(nature1.equals("Cours")) ligne.setNbc(nbrh1);
            else if (nature1.equals("TD")) ligne.setNbtd(nbrh1);
            else if(nature1.equals("TP")) ligne.setNbtp(nbrh1);
            s.add(ligne);
            
        }
        
        
        d.setLigneDemandes(s);
        Enseignant e = new Enseignant();e.setIdutilisateur(1);
        d.setEnseignant(e);
        d.setDated(null);
        session.save(d);
        session.save(ligne);

        session.getTransaction().commit();
        session.close();
        
        return "nouvelles_demandes";
    }
    
    
    
}
