package com.example.proxibanquebc.repository;

import com.example.proxibanquebc.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    // normalement ca marche
    Compte findByNumeroCompte(String numeroCompte);
}
