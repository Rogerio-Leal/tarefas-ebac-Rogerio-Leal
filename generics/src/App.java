import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        Map<Integer, Carro> listaDeCarros = new HashMap<>();

        Carro escortXr = new EscortXr("EscortXr","azul",10000, 1);
        Carro civic = new Civic("Civic","vermelho",260000, 2);
        Carro lancer = new Lancer("Lancer","preto",200000, 3);

        listaDeCarros.put(1, escortXr);
        listaDeCarros.put(2, civic);
        listaDeCarros.put(3, lancer);

        listaDeCarros.forEach((id, carro) -> {
            System.out.println("\nCódigo do carro: " + id);
            System.out.println("Detalhes: " + carro);
        });
    }
}
