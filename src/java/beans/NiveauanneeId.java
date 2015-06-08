package beans;
// Generated 7 juin 2015 23:46:30 by Hibernate Tools 4.3.1



/**
 * NiveauanneeId generated by hbm2java
 */
public class NiveauanneeId  implements java.io.Serializable {


     private String section;
     private String anneeuniversitaire;

    public NiveauanneeId() {
    }

    public NiveauanneeId(String section, String anneeuniversitaire) {
       this.section = section;
       this.anneeuniversitaire = anneeuniversitaire;
    }
   
    public String getSection() {
        return this.section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }
    public String getAnneeuniversitaire() {
        return this.anneeuniversitaire;
    }
    
    public void setAnneeuniversitaire(String anneeuniversitaire) {
        this.anneeuniversitaire = anneeuniversitaire;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NiveauanneeId) ) return false;
		 NiveauanneeId castOther = ( NiveauanneeId ) other; 
         
		 return ( (this.getSection()==castOther.getSection()) || ( this.getSection()!=null && castOther.getSection()!=null && this.getSection().equals(castOther.getSection()) ) )
 && ( (this.getAnneeuniversitaire()==castOther.getAnneeuniversitaire()) || ( this.getAnneeuniversitaire()!=null && castOther.getAnneeuniversitaire()!=null && this.getAnneeuniversitaire().equals(castOther.getAnneeuniversitaire()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSection() == null ? 0 : this.getSection().hashCode() );
         result = 37 * result + ( getAnneeuniversitaire() == null ? 0 : this.getAnneeuniversitaire().hashCode() );
         return result;
   }   


}


