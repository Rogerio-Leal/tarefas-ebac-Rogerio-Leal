package br.com.rleal.produto_service.service;

import br.com.rleal.produto_service.domain.Produto;
import br.com.rleal.produto_service.dto.ProdutoRequestDTO;
import br.com.rleal.produto_service.dto.ProdutoResponseDTO;
import br.com.rleal.produto_service.exception.RegraNegocioException; // Reaproveite a mesma estrutura de exceção
import br.com.rleal.produto_service.repository.IProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final IProdutoRepository produtoRepository;

    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        if (produtoRepository.findBySku(dto.getSku()).isPresent()) {
            throw new RegraNegocioException("Já existe um produto cadastrado com este SKU.");
        }

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setSku(dto.getSku());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        produto = produtoRepository.save(produto);
        return converteParaDTO(produto);
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converteParaDTO)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(String id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado."));
        return converteParaDTO(produto);
    }

    public ProdutoResponseDTO atualizar(String id, ProdutoRequestDTO dto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado para atualização."));

        // Trava de segurança: impede alteração do SKU
        if (!produtoExistente.getSku().equals(dto.getSku())) {
            throw new RegraNegocioException("O SKU é um identificador imutável e não pode ser alterado.");
        }

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setDescricao(dto.getDescricao());
        produtoExistente.setPreco(dto.getPreco());
        produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        produtoExistente = produtoRepository.save(produtoExistente);
        return converteParaDTO(produtoExistente);
    }

    public void deletar(String id) {
        if (!produtoRepository.existsById(id)) {
            throw new RegraNegocioException("Não é possível deletar: Produto não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoResponseDTO converteParaDTO(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setSku(produto.getSku());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return dto;
    }
}