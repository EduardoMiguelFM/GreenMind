package br.com.fiap.GreenMind.dao;

import br.com.fiap.GreenMind.factory.ConnectionFactory;
import br.com.fiap.GreenMind.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDao {

    // MÉTODO CREATE (Salvar Categoria)
    public void salvarCategoria(Categoria categoria) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "INSERT INTO categorias (nome_cat) VALUES (?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, categoria.getNome());
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }

    // MÉTODO GET (Listar Categorias)
    public List<Categoria> listarCategorias() throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "SELECT * FROM categorias";

        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Categoria> categorias = new ArrayList<>();
        while (rs.next()) {
            categorias.add(new Categoria(
                    rs.getLong("id_cat"),
                    rs.getString("nome_cat")
            ));
        }

        rs.close();
        stmt.close();
        ConnectionFactory.fecharConexao(connection);
        return categorias;
    }

    // MÉTODO GET (Listar Categorias por Id)
    public Optional<Categoria> buscarCategoriaPorId(Long id) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "SELECT * FROM categorias WHERE id_cat = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Categoria categoria = new Categoria(
                    rs.getLong("id_cat"),
                    rs.getString("nome_cat")
            );
            rs.close();
            stmt.close();
            ConnectionFactory.fecharConexao(connection);
            return Optional.of(categoria);
        }

        rs.close();
        stmt.close();
        ConnectionFactory.fecharConexao(connection);
        return Optional.empty();
    }

    // MÉTODO PUT (Alterar Categorias)
    public void alterarCategoria(Categoria categoria) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "UPDATE categorias SET nome_cat = ? WHERE id_cat\" = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, categoria.getNome());
        stmt.setLong(2, categoria.getId());
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }

    // MÉTODO DELETE (Excluir Categorias)
    public void excluirCategoria(Long id) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();
        String sql = "DELETE FROM categorias WHERE id_cat\" = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        ConnectionFactory.fecharConexao(connection);
    }

    // MÉTODO PARA BUSCAR O ID DA CATEGORIA PELO NOME
    public Optional<Long> buscarIdPorNome(String nomeCategoria) throws SQLException {
        Connection connection = ConnectionFactory.obterConexao();

        // Verifique se o nome correto da coluna é "nome_cat"
        String sql = "SELECT id_cat FROM categorias WHERE nome_cat = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nomeCategoria);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Long idCat = rs.getLong("id_cat");
            rs.close();
            stmt.close();
            ConnectionFactory.fecharConexao(connection);
            return Optional.of(idCat);
        }

        rs.close();
        stmt.close();
        ConnectionFactory.fecharConexao(connection);
        return Optional.empty(); // Retorna vazio se não encontrar a categoria
    }
}



