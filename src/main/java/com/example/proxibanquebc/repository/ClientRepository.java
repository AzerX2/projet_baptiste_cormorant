package com.example.proxibanquebc.repository;

import com.example.proxibanquebc.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
