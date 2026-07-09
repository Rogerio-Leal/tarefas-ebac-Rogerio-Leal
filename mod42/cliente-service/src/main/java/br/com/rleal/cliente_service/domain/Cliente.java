package br.com.rleal.cliente_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    
    private String nome;
    
    @Indexed(unique = true)
    private String email;
    
    private String telefone;
    
    @Indexed(unique = true)
    private String cpf;
}