package br.com.rleal.cliente_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.rleal.cliente_service.domain.Cliente;

import java.util.Optional;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String> {
	
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    
}