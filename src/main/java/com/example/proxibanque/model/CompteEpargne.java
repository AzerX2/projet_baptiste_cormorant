package com.example.proxibanque.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class CompteEpargne extends Compte {
    // Le compte épargne est caractérisé par le taux de réunération, par défaut à 3%
    private double tauxRemuneration = 0.03;
}
