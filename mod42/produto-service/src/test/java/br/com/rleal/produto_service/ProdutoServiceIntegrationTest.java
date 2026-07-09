package br.com.rleal.produto_service;

import br.com.rleal.produto_service.domain.Produto;
import br.com.rleal.produto_service.dto.ProdutoRequestDTO;
import br.com.rleal.produto_service.dto.ProdutoResponseDTO;
import br.com.rleal.produto_service.exception.RegraNegocioException;
import br.com.rleal.produto_service.repository.IProdutoRepository;
import br.com.rleal.produto_service.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceIntegrationTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private IProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
    	
        produtoRepository.deleteAll();
    }

    @Test
    public void deveCriarProdutoComSucesso() {

        ProdutoRequestDTO request = new ProdutoRequestDTO(
                "Detergente Neutro",
                "Detergente líquido para louças de 500ml",
                new BigDecimal("2.50"),
                "PROD-DET-001",
                150
        );

        ProdutoResponseDTO response = produtoService.criar(request);

        assertNotNull(response.getId());
        assertEquals("Detergente Neutro", response.getNome());
        assertEquals(new BigDecimal("2.50"), response.getPreco());
        assertEquals("PROD-DET-001", response.getSku());

        Optional<Produto> produtoNoBanco = produtoRepository.findById(response.getId());
        assertTrue(produtoNoBanco.isPresent());
    }

    @Test
    public void deveLancarExcecaoAoCadastrarSkuDuplicado() {

        Produto produtoExistente = new Produto(null, "Sabão em Pó", "Sabão de 1kg", new BigDecimal("12.90"), "PROD-SAB-002", 50);
        produtoRepository.save(produtoExistente);


        ProdutoRequestDTO requestDuplicado = new ProdutoRequestDTO(
                "Sabão Líquido",
                "Sabão líquido de 1L",
                new BigDecimal("15.90"),
                "PROD-SAB-002",
                30
        );

        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            produtoService.criar(requestDuplicado);
        });

        assertEquals("Já existe um produto cadastrado com este SKU.", excecao.getMessage());
    }

    @Test
    public void deveBuscarProdutoPorIdComSucesso() {

        Produto produto = new Produto(null, "Desinfetante Pinho", "Desinfetante de 1L", new BigDecimal("7.80"), "PROD-DES-003", 80);
        produto = produtoRepository.save(produto);

        ProdutoResponseDTO response = produtoService.buscarPorId(produto.getId());

        assertNotNull(response);
        assertEquals(produto.getId(), response.getId());
        assertEquals("Desinfetante Pinho", response.getNome());
    }

    @Test
    public void deveLancarExcecaoAoBuscarProdutoInexistente() {

        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            produtoService.buscarPorId("id_invalido");
        });

        assertEquals("Produto não encontrado.", excecao.getMessage());
    }

    @Test
    public void deveListarTodosOsProdutosComSucesso() {

        Produto p1 = new Produto(null, "Esponja", "Esponja de lavar louça", new BigDecimal("1.20"), "PROD-ESP-004", 500);
        Produto p2 = new Produto(null, "Pano de Prato", "Pano de algodão", new BigDecimal("4.50"), "PROD-PAN-005", 200);
        produtoRepository.save(p1);
        produtoRepository.save(p2);

        List<ProdutoResponseDTO> resultado = produtoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        List<String> skus = resultado.stream().map(ProdutoResponseDTO::getSku).collect(Collectors.toList());
        assertTrue(skus.contains("PROD-ESP-004"));
        assertTrue(skus.contains("PROD-PAN-005"));
    }

    @Test
    public void deveAtualizarProdutoComSucesso() {
    	
        Produto produtoOriginal = new Produto(null, "Água Sanitária", "Água sanitária de 2L", new BigDecimal("5.50"), "PROD-AGU-006", 100);
        produtoOriginal = produtoRepository.save(produtoOriginal);

        ProdutoRequestDTO requestAtualizacao = new ProdutoRequestDTO(
                "Água Sanitária Premium",
                "Água sanitária concentrada de 2L",
                new BigDecimal("6.20"),
                "PROD-AGU-006",
                120
        );

        ProdutoResponseDTO response = produtoService.atualizar(produtoOriginal.getId(), requestAtualizacao);

        assertEquals("Água Sanitária Premium", response.getNome());
        assertEquals(new BigDecimal("6.20"), response.getPreco());
        assertEquals(120, response.getQuantidadeEstoque());

        Produto produtoNoBanco = produtoRepository.findById(produtoOriginal.getId()).get();
        assertEquals("Água Sanitária Premium", produtoNoBanco.getNome());
    }

    @Test
    public void deveLancarExcecaoAoTentarAlterarSkuNaAtualizacao() {

        Produto produtoOriginal = new Produto(null, "Limpa Vidros", "Limpa vidros spray", new BigDecimal("8.90"), "PROD-VID-007", 60);
        produtoOriginal = produtoRepository.save(produtoOriginal);

        ProdutoRequestDTO requestComSkuDiferente = new ProdutoRequestDTO(
                "Limpa Vidros",
                "Limpa vidros spray",
                new BigDecimal("8.90"),
                "PROD-VID-MODIFICADO",
                60
        );

        String idProduto = produtoOriginal.getId();

        RegraNegocioException excecao = assertThrows(RegraNegocioException.class, () -> {
            produtoService.atualizar(idProduto, requestComSkuDiferente);
        });

        assertEquals("O SKU é um identificador imutável e não pode ser alterado.", excecao.getMessage());
    }
}