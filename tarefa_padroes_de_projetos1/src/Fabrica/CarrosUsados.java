package Fabrica;

public class CarrosUsados extends Fabricante {
    @Override
    Carro tipoDeCarro(String modelo) {
        if("A".equals(modelo)){
            return new Fusca(54,"Álcool","Preto",10000);
        } else if ("B".equals(modelo)){
            return new Celta(60,"Gasolina","Vermelho",12000);
        }
        System.out.println("O modelo solicitado não está disponível, por favor verifique o código solicitado");
        return null;
    }
}
