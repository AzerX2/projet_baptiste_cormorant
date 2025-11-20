package com.example.proxibanque.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class CompteCourant extends Compte {
    //  En particulier, le Compte courant est caractérisé
    //  par une autorisation de découvert par défaut de
    //1000 euros.
    private double decouvertAutorise = 1000.0;
}