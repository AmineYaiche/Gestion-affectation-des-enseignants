/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.enseignant;

import beans.LigneDemande;
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
    private LigneDemande[] ligneDemande;
    private List<Matiere>[] matieres;
    private String section1;
    private String section2;
    private String section3;
    
    SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
    private Session session=sessionFact.openSession();
    
     public NouvelleDemande() {
         matieres = new List[3];
         ligneDemande = new LigneDemande[3];
         for(int i = 0 ; i < 3; i ++){
             matieres[i] = new ArrayList<Matiere>();
             ligneDemande[i] = new LigneDemande();
         }
    }
    

    public void setLigneDemande(LigneDemande ligneDemande , int i) {
        this.ligneDemande[i] = ligneDemande;
    }

    public void setMatieres(List<Matiere> matieres, int i) {
        this.matieres[i] = matieres;
    }

    public List<Matiere> getMatieres(int i) {
        return matieres[i];
    }

    public LigneDemande getLigneDemande(int i) {
        return ligneDemande[i];
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
}
