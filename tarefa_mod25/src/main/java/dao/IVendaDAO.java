package main.java.dao;

import main.java.dao.generic.IGenericDAO;
import main.java.domain.Venda;
import main.java.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}
