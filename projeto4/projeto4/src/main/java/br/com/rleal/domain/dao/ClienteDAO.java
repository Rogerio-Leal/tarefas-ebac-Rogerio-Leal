package br.com.rleal.domain.dao;

import br.com.rleal.domain.Cliente;
import br.com.rleal.domain.dao.generic.GenericDAO;

public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super(Cliente.class);
	}

}