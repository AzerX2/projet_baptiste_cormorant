package com.example.proxibanquebc;

import com.example.proxibanquebc.model.Client;
import com.example.proxibanquebc.model.CompteCourant;
import com.example.proxibanquebc.model.CompteEpargne;
import com.example.proxibanquebc.repository.ClientRepository;
import com.example.proxibanquebc.repository.CompteRepository;
import com.example.proxibanquebc.service.CompteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProxiBanqueBcApplication {

    private static final Logger log = LoggerFactory.getLogger(ProxiBanqueBcApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProxiBanqueBcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ClientRepository clientRepository, CompteService compteService, CompteRepository compteRepository) {
        return args -> {
            log.info("--- Demarrage du test d'initialisation ---");

            // Creation client
            Client clientA = new Client();
            clientA.setNom("Dupont");
            clientA.setPrenom("Alice");

            Client clientB = new Client();
            clientB.setNom("Martin");
            clientB.setPrenom("Bob");

            // Creation Compte
            //Courant
            CompteCourant ccA = new CompteCourant();
            ccA.setNumeroCompte("CC0001A");
            ccA.setSolde(2500.0);
            ccA.setClient(clientA);

            CompteCourant ccB = new CompteCourant();
            ccB.setNumeroCompte("CC0002B");
            ccB.setSolde(15000.0);
            ccB.setClient(clientB);

            //Epargne
            CompteEpargne ceA = new CompteEpargne();
            ceA.setNumeroCompte("CE0001A");
            ceA.setSolde(500.0);
            ceA.setClient(clientA);

            // Set
            clientA.setComptesCourants(List.of(ccA));
            clientA.setComptesEpargnes(List.of(ceA));
            clientB.setComptesCourants(List.of(ccB));

            clientRepository.saveAll(List.of(clientA, clientB));


            double montantVirement = 500.0;
            log.info("--- Test virement de 500.0 € de Bob (CC0002B) vers Alice (CC0001A) ---");
            log.info("Solde avant Alice (CC0001A): {} €", compteRepository.findByNumeroCompte("CC0001A").getSolde());
            log.info("Solde avant Bob (CC0002B): {} €", compteRepository.findByNumeroCompte("CC0002B").getSolde());

            try {
                compteService.Virement("CC0002B", "CC0001A", montantVirement);
                log.info("Sucess: virement reussi.");
            } catch (Exception e) {
                log.error("Erreur: virement: {}", e.getMessage());
            }

            log.info("Solde apres Alice (CC0001A): {} € (Attendu: 3000.0)", compteRepository.findByNumeroCompte("CC0001A").getSolde());
            log.info("Solde apres Bob (CC0002B): {} € (Attendu: 14500.0)", compteRepository.findByNumeroCompte("CC0002B").getSolde());

            log.info("--- Initialisation terminee ---");
        };
    }
}