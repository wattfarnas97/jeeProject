package gestion.compte.metier;

import gestion.compte.dao.CompteRepository;
import gestion.compte.dao.OperationRepository;
import gestion.compte.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BanqueMetierImpl implements IBanque {
    @Autowired
    private CompteRepository compteDao;
    @Autowired
    private OperationRepository operationDao;

    @Override
    public Compte consulterCompte(String code) {
        Optional<Compte> cp=compteDao.findById(code);
        if(cp.isPresent())
        {
            return cp.get();

        }
        throw  new RuntimeException("compte introuvable");

    }

    @Override
    public void verser(String code, double montant) {

        Compte cp=consulterCompte(code);
        Versement v= new Versement();
        v.setMontant(montant);
        v.setDateOperation(new Date());
        v.setCompte(cp);
        operationDao.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteDao.save(cp);
    }

    @Override
    public void retirer(String code, double montant) {
        Compte cp=consulterCompte(code);
        double facilite=0;
        if(cp instanceof CompteCourant)
            facilite=((CompteCourant) cp).getDecouvert();

            if(facilite+cp.getSolde()<montant)
            {
                throw new RuntimeException("Solde insufisant");
            }
        Retrait r= new Retrait();
        r.setMontant(montant);
        r.setDateOperation(new Date());
        r.setCompte(cp);
        operationDao.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteDao.save(cp);

    }

    @Override
    public void virement(String codecpte1, String codeCpte2, double montant) {
        if(codecpte1.equals(codeCpte2))
            throw  new RuntimeException("operation impossible:virement sur le meme compte");
        retirer(codecpte1,montant);
        verser(codeCpte2,montant);
    }

    @Override
    public Page<Operation> listOperation(String code, int page, int size) {
        final Page<Operation> operations = operationDao.listOperation(code, new PageRequest(page, size));
        return operations;
    }

    @Override
    public Page<Compte> listeCompte(String mc,int page, int size) {
        return compteDao.findByCodeContains(mc,new PageRequest(page,size));
    }
}
