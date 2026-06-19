package br.com.rleal.dao;

import br.com.rleal.dao.exceptions.DAOException;
import br.com.rleal.dao.exceptions.TipoChaveNaoEncontradaException;
import br.com.rleal.dao.generic.IGenericDAO;
import br.com.rleal.domain.Venda;

public interface IVendaDAO extends IGenericDAO<Venda, String> {
	
	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;

}
