package br.com.fiap.GreenMind.service;

import br.com.fiap.GreenMind.dao.CategoriaDao;
import br.com.fiap.GreenMind.model.Categoria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaService {

    private CategoriaDao categoriaDao;

    public CategoriaService() {
        this.categoriaDao = new CategoriaDao();
    }

    public void salvarCategoria(Categoria categoria) throws SQLException {
        categoriaDao.salvarCategoria(categoria);
    }

    public void alterarCategoria(Categoria categoria) throws SQLException {
        Optional<Categoria> categoriaExistenteOpt = categoriaDao.buscarCategoriaPorId(categoria.getId());

        if (categoriaExistenteOpt.isEmpty()) {
            throw new SQLException("Categoria não encontrada para atualização");
        }

        categoriaDao.alterarCategoria(categoria);
    }

    public List<Categoria> listarCategorias() throws SQLException {
        return categoriaDao.listarCategorias();
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) throws SQLException {
        return categoriaDao.buscarCategoriaPorId(id);
    }

    public void excluirCategoria(Long id) throws SQLException {
        categoriaDao.excluirCategoria(id);
    }
}

