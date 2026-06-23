package br.com.rleal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.rleal.domain.Acessorio;
import br.com.rleal.domain.Carro;
import br.com.rleal.domain.Marca;

public class CarroTest {

	@Test
	public void cadastrar() {
		
		Marca marca = new Marca();
	    marca.setNome("Volkswagen");

	    Acessorio tetoSolar = new Acessorio();
	    tetoSolar.setNome("Teto Solar");

	    Acessorio arCondicionado = new Acessorio();
	    arCondicionado.setNome("Ar Condicionado");

	    Carro carro = new Carro();
	    carro.setCodigo("GOLF01");
	    carro.setNome("Golf GTI");
	    carro.setPreco(new BigDecimal("150000.00"));
	    carro.setMarca(marca);

	    List<Acessorio> listaAcessorios = new ArrayList<>();
	    listaAcessorios.add(tetoSolar);
	    listaAcessorios.add(arCondicionado);
	    carro.setAcessorios(listaAcessorios);
	}
}
