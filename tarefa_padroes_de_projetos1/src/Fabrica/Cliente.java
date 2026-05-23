package Fabrica;

public class Cliente {

    private String codigoSolicitado;

    private String tipoSolicitado;

    public Cliente(String codigoSolicitado, String tipoSolicitado) {
        this.codigoSolicitado = codigoSolicitado;
        this.tipoSolicitado = tipoSolicitado;
    }

    public String tipoSolicitado() {
        return tipoSolicitado;
    }
    public String getCodigoSolicitado() {
        return codigoSolicitado;
    }
}
