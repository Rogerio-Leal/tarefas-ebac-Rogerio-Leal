package br.com.rleal.services;

import br.com.rleal.dao.exceptions.DAOException;
import br.com.rleal.domain.Cliente;
import br.com.rleal.services.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {
	
//	Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;
//
	Cliente buscarPorCPF(Long cpf) throws DAOException;
//
//	void excluir(Long cpf);
//
//	void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;

}
