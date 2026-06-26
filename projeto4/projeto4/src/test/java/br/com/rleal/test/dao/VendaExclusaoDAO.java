package br.com.rleal.test.dao;

import br.com.rleal.domain.Venda;
import br.com.rleal.domain.dao.IVendaDAO;
import br.com.rleal.domain.dao.generic.GenericDAO;
import br.com.rleal.exceptions.DAOException;
import br.com.rleal.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoDAO extends GenericDAO<Venda, Long> implements IVendaDAO {

	public VendaExclusaoDAO() {
		super(Venda.class);
	}

	@Override
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public Venda consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}