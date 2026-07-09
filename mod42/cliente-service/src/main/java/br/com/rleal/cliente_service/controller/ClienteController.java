package br.com.rleal.cliente_service.controller;

import br.com.rleal.cliente_service.dto.ClienteRequestDTO;
import br.com.rleal.cliente_service.dto.ClienteResponseDTO;
import br.com.rleal.cliente_service.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento do cadastro de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente", description = "Valida os dados e salva um novo cliente no MongoDB.")
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO clienteCriado = clienteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes cadastrados.")
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteResponseDTO> lista = clienteService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Busca os detalhes de um cliente específico pelo seu identificador único.")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable String id) {
        ClienteResponseDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente com base no ID fornecido.")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable String id, @Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO clienteAtualizado = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente", description = "Remove um cliente da base de dados definitivamente.")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        clienteService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}