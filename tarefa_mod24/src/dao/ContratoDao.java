package dao;

public class ContratoDao implements IContratoDao {

    @Override
    public String salvar() {
        throw new UnsupportedOperationException("Não funciona o banco");
    }

    @Override
    public String buscar() {
        return "Buscando";
    }

    @Override
    public String excluir() {
        return "Excluido";
    }

    @Override
    public String atualizar() {
        return "Atualizado";
    }
}
