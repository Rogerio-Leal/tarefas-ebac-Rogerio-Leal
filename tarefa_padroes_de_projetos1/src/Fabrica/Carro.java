package Fabrica;

public abstract class Carro {

    private Integer potencia;

    private String combustivel;

    private String cor;

    private Integer preco;

    public Carro(Integer potencia, String combustivel, String cor, Integer preco) {
        this.potencia = potencia;
        this.combustivel = combustivel;
        this.cor = cor;
        this.preco = preco;
    }

    public void CompraFechada(){
        System.out.println("Resumo da compra:");
        System.out.println("Modelo: " + getClass().getSimpleName());
        System.out.println("Cor: " + cor);
        System.out.println("Preço: R$" + preco);
    }
}
