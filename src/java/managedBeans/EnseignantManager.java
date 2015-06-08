/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Demande;
import beans.Enseignant;
import beans.LigneDemande;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@ManagedBean
@RequestScoped
public class EnseignantManager {
    private Enseignant enseignant;
    private Demande demande;
    private LigneDemande ligneDemande;
    SessionFactory sessionFact=new Configuration().configure().buildSessionFactory();
    private Session session=sessionFact.openSession();
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
    
}
