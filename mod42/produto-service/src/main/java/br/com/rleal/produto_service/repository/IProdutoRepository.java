package br.com.rleal.produto_service.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.rleal.produto_service.domain.Produto;

import java.util.Optional;

@Repository
public interface IProdutoRepository extends MongoRepository<Produto, String> {
    
    Optional<Produto> findBySku(String sku);
}