package br.com.fiap.GreenMind.dao;

import br.com.fiap.GreenMind.factory.ConnectionFactory;
import br.com.fiap.GreenMind.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    // MÉTODO CREATE (Salvar Contato)
    public void salvarContato(Contato contato) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "INSERT INTO contatos (nome_contato, email_contato, telefone_contato, mensagem, data_envio) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, contato.getNomeContato());
        stmt.setString(2, contato.getEmailContato());
        stmt.setString(3, contato.getTelefoneContato());
        stmt.setString(4, contato.getMensagem());
        stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));

        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }

    // MÉTODO READ (Listar todos os Contatos)
    public List<Contato> listarContatos() throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "SELECT * FROM contatos";

        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Contato> contatos = new ArrayList<>();
        while (rs.next()) {
            contatos.add(new Contato(
                    rs.getLong("id_contato"),
                    rs.getString("nome_contato"),
                    rs.getString("email_contato"),
                    rs.getString("telefone_contato"),
                    rs.getString("mensagem"),
                    rs.getDate("data_envio")
            ));
        }

        rs.close();
        stmt.close();
        ConnectionFactory.fecharConexao(connection);
        return contatos;
    }

    // MÉTODO DELETE (Excluir Contato)
    public void excluirContato(Long id) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "DELETE FROM contatos WHERE id_contato = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }
}
