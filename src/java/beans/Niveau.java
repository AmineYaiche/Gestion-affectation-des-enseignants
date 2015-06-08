package beans;
// Generated 7 juin 2015 23:46:30 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Niveau generated by hbm2java
 */
public class Niveau  implements java.io.Serializable {


     private String section;
     private Set<Niveauannee> niveauannees = new HashSet<Niveauannee>(0);
     private Set<Prog> progs = new HashSet<Prog>(0);

    public Niveau() {
    }

	
    public Niveau(String section) {
        this.section = section;
    }
    public Niveau(String section, Set<Niveauannee> niveauannees, Set<Prog> progs) {
       this.section = section;
       this.niveauannees = niveauannees;
       this.progs = progs;
    }
   
    public String getSection() {
        return this.section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }
    public Set<Niveauannee> getNiveauannees() {
        return this.niveauannees;
    }
    
    public void setNiveauannees(Set<Niveauannee> niveauannees) {
        this.niveauannees = niveauannees;
    }
    public Set<Prog> getProgs() {
        return this.progs;
    }
    
    public void setProgs(Set<Prog> progs) {
        this.progs = progs;
    }




}

