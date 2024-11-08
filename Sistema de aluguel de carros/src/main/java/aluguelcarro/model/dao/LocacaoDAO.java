package aluguelcarro.model.dao;

import aluguelcarro.model.dto.Cliente;
import aluguelcarro.model.dto.Locacao;
import aluguelcarro.model.dto.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO {
    private Connection conn;

    public LocacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserirLocacao(Locacao locacao) throws SQLException {
        String sql = "INSERT INTO Locacao (clienteId, veiculoId, dataInicio, dataFim) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getClienteId());
            stmt.setInt(2, locacao.getVeiculoId());
            stmt.setDate(3, locacao.getDataInicio());
            stmt.setDate(4, locacao.getDataFim());
            stmt.executeUpdate();
        }
    }


    public List<Locacao> listarLocacoes() throws SQLException {
        List<Locacao> locacoes = new ArrayList<>();
        String sql = "SELECT l.id AS locacao_id, l.dataInicio, l.dataFim, " +
                     "c.id AS cliente_id, c.nome AS cliente_nome, c.email, c.telefone, " +
                     "v.id AS veiculo_id, v.modelo, v.marca, v.precoDiaria " +
                     "FROM Locacao l " +
                     "JOIN Cliente c ON l.clienteId = c.id " +
                     "JOIN Veiculo v ON l.veiculoId = v.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("cliente_nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                );

                Veiculo veiculo = new Veiculo(
                        rs.getInt("veiculo_id"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getDouble("precoDiaria")
                );

                Locacao locacao = new Locacao(
                        rs.getInt("locacao_id"),
                        cliente,
                        veiculo,
                        rs.getDate("dataInicio"),
                        rs.getDate("dataFim")
                );

                locacoes.add(locacao);
            }
        }
        return locacoes;
    } 

    public void atualizarLocacao(Locacao locacao) throws SQLException {
        String sql = "UPDATE Locacao SET clienteId = ?, veiculoId = ?, dataInicio = ?, dataFim = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, locacao.getClienteId());
            stmt.setInt(2, locacao.getVeiculoId());
            stmt.setDate(3, new java.sql.Date(locacao.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(locacao.getDataFim().getTime()));
            stmt.setInt(5, locacao.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirLocacao(int id) throws SQLException {
        String sql = "DELETE FROM Locacao WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
