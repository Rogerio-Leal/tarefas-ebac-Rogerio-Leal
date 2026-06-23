package br.com.rleal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.rleal.dao.AcessorioDAO;
import br.com.rleal.dao.CarroDAO;
import br.com.rleal.dao.IAcessorioDAO;
import br.com.rleal.dao.ICarroDAO;
import br.com.rleal.dao.IMarcaDAO;
import br.com.rleal.dao.MarcaDAO;
import br.com.rleal.domain.Acessorio;
import br.com.rleal.domain.Carro;
import br.com.rleal.domain.Marca;

public class CarroTest {
	
	private IMarcaDAO marcaDao;
	
	private ICarroDAO carroDao;
	
	private IAcessorioDAO acessorioDao;
	
	public CarroTest() {
		
		marcaDao = new MarcaDAO();
		
		carroDao = new CarroDAO();
		
		acessorioDao = new AcessorioDAO();		
	}

	@Test
	public void cadastrar() {
		
		Marca marca = new Marca();
		marca.setCodigo("M02");
        marca.setNome("Volkswagen");

        Acessorio tetoSolar = new Acessorio();
        tetoSolar.setCodigo("A03");
        tetoSolar.setNome("Teto Solar");

        Acessorio arCondicionado = new Acessorio();
        arCondicionado.setCodigo("A04");
        arCondicionado.setNome("Ar Condicionado");

        Carro carro = new Carro();
        carro.setCodigo("GOLF02");
        carro.setNome("Golf GTI");
        carro.setPreco(new BigDecimal("150000.00"));

        carro.setMarca(marca);
        
        List<Acessorio> acessorios = new ArrayList<>();
        acessorios.add(tetoSolar);
        acessorios.add(arCondicionado);
        carro.setAcessorios(acessorios);

        marcaDao.cadastrar(marca);
        acessorioDao.cadastrar(tetoSolar);
        acessorioDao.cadastrar(arCondicionado);
        carroDao.cadastrar(carro);
	}
}
