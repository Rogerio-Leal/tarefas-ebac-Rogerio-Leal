public abstract class Carro {

    private String nome;

    private String cor;

    private Integer preco;

    private Integer codigo;

    public Carro(String nome, String cor, Integer preco, Integer codigo) {
        this.nome = nome;
        this.cor = cor;
        this.preco = preco;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                ", cor='" + cor + '\'' +
                ", preco=" + preco +
                ", codigo=" + codigo +
                '}';
    }
}
