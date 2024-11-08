package aluguelcarro.model.dao;

import aluguelcarro.model.dto.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private Connection conn;

    public VeiculoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserirVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO Veiculo (modelo, marca, precoDiaria) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setDouble(3, veiculo.getPrecoDiaria());
            stmt.executeUpdate();
        }
    }

    public Veiculo buscarVeiculoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Veiculo WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Veiculo(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getDouble("precoDiaria")
                    );
                }
            }
        }
        return null;
    }

    public List<Veiculo> listarVeiculos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM Veiculo";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getDouble("precoDiaria")
                );
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE Veiculo SET modelo = ?, marca = ?, precoDiaria = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setDouble(3, veiculo.getPrecoDiaria());
            stmt.setInt(4, veiculo.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirVeiculo(int id) throws SQLException {
        // excluir locacoes veiculo
        String deleteLocacaoSql = "DELETE FROM Locacao WHERE veiculoId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteLocacaoSql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        // excluir veiculo
        String deleteVeiculoSql = "DELETE FROM Veiculo WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(deleteVeiculoSql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
