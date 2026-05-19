import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Parte1 {

    public static void main(String[] args) {
        listaDeContatos();
    }

    private static void listaDeContatos() {
        Scanner scanner = new Scanner(System.in);

        // 1. Recebe os nomes digitados no console
        System.out.println("Digite os nomes separados por vírgula (ex: Carlos, Ana, Bruno):");
        String textoDigitado = scanner.nextLine();

        String[] nomesSeparados = textoDigitado.split(",");

        List<String> listaDeNomes = new ArrayList<>();

        Collections.sort(listaDeNomes);

        System.out.println("\n*** Nomes em Ordem Alfabética ***");
        listaDeNomes.forEach(nome -> System.out.println(nome));
    }
}
