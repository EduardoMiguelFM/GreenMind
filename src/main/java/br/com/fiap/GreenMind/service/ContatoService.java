package br.com.fiap.GreenMind.service;

import br.com.fiap.GreenMind.dao.ContatoDao;
import br.com.fiap.GreenMind.model.Contato;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ContatoService {

    private ContatoDao contatoDao;

    public ContatoService() {
        this.contatoDao = new ContatoDao();
    }

    public void salvarContato(Contato contato) throws SQLException {
        contatoDao.salvarContato(contato);
    }

    public List<Contato> listarContatos() throws SQLException {
        return contatoDao.listarContatos();
    }

    public void excluirContato(Long id) throws SQLException {
        contatoDao.excluirContato(id);
    }

    // MÉTODO READ (Buscar Contato por ID)
    public Optional<Contato> buscarContatoPorId(Long id) throws SQLException {
        return contatoDao.buscarContatoPorId(id);
    }

}

