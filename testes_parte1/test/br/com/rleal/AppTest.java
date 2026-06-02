package br.com.rleal;

import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void deveConterApenasMulheresNoMap() {

        //Simula a digitação no console
        String textoDigitado = "1-Carlos-M, 2-Ana-F, 3-Bruno-M, 4-Maria-f";

        Map<Integer, Pessoa> resultado = App.filtrarMulheres(textoDigitado);

        assertFalse("O Map não deveria estar vazio", resultado.isEmpty());

        boolean todosFemininos = resultado.values().stream()
                .allMatch(pessoa -> pessoa.getSexo().equalsIgnoreCase("F"));

        assertTrue("O Map deve conter apenas pessoas do sexo feminino", todosFemininos);
    }
}
