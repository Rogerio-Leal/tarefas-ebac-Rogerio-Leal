package br.com.rleal.produto_service.controller;

import br.com.rleal.produto_service.dto.ProdutoRequestDTO;
import br.com.rleal.produto_service.dto.ProdutoResponseDTO;
import br.com.rleal.produto_service.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento do catálogo de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo produto", description = "Valida os dados e insere um produto no MongoDB.")
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoCriado = produtoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna o catálogo completo de produtos.")
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico.")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza as informações de um produto existente baseado no ID.")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable String id, @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto", description = "Remove permanentemente um produto do catálogo.")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}