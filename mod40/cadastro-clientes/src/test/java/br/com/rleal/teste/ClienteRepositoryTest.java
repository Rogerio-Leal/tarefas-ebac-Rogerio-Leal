package br.com.rleal.teste;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import br.com.rleal.domain.Cliente;
import br.com.rleal.repository.ClienteRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    @DisplayName("Teste simples de salvamento")
    void deveSalvarCliente() {
        // 1. Criar o objeto sem ID (deixe o banco gerar)
        Cliente cliente = Cliente.builder()
                .nome("Rogério")
                .cpf("11122233344") // Garanta que este CPF não existe no banco
                .email("teste@teste.com")
                .build();

        // 2. Salvar
        Cliente salvo = repository.save(cliente);

        // 3. Validar
        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
    }
}