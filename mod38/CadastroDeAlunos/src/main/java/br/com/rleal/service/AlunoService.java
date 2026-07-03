package br.com.rleal.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.rleal.dao.AlunoDAO;
import br.com.rleal.domain.Aluno;

@Stateless
public class AlunoService {

    @Inject
    private AlunoDAO alunoDAO;

    public void salvar(Aluno aluno) throws Exception {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome do aluno é obrigatório.");
        }
        if (aluno.getCpf() == null || aluno.getCpf().trim().isEmpty()) {
            throw new RuntimeException("O CPF do aluno é obrigatório.");
        }

        Aluno alunoExistente = alunoDAO.buscarPorCpf(aluno.getCpf());
        if (alunoExistente != null) {
            throw new RuntimeException("Já existe um aluno cadastrado com o CPF: " + aluno.getCpf());
        }

        alunoDAO.salvar(aluno);
    }

    public void atualizar(Aluno aluno) throws Exception {
        alunoDAO.atualizar(aluno);
    }

    public void excluir(Aluno aluno) {
        alunoDAO.excluir(aluno);
    }

    public List<Aluno> buscarTodos() {
        return alunoDAO.buscarTodos();
    }
    
    public List<Aluno> buscarPorFiltro(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return alunoDAO.buscarTodos();
        }
        return alunoDAO.buscarPorFiltro(filtro);
    }
}