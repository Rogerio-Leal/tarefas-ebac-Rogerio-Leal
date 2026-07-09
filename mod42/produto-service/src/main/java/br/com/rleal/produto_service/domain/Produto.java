package br.com.rleal.produto_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Document(collection = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    private String id;
    
    private String nome;
    
    private String descricao;
    
    private BigDecimal preco;
    
    @Indexed(unique = true)
    private String sku;
    
    private Integer quantidadeEstoque;
}