/**
 * @author rogerio.leal
 */

public class Main {

    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Rogério");
        pessoa1.setCpf(123456789);
        pessoa1.setIdade(35);
        pessoa1.setEndereco("Rua teste");
        System.out.println(pessoa1.getNome());
        System.out.println(pessoa1.getCpf());
        System.out.println(pessoa1.getIdade());
        System.out.println(pessoa1.getEndereco());
    }
}
