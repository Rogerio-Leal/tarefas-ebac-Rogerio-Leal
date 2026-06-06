package dao.mocks;

import dao.IContratoDao;

public class ContratoDaoMock implements IContratoDao {

    @Override
    public String salvar() {
        return "Sucesso";
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
