package com.example.proxibanquebc.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Compte { // Super Classe pour engendrer les autres model

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // info du compte demander par la banque
    /*
     * Tout compte bancaire (qu'il soit compte courant ou compte épargne)
     * est caractérisé par
     * un numéro de compte,
     * un solde et
     * une date d'ouverture du compte.
     * */
    @Column(unique = true)
    private String numeroCompte;
    private double solde;
    private LocalDate dateOuverture = LocalDate.now();

    // le client relie au compte
    @ManyToOne
    private Client client;
}