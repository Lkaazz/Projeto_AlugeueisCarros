package aluguelcarro.servlet;

import aluguelcarro.model.dao.ClienteDAO;
import aluguelcarro.model.dto.Cliente;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aluguel_carros", "root", "@coxinha2");
        } catch (Exception e) {
            throw new ServletException("Não foi possível conectar ao banco de dados.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ClienteDAO clienteDAO = new ClienteDAO(conn);

        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Cliente cliente = clienteDAO.buscarClientePorId(id);
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                clienteDAO.excluirCliente(id);
                response.sendRedirect("clientes");
            } else {
                request.setAttribute("clientes", clienteDAO.listarClientes());
                request.getRequestDispatcher("clientes.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao processar ação.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDAO clienteDAO = new ClienteDAO(conn);

        try {
            String idParam = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");

            int id = (idParam == null || idParam.isEmpty()) ? 0 : Integer.parseInt(idParam);
            Cliente cliente = new Cliente(id, nome, email, telefone);

            if (id == 0) {
                clienteDAO.inserirCliente(cliente);
            } else {
                clienteDAO.atualizarCliente(cliente);
            }

            response.sendRedirect("clientes");
        } catch (SQLException e) {
            throw new ServletException("Erro ao adicionar ou editar cliente.", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
