package com.example.proxibanque.repository;

import com.example.proxibanque.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    // normalement ca marche
    Compte findByNumeroCompte(String numeroCompte);
}
