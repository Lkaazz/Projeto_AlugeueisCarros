package aluguelcarro.model.dao;

import aluguelcarro.model.dto.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    // inserir um cliente
    public void inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, email, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.executeUpdate();
        }
    }

    // buscar cliente por ID
    public Cliente buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                    );
                }
            }
        }
        return null;
    }

    // listar todos os clientes
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // atualizar cliente
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();
        }
    }

    // excluir locações de um cliente
    public void excluirCliente(int id) throws SQLException {
        String sqlDeleteLocacao = "DELETE FROM Locacao WHERE clienteId = ?";
        try (PreparedStatement stmtDeleteLocacao = conn.prepareStatement(sqlDeleteLocacao)) {
            stmtDeleteLocacao.setInt(1, id);
            stmtDeleteLocacao.executeUpdate();
        }

        // excluir o cliente
        String sqlDeleteCliente = "DELETE FROM Cliente WHERE id = ?";
        try (PreparedStatement stmtDeleteCliente = conn.prepareStatement(sqlDeleteCliente)) {
            stmtDeleteCliente.setInt(1, id);
            stmtDeleteCliente.executeUpdate();
        }
    }
}
