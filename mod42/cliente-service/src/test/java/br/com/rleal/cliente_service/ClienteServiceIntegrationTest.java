package br.com.rleal.cliente_service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.rleal.cliente_service.domain.Cliente;
import br.com.rleal.cliente_service.dto.ClienteRequestDTO;
import br.com.rleal.cliente_service.dto.ClienteResponseDTO;
import br.com.rleal.cliente_service.exception.RegraNegocioException;
import br.com.rleal.cliente_service.repository.IClienteRepository;
import br.com.rleal.cliente_service.service.ClienteService;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceIntegrationTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {

        clienteRepository.deleteAll();
    }

    @Test
    public void deveCriarClienteComSucesso() {
        ClienteRequestDTO request = new ClienteRequestDTO(
                "João da Silva",
                "joao@email.com",
                "51999999999",
                "07148003061"
        );

        ClienteResponseDTO response = clienteService.criar(request);

        assertNotNull(response.getId());
        assertEquals("João da Silva", response.getNome());
        assertEquals("joao@email.com", response.getEmail());

        Optional<Cliente> clienteNoBanco = clienteRepository.findById(response.getId());
        assertTrue(clienteNoBanco.isPresent());
    }

    @Test
    public void deveLancarExcecaoAoCadastrarCpfDuplicado() {

        Cliente clienteExistente = new Cliente(null, "Pedro", "pedro@email.com", "51888888888", "07148003061");
        clienteRepository.save(clienteExistente);

        ClienteRequestDTO requestDuplicado = new ClienteRequestDTO(
                "Lucas",
                "lucas@email.com",
                "51777777777",
                "07148003061"
        );

        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            clienteService.criar(requestDuplicado);
        });

        assertEquals("CPF já cadastrado no sistema.", excecao.getMessage());
    }
    
    @Test
    public void deveBuscarClientePorIdComSucesso() {
        // Cenário: Salva um cliente prévio diretamente via repository
        Cliente cliente = new Cliente(null, "Carlos Souza", "carlos@email.com", "51988887777", "43825838084");
        cliente = clienteRepository.save(cliente);

        // Ação
        ClienteResponseDTO response = clienteService.buscarPorId(cliente.getId());

        // Validação
        assertNotNull(response);
        assertEquals(cliente.getId(), response.getId());
        assertEquals("Carlos Souza", response.getNome());
        assertEquals("carlos@email.com", response.getEmail());
    }

    @Test
    public void deveLancarExcecaoAoBuscarClienteInexistente() {
        // Ação & Validação: Garante que buscar por um ID aleatório gera erro
        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            clienteService.buscarPorId("id_inexistente_123");
        });

        assertEquals("Cliente não encontrado com o ID fornecido.", excecao.getMessage());
    }

    @Test
    public void deveListarTodosOsClientesComSucesso() {

        Cliente c1 = new Cliente(null, "Amanda", "amanda@email.com", "51911111111", "04753063065");
        Cliente c2 = new Cliente(null, "Bruno", "bruno@email.com", "51922222222", "55122177002");
        clienteRepository.save(c1);
        clienteRepository.save(c2);


        List<ClienteResponseDTO> resultado = clienteService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        
        List<String> nomes = resultado.stream().map(ClienteResponseDTO::getNome).collect(Collectors.toList());
        assertTrue(nomes.contains("Amanda"));
        assertTrue(nomes.contains("Bruno"));
    }

    @Test
    public void deveAtualizarClienteComSucesso() {

        Cliente clienteOriginal = new Cliente(null, "Felipe", "felipe@email.com", "51933333333", "03612503020");
        clienteOriginal = clienteRepository.save(clienteOriginal);


        ClienteRequestDTO requestAtualizacao = new ClienteRequestDTO(
                "Felipe Antunes",
                "felipe.novo@email.com",
                "51944444444",
                "03612503020"
        );

        ClienteResponseDTO response = clienteService.atualizar(clienteOriginal.getId(), requestAtualizacao);

        assertEquals("Felipe Antunes", response.getNome());
        assertEquals("felipe.novo@email.com", response.getEmail());
        assertEquals("51944444444", response.getTelefone());
        assertEquals("03612503020", response.getCpf());

        Cliente clienteNoBanco = clienteRepository.findById(clienteOriginal.getId()).get();
        assertEquals("Felipe Antunes", clienteNoBanco.getNome());
        assertEquals("felipe.novo@email.com", clienteNoBanco.getEmail());
    }

    @Test
    public void deveLancarExcecaoAoTentarAlterarCpfNaAtualizacao() {

        Cliente clienteOriginal = new Cliente(null, "Juliana", "juliana@email.com", "51955555555", "84725026048");
        clienteOriginal = clienteRepository.save(clienteOriginal);


        ClienteRequestDTO requestComCpfDiferente = new ClienteRequestDTO(
                "Juliana",
                "juliana@email.com",
                "51955555555",
                "54641951000"
        );

        String idCliente = clienteOriginal.getId();

        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            clienteService.atualizar(idCliente, requestComCpfDiferente);
        });

        assertEquals("O CPF é um dado imutável e não pode ser alterado após o cadastro inicial.", excecao.getMessage());
    }

    @Test
    public void deveLancarExcecaoAoAtualizarParaEmailQueJaPertenceAOutroCliente() {

        Cliente clienteExistente1 = new Cliente(null, "Lucas", "lucas@email.com", "51966666666", "11666497042");
        Cliente clienteExistente2 = new Cliente(null, "Mariana", "mariana@email.com", "51977777777", "95325091007");
        clienteRepository.save(clienteExistente1);
        clienteExistente2 = clienteRepository.save(clienteExistente2);

        ClienteRequestDTO requestComEmailDuplicado = new ClienteRequestDTO(
                "Mariana Souza",
                "lucas@email.com", // E-mail já usado pelo Lucas
                "51977777777",
                "95325091007"
        );

        String idMariana = clienteExistente2.getId();

        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            clienteService.atualizar(idMariana, requestComEmailDuplicado);
        });

        assertEquals("Este e-mail já está sendo usado por outro cliente.", excecao.getMessage());
    }
}