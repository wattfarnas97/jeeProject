package gestion.compte.metier;

import gestion.compte.entities.Compte;
import gestion.compte.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanque {
    public Compte consulterCompte(String code);
    public void verser(String code,double montant);
    public void retirer(String code,double montant);
    public void virement(String codecpte1,String codeCpte2,double montant);
    public Page<Operation> listOperation(String code,int page,int size);
    public Page<Compte> listeCompte(String mc,int page, int size);
}
