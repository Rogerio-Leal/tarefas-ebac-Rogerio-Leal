package br.com.rleal.domain.dao;

import br.com.rleal.domain.Produto;
import br.com.rleal.domain.dao.generic.GenericDAO;

public class ProdutoDAO extends GenericDAO<Produto, Long> implements IProdutoDAO {

	public ProdutoDAO() {
		super(Produto.class);
	}

}