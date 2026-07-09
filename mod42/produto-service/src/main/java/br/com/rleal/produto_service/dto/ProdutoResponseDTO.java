package br.com.rleal.produto_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String sku;
    private Integer quantidadeEstoque;
}