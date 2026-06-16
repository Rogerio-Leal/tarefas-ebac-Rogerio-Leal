package br.com.rleal.dao;

import java.util.List;

import br.com.rleal.domain.Produto;

public interface IProdutoDAO {

	public Integer cadastrar(Produto produto) throws Exception;

	public Produto consultar(String codigo) throws Exception;

	public Integer excluir(Produto produtoBD) throws Exception;

	public List<Produto> buscarTodos() throws Exception;

	public Produto buscar(String string) throws Exception;

	public Integer atualizar(Produto produtoBD) throws Exception;

}
