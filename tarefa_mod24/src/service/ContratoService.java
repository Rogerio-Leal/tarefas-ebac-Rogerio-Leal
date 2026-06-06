package service;

import dao.ContratoDao;
import dao.IContratoDao;

public class ContratoService implements IContratoService {

    private IContratoDao contratoDao;

    public ContratoService(IContratoDao dao) {
        this.contratoDao = dao;

    }

    @Override
    public String salvar() {
        return contratoDao.salvar();
    }

    @Override
    public String buscar() {
        return contratoDao.buscar();
    }

    @Override
    public String excluir() {
        return contratoDao.excluir();
    }

    @Override
    public String atualizar() {
        return contratoDao.atualizar();
    }
}
