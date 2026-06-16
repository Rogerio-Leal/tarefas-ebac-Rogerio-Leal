package br.com.rleal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.rleal.dao.IProdutoDAO;
import br.com.rleal.dao.ProdutoDAO;
import br.com.rleal.domain.Produto;


public class ProdutoTest {
	
	@Test
	public void cadastrarProdutoTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("PS5");
		
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.consultar(produto.getCodigo());
		assertNotNull(produtoBD);
		assertNotNull(produtoBD.getId());
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		Integer qtdDel = dao.excluir(produtoBD);
		assertNotNull(qtdDel);
	}
	
	@Test
	public void buscarTodosProdutosTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("PS5");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtos = new Produto();
		produtos.setCodigo("02");
		produtos.setNome("Xbox Series X");
		Integer qtd2 = dao.cadastrar(produtos);
		assertTrue(qtd2 == 1);
		
		List<Produto> list = dao.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Produto prod : list) {
			dao.excluir(prod);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = dao.buscarTodos();
		assertEquals(list.size(), 0);
		
	}
	
	@Test
	public void atualizarProdutoTest() throws Exception {
		IProdutoDAO dao = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("01");
		produto.setNome("PS5");
		Integer qtd = dao.cadastrar(produto);
		assertTrue(qtd == 1);
		
		Produto produtoBD = dao.buscar("01");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		
		produtoBD.setCodigo("02");
		produtoBD.setNome("PS5 Pro");
		Integer countUpdate = dao.atualizar(produtoBD);
		assertTrue(countUpdate == 1);
		
		Produto produtoBD1 = dao.buscar("01");
		assertNull(produtoBD1);
		
		Produto produtoBD2 = dao.buscar("02");
		assertNotNull(produtoBD2);
		assertEquals(produtoBD.getId(), produtoBD2.getId());
		assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
		assertEquals(produtoBD.getNome(), produtoBD2.getNome());
		
		List<Produto> list = dao.buscarTodos();
		for (Produto prod : list) {
			dao.excluir(prod);
		}
	}

}
