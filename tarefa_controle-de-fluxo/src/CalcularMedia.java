import java.util.Scanner;

public class CalcularMedia {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("--- Calculadora de Média ---");

        System.out.print("\nDigite a 1ª nota: ");
        int nota1 = s.nextInt();

        System.out.print("Digite a 2ª nota: ");
        int nota2 = s.nextInt();

        System.out.print("Digite a 3ª nota: ");
        int nota3 = s.nextInt();

        System.out.print("Digite a 4ª nota: ");
        int nota4 = s.nextInt();

        int media = (nota1 + nota2 + nota3 + nota4) / 4;
        System.out.println("\nMédia final: " + media);

        resultado(media);
        }

    public static void resultado(int media){
        if (media >= 7) {
            System.out.println("Aprovado!");
        } else if (media >= 5) {
            System.out.println("Em recuperação!");
        } else {
            System.out.println("Reprovado!");
        }
    }
}
