/**
 * @author rogerio.leal
 */

public class CalcularResultado {

    public static void main(String[] args) {
        media();
    }

    private static void media() {
        System.out.println("*** Resultado ***");

        double nota1 = 7;
        double nota2 = 5.5;
        double nota3 = 6.5;
        double nota4 = 5;

        double media = (nota1 + nota2 + nota3 + nota4) /4;
        System.out.println("A média é: " + media);
    }
}



