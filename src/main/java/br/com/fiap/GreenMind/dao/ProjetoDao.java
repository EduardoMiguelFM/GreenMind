package br.com.fiap.GreenMind.dao;

import br.com.fiap.GreenMind.factory.ConnectionFactory;
import br.com.fiap.GreenMind.model.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoDao {

    // MÉTODO CREATE (Salvar Projeto)
    public void salvarProjeto(Projeto projeto) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "INSERT INTO projetos (nome_proj, descricao, detalhes, imagem_url, categoria_id, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, projeto.getNomeProj());
        stmt.setString(2, projeto.getDescricao());
        stmt.setString(3, projeto.getDetalhes());
        stmt.setString(4, projeto.getImagemUrl());
        stmt.setString(5, projeto.getCategoriaId());
        stmt.setDate(6, new java.sql.Date(System.currentTimeMillis())); // Data atual
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }

    // MÉTODO READ (Listar todos os Projetos)
    public List<Projeto> listarProjetos() throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "SELECT p.id_proj, p.nome_proj, p.descricao, p.detalhes, p.imagem_url, c.nome_cat AS nome_categoria, p.data_criacao " +
                "FROM projetos p " +
                "JOIN categorias c ON p.categoria_id = c.id_cat";

        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Projeto> projetos = new ArrayList<>();
        while (rs.next()) {
            Projeto projeto = new Projeto();
            projeto.setIdProj(rs.getLong("id_proj"));
            projeto.setNomeProj(rs.getString("nome_proj"));
            projeto.setDescricao(rs.getString("descricao"));
            projeto.setDetalhes(rs.getString("detalhes"));
            projeto.setImagemUrl(rs.getString("imagem_url"));
            projeto.setCategoriaId(rs.getString("nome_categoria"));
            projeto.setDataCriacao(rs.getDate("data_criacao"));
            projetos.add(projeto);
        }

        rs.close();
        stmt.close();
        ConnectionFactory.fecharConexao(connection);

        return projetos;
    }

    // MÉTODO READ (Buscar Projeto por ID)
    public Optional<Projeto> buscarProjetoPorId(Long id) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "SELECT * FROM projetos WHERE id_proj = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Projeto projeto = new Projeto(
                    rs.getLong("id_proj"),
                    rs.getString("nome_proj"),
                    rs.getString("descricao"),
                    rs.getString("detalhes"),
                    rs.getString("imagem_url"),
                    rs.getString("categoria_id"),
                    rs.getDate("data_criacao")
            );
            rs.close();
            stmt.close();
            ConnectionFactory.fecharConexao(connection);
            return Optional.of(projeto); // Retorna o projeto encontrado.
        }

        rs.close();
        stmt.close();
        ConnectionFactory.fecharConexao(connection);
        return Optional.empty(); // Retorna vazio se o projeto não for encontrado.
    }

    // MÉTODO UPDATE (Alterar Projeto)
    public void alterarProjeto(Projeto projeto) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "UPDATE projetos SET nome_proj = ?, descricao = ?, detalhes = ?, imagem_url = ?, categoria_id = ? WHERE id_proj = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, projeto.getNomeProj());
        stmt.setString(2, projeto.getDescricao());
        stmt.setString(3, projeto.getDetalhes());
        stmt.setString(4, projeto.getImagemUrl());
        stmt.setString(5, projeto.getCategoriaId());
        stmt.setLong(6, projeto.getIdProj()); // Identifica o registro pelo ID.
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }

    // MÉTODO DELETE (Excluir Projeto)
    public void excluirProjeto(Long id) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "DELETE FROM projetos WHERE id_proj = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id); // Configura o ID do projeto a ser excluído.
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }
}
