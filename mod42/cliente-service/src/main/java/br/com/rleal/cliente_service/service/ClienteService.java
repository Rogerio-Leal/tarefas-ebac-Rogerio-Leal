package br.com.rleal.cliente_service.service;

import br.com.rleal.cliente_service.domain.Cliente;
import br.com.rleal.cliente_service.dto.ClienteRequestDTO;
import br.com.rleal.cliente_service.dto.ClienteResponseDTO;
import br.com.rleal.cliente_service.exception.RegraNegocioException;
import br.com.rleal.cliente_service.repository.IClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponseDTO criar(ClienteRequestDTO dto) {

        if (clienteRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RegraNegocioException("CPF já cadastrado no sistema.");
        }
        if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RegraNegocioException("E-mail já cadastrado no sistema.");
        }


        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setCpf(dto.getCpf());

        cliente = clienteRepository.save(cliente);

        return converteParaDTO(cliente);
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::converteParaDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(String id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado com o ID fornecido."));
        return converteParaDTO(cliente);
    }

    public ClienteResponseDTO atualizar(String id, ClienteRequestDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado para atualização."));

        // 1. TRAVA DE SEGURANÇA: Impede que o usuário tente enviar um CPF diferente
        if (!clienteExistente.getCpf().equals(dto.getCpf())) {
            throw new RegraNegocioException("O CPF é um dado imutável e não pode ser alterado após o cadastro inicial.");
        }

        // 2. Valida se o e-mail novo já pertence a OUTRO cliente
        clienteRepository.findByEmail(dto.getEmail())
                .ifPresent(c -> {
                    if (!c.getId().equals(id)) {
                        throw new RegraNegocioException("Este e-mail já está sendo usado por outro cliente.");
                    }
                });

        // 3. Atualiza apenas os dados permitidos
        clienteExistente.setNome(dto.getNome());
        clienteExistente.setEmail(dto.getEmail());
        clienteExistente.setTelefone(dto.getTelefone());
        // O CPF foi removido daqui propositalmente!

        clienteExistente = clienteRepository.save(clienteExistente);
        return converteParaDTO(clienteExistente);
    }

    public void deletar(String id) {
        if (!clienteRepository.existsById(id)) {
            throw new RegraNegocioException("Não é possível deletar: Cliente não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO converteParaDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setCpf(cliente.getCpf());
        return dto;
    }
}