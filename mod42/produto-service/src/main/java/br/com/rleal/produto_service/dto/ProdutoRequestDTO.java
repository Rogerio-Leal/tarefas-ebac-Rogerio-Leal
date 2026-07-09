package br.com.rleal.produto_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição do produto é obrigatória")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "O preço deve ser igual ou maior que zero")
    private BigDecimal preco;

    @NotBlank(message = "O SKU é obrigatório")
    private String sku;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa")
    private Integer quantidadeEstoque;
}