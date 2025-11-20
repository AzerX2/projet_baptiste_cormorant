package com.example.proxibanque.repository;

import com.example.proxibanque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
