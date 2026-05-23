package Fabrica;

public class App {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("B","novo");
        Fabricante fabrica = getFabricante(cliente);
        Carro carro = fabrica.criarCarro(cliente.getCodigoSolicitado());
        carro.CompraFechada();
    }

    private static Fabricante getFabricante(Cliente cliente) {
        if(cliente.tipoSolicitado().equalsIgnoreCase("Novo")){
            return new CarrosNovos();
        } else {
            return new CarrosUsados();
        }
    }
}
