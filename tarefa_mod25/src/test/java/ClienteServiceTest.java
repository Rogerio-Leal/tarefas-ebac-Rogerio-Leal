package test.java;

import main.java.dao.IClienteDAO;
import main.java.exceptions.TipoChaveNaoEncontradaException;
import main.java.services.ClienteService;
import main.java.services.IClienteService;
import main.java.domain.Cliente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.java.dao.ClienteDaoMock;

public class ClienteServiceTest {

    private IClienteService clienteService;

    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteDAO dao = new ClienteDaoMock();
        clienteService = new ClienteService(dao);
    }

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(12312312312L);
        cliente.setNome("Rogério");
        cliente.setCidade("Rio Grande do Sul");
        cliente.setEnd("End");
        cliente.setEstado("RS");
        cliente.setNumero(10);
        cliente.setTel(12345678901L);

    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteService.cadastrar(cliente);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente() {
        clienteService.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Rogério Leal");
        clienteService.alterar(cliente);
        Assert.assertEquals("Rogério Leal", cliente.getNome());
    }
}
