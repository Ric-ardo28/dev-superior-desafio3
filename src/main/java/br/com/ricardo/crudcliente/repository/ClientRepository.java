package br.com.ricardo.crudcliente.repository;

import br.com.ricardo.crudcliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
