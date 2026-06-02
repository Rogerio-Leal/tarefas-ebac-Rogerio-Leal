package br.com.rleal;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        listaDeContatos();
    }

    private static void listaDeContatos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os contatos no formato 'id-nome-sexo' separados por vírgula.");
        System.out.println("Ex: 1-Carlos-M, 2-Ana-F, 3-Bruno-M:");
        String textoDigitado = scanner.nextLine();

        Map<Integer, Pessoa> apenasMulheres = filtrarMulheres(textoDigitado);

        System.out.println("\n--- Apenas Pessoas do Sexo Feminino ---");
        apenasMulheres.forEach((codigo, pessoa) ->
                System.out.println("Id: " + codigo + " -> " + pessoa)
        );
        scanner.close();
    }
    //Criado novo método para separar a lógica de negócio da interação do console
    public static Map<Integer, Pessoa> filtrarMulheres(String textoDigitado) {
        String[] nomesSeparados = textoDigitado.split(",");

        Map<Integer, Pessoa> mapaDePessoas = Arrays.stream(nomesSeparados)
                .map(String::trim)
                .map(item -> item.split("-"))
                .filter(partes -> partes.length == 3)
                .collect(Collectors.toMap(
                        partes -> Integer.parseInt(partes[0].trim()),
                        partes -> new Pessoa(partes[1].trim(), partes[2].trim())
                ));

        return mapaDePessoas.entrySet().stream()
                .filter(entry -> "F".equalsIgnoreCase(entry.getValue().getSexo().trim()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
