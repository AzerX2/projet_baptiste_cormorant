package com.example.proxibanque.controller;

import com.example.proxibanque.model.Client;
import com.example.proxibanque.repository.ClientRepository;
import com.example.proxibanque.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/conseiller")
@RequiredArgsConstructor
public class ConseillerController {

    private final ClientRepository clientRepository;
    private final CompteService compteService;

    @PostMapping("/client")
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> readClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/virement")
    public ResponseEntity<String> virement(@RequestBody Map<String, Object> request) {
        try {
            String debit = (String) request.get("compteDebit");
            String credit = (String) request.get("compteCredit");
            double montant = ((Number) request.get("montant")).doubleValue();

            compteService.Virement(debit, credit, montant);
            return ResponseEntity.ok("Success: Virement effectué avec succès.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}