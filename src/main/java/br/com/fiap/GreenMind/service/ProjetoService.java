package br.com.fiap.GreenMind.service;

import br.com.fiap.GreenMind.dao.CategoriaDao;
import br.com.fiap.GreenMind.dao.ProjetoDao;
import br.com.fiap.GreenMind.dto.ProjetoDto;
import br.com.fiap.GreenMind.model.Projeto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjetoService {

    private ProjetoDao projetoDao;

    public ProjetoService() {
        this.projetoDao = new ProjetoDao();
    }

    // MÉTODO CREATE (Salvar Projeto)
    public void salvarProjeto(Projeto projeto, String nomeCategoria) throws SQLException {
        CategoriaDao categoriaDao = new CategoriaDao();
        Optional<Long> categoriaIdOpt = categoriaDao.buscarIdPorNome(nomeCategoria);
        if (categoriaIdOpt.isEmpty()) {
            throw new IllegalArgumentException("Categoria não encontrada: " + nomeCategoria);
        }
        projeto.setCategoriaId(String.valueOf(categoriaIdOpt.get()));

        projetoDao.salvarProjeto(projeto);
    }


    // MÉTODO UPDATE (Alterar Projeto)
    public void alterarProjeto(ProjetoDto dto, Long id) throws SQLException {
        Optional<Projeto> projetoOpt = projetoDao.buscarProjetoPorId(id);

        if (projetoOpt.isPresent()) {
            Projeto projeto = projetoOpt.get();

            // Atualiza apenas os campos fornecidos no DTO
            if (dto.getNomeProj() != null) projeto.setNomeProj(dto.getNomeProj());
            if (dto.getDescricao() != null) projeto.setDescricao(dto.getDescricao());
            if (dto.getImagemUrl() != null) projeto.setImagemUrl(dto.getImagemUrl());
            if (dto.getCategoriaId() != null) projeto.setCategoriaId(dto.getCategoriaId());

            projetoDao.alterarProjeto(projeto);
        } else {
            throw new SQLException("Projeto não encontrado");
        }
    }

    // MÉTODO GET (Listar todos os Projetos)
    public List<Projeto> listarProjetos() throws SQLException {
        return projetoDao.listarProjetos();
    }

    // MÉTODO GET(Buscar Projeto por ID)
    public Optional<Projeto> buscarProjetoPorId(Long id) throws SQLException {
        return projetoDao.buscarProjetoPorId(id);
    }

    // MÉTODO DELETE (Excluir Projeto)
    public void excluirProjeto(Long id) throws SQLException {
        projetoDao.excluirProjeto(id);
    }
}
