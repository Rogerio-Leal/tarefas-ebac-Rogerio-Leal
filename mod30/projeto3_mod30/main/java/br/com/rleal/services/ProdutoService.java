package br.com.rleal.services;

import br.com.rleal.dao.IProdutoDAO;
import br.com.rleal.domain.Produto;
import br.com.rleal.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {
	
	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
