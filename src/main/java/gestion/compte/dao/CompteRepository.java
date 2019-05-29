package gestion.compte.dao;

import gestion.compte.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,String> {
    public Page<Compte>findByCodeContains(String mc, Pageable pageable);
}
