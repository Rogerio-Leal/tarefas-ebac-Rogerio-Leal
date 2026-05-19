import java.util.ArrayList;
import java.util.List;

public class Generos {
    public static void main(String[] args) {
        cadastro();
    }

    private static void cadastro() {
        System.out.println("*** Cadastro de Pessoas ***");
        List<Pessoas> pessoas = new ArrayList<>();

        Pessoas a = new Pessoas("Ana Flávia Leal", "feminino");
        Pessoas b = new Pessoas("Rogério Leal", "masculino");
        Pessoas c = new Pessoas("Fernando Silva", "masculino");
        Pessoas d = new Pessoas("Brenda Soares", "feminino");

        pessoas.add(a);
        pessoas.add(b);
        pessoas.add(c);
        pessoas.add(d);

        List<Pessoas> grupoFeminino = new ArrayList<>();
        List<Pessoas> grupoMasculino = new ArrayList<>();

        for (Pessoas pessoa : pessoas) {
            if (pessoa.getSexo().equalsIgnoreCase("feminino")) {
                grupoFeminino.add(pessoa);
            } else if (pessoa.getSexo().equalsIgnoreCase("masculino")) {
                grupoMasculino.add(pessoa);
            }
        }

        System.out.println("\n*** Grupo Feminino ***");
        for (Pessoas mulher : grupoFeminino) {
            System.out.println(mulher);
        }

        System.out.println("\n*** Grupo Masculino ***");
        for (Pessoas homem : grupoMasculino) {
            System.out.println(homem);
        }
    }
}
