package com.example.proxibanque.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Compte {

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
    private LocalDate dateOuverture;

    // le client (a faire)
    // Un client est caractérisé par : nom, prenom, adresse,code postal,ville, téléphone
    @ManyToOne
    private Client client;
}