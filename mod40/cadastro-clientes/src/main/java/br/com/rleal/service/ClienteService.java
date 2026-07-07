package br.com.rleal.service;

import br.com.rleal.domain.Cliente;
import br.com.rleal.exception.RegraNegocioException;
import br.com.rleal.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean cpfEmUso = repository.existsByCpf(cliente.getCpf());
        
        if (cliente.getId() == null && cpfEmUso) {
            throw new RegraNegocioException("Já existe um cliente cadastrado com este CPF.");
        }

        return repository.save(cliente);
    }

    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado na base de dados."));
    }

    @Transactional
    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }
}