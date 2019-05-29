package gestion.compte;

import gestion.compte.dao.ClientRepository;
import gestion.compte.dao.CompteRepository;
import gestion.compte.dao.OperationRepository;
import gestion.compte.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CompteApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientDao;
    @Autowired
    private CompteRepository compteDao;
    @Autowired
    private OperationRepository operatioDao;


    public static void main(String[] args)
    {
        SpringApplication.run(CompteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

     /*   Client c=new Client();
        c.setEmail("wattfarouk@gmail.com");
        c.setNom("Ouattara");
        c.setPrenom("Farouk");
        clientDao.save(c);
        CompteCourant cp=new CompteCourant();
        cp.setCode("GBFD125055");
        cp.setClient(c);
        cp.setDateCreation(new Date());
        cp.setSolde(15000);
        cp.setDecouvert(20000);
        compteDao.save(cp);
        Versement v= new Versement();
        v.setCompte(cp);
        v.setDateOperation(new Date());
        v.setMontant(1500);
        operatioDao.save(v);






         c=new Client();
        c.setEmail("Assita@gmail.com");
        c.setNom("Traore");
        c.setPrenom("Assita");
        clientDao.save(c);
        CompteEpargne ce=new CompteEpargne();
        ce.setCode("15HGH15850");
        ce.setClient(c);
        ce.setDateCreation(new Date());
        ce.setSolde(15000);
        ce.setTaux(30);
        compteDao.save(ce);
        v= new Versement();
        v.setCompte(ce);
        v.setDateOperation(new Date());
        v.setMontant(800000);
        operatioDao.save(v);*/
    }
}
