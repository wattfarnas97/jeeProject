package gestion.compte.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("R")
public class Retrait  extends  Operation{
    public Retrait() {
    }

    public Retrait(Long numero, Date dateOperation, double montant, Compte compte) {
        super(numero, dateOperation, montant, compte);
    }
}
