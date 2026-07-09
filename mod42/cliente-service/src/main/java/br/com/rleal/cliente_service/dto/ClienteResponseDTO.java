package br.com.rleal.cliente_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
	private String id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

}
