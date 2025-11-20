package com.example.proxibanquebc.service;

import com.example.proxibanquebc.model.Compte;
import com.example.proxibanquebc.repository.CompteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;

    @Transactional
    public void Virement(String numeroCompteDebit, String numeroCompteCredit, double montant) {

        Compte compteDebit = compteRepository.findByNumeroCompte(numeroCompteDebit);
        Compte compteCredit = compteRepository.findByNumeroCompte(numeroCompteCredit);

        if (compteDebit == null || compteCredit == null)
            throw new NoSuchElementException("Erreur: Un des comptes n'existe pas.");

        if (montant <= 0)
            throw new IllegalArgumentException("Erreur: Le montant doit être positif.");

        // A voir pour apres : check le decouvert
        compteDebit.setSolde(compteDebit.getSolde() - montant);

        compteCredit.setSolde(compteCredit.getSolde() + montant);

        compteRepository.save(compteDebit);
        compteRepository.save(compteCredit);
    }
}
