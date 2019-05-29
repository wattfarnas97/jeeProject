package gestion.compte.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("V")
public class Versement  extends  Operation{
    public Versement(Long numero, Date dateOperation, double montant, Compte compte) {
        super(numero, dateOperation, montant, compte);
    }

    public Versement() {
    }
}
