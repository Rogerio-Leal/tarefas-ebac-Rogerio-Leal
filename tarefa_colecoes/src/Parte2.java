import java.util.ArrayList;
import java.util.List;

public class Parte2 {
    
    public static void main(String[] args) {
        nomesPorGenero();
    }

    private static void nomesPorGenero() {
        List<String> listaOriginal = new ArrayList<>();
        listaOriginal.add("Ana Flávia-f");
        listaOriginal.add("Rogério-M");
        listaOriginal.add("Fernando-m");
        listaOriginal.add("Brenda-F");
        listaOriginal.add("Carlos-m");
        listaOriginal.add("Pedro-M");
        listaOriginal.add("Maria-f");

        List<String> grupoFeminino = new ArrayList<>();
        List<String> grupoMasculino = new ArrayList<>();

        for (String item : listaOriginal) {
            String[] partes = item.split("-");

            String nome = partes[0];
            String genero = partes[1];

            if (genero.equalsIgnoreCase("f")) {
                grupoFeminino.add(nome);
            } else if (genero.equalsIgnoreCase("m")) {
                grupoMasculino.add(nome);
            }
        }

        System.out.println("*** Grupo Feminino ***");
        grupoFeminino.forEach(System.out::println);

        System.out.println("\n*** Grupo Masculino ***");
        grupoMasculino.forEach(System.out::println);
    }
}
