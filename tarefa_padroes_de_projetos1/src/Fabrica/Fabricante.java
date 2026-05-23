package Fabrica;

public abstract class Fabricante {
    public Carro criarCarro(String modelo){
        Carro carro = tipoDeCarro(modelo);
        return carro;
    }

    abstract Carro tipoDeCarro(String modelo);
}