package Fabrica;

public class CarrosNovos extends Fabricante {

    @Override
    Carro tipoDeCarro(String modelo) {
        if("A".equals(modelo)) {
            return new Outlander(252,"gasolina","preto",379000);
        } else if ("B".equals(modelo)) {
            return new Dolphin(95,"Elétrico","Branco",119000);
        }
        System.out.println("O modelo solicitado não está disponível, por favor verifique o código solicitado");
        return null;
    }
}
