package gestion.compte.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Client implements Serializable {
@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long code;
private String nom;
private String prenom;
private String email;
@OneToMany(mappedBy = "client",fetch = FetchType.LAZY,orphanRemoval = true)
private Collection<Compte> comptes;

    public Client(String nom, String prenom, String email, Collection<Compte> comptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.comptes = comptes;
    }

    public Client() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }
}
