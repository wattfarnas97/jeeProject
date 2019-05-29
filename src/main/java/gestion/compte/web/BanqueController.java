package gestion.compte.web;


import gestion.compte.entities.Compte;
import gestion.compte.entities.Operation;
import gestion.compte.metier.IBanque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BanqueController  {
    @Autowired
    private IBanque banqueMetier;


    @GetMapping("/comptes")
    public String comptes(Model model, @RequestParam(name="page",defaultValue="0")int page, @RequestParam(name="motCle",defaultValue="")String mc)
    {
        Page<Compte> listCompte=banqueMetier.listeCompte(mc, page,5);


        model.addAttribute("listCompte",listCompte.getContent());
        model.addAttribute("pages",new int[listCompte.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("motCle",mc);
        return "comptes";
    }

    @GetMapping("/operations")
    public String operations()
    {

        return "operations";
    }
    @GetMapping("/clients")
    public String clients()
    {

        return "clients";
    }


    @GetMapping("/detailCompte")
    public  String ConsulterSolde(Model model ,String code, @RequestParam(name="page",defaultValue="0")int page)
    {
        Compte cp=banqueMetier.consulterCompte(code);
        Page<Operation> listOperation=banqueMetier.listOperation(code,page,5);

        model.addAttribute("compte",cp);

        model.addAttribute("listOperation",listOperation.getContent());
        model.addAttribute("pages",new int[listOperation.getTotalPages()]);
        model.addAttribute("currentPage",page);
        return "detailCompte";
    }


    @GetMapping("/operation")
    public  String operation(Model model,String code)
    {
        Compte cp=banqueMetier.consulterCompte(code);
        model.addAttribute("compte",cp);
        return "operation";
    }
    @PostMapping("/saveoperation")
        public String saveOpeartion(Model model,String compte,String type,double montant,String destination)
        {

            try {
                if(type.equals("versement"))
                {
                    banqueMetier.verser(compte,montant);

                }

                else  if(type.equals("retrait"))
                {
                    banqueMetier.retirer(compte,montant);
                }
                else
                {
                    banqueMetier.virement(compte,destination,montant);
                }

            }catch (Exception e)
            {
                model.addAttribute("exeption",e);

                return "redirect:/operation?code="+compte+"&error="+e.getMessage();
            }
            return "redirect:/detailCompte?code="+compte;

        }

}
