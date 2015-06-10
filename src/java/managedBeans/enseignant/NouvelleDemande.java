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
    private String section;
    private String nature;
    private int nbrHeure;
    private LigneDemande ligneDemande;
    private List<Matiere> matieres;

    
    SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
    private Session session=sessionFact.openSession();
    
     public NouvelleDemande() {
         matieres = new ArrayList<Matiere>();
    }
    
    public void setSection(String section) {
        this.section = section;
    }


    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setNbrHeure(int nbrHeure) {
        this.nbrHeure = nbrHeure;
    }

    public void setLigneDemande(LigneDemande ligneDemande) {
        this.ligneDemande = ligneDemande;
    }

    public String getSection() {
        return section;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public String getNature() {
        return nature;
    }

    public int getNbrHeure() {
        return nbrHeure;
    }

    public LigneDemande getLigneDemande() {
        return ligneDemande;
    }
    
    public List<Niveau> getAllSections(){
        session.beginTransaction();
        Query q =  session.createQuery("FROM Niveau");
        List<Niveau> l=q.list();
        session.getTransaction().commit();
        
        
        return l;   
    }
    
    public void onSectionChange(){
        session.beginTransaction();
        matieres = new ArrayList<Matiere>();
        if(section == null || section.equals("")) return;
        
        Query q = session.createQuery("FROM Prog WHERE section = :section ").setParameter("section", section);
        if(q != null){
            List<Prog> p  = q.list();
            if(p != null)
                for(Prog elem:p)
                    matieres.add(elem.getMatiere());
            session.getTransaction().commit();
        
        
        }
    }
}
