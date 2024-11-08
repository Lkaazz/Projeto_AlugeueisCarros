package aluguelcarro.servlet;

import aluguelcarro.model.dao.ClienteDAO;
import aluguelcarro.model.dto.Cliente;
import aluguelcarro.model.dao.LocacaoDAO;
import aluguelcarro.model.dto.Locacao;
import aluguelcarro.model.dao.VeiculoDAO;
import aluguelcarro.model.dto.Veiculo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/locacoes")
public class LocacaoServlet extends HttpServlet {
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
        LocacaoDAO locacaoDAO = new LocacaoDAO(conn);
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        VeiculoDAO veiculoDAO = new VeiculoDAO(conn);

        try {
            if ("delete".equals(action)) {
                // Excluir locação
                int id = Integer.parseInt(request.getParameter("id"));
                locacaoDAO.excluirLocacao(id);
                response.sendRedirect("locacoes");

            } else {
                // Listar todas as locações
                List<Locacao> locacoes = locacaoDAO.listarLocacoes();
                List<Cliente> clientes = clienteDAO.listarClientes();
                List<Veiculo> veiculos = veiculoDAO.listarVeiculos();

                request.setAttribute("locacoes", locacoes);
                request.setAttribute("clientes", clientes);
                request.setAttribute("veiculos", veiculos);

                request.getRequestDispatcher("locacoes.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao processar ação.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocacaoDAO locacaoDAO = new LocacaoDAO(conn);
        try {
            String id = request.getParameter("id");
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));
            int veiculoId = Integer.parseInt(request.getParameter("veiculoId"));
            Date dataInicio = Date.valueOf(request.getParameter("dataInicio"));
            Date dataFim = Date.valueOf(request.getParameter("dataFim"));

            Locacao locacao = new Locacao(id == null || id.isEmpty() ? 0 : Integer.parseInt(id), clienteId, veiculoId, dataInicio, dataFim);

            if (id == null || id.isEmpty()) {
                locacaoDAO.inserirLocacao(locacao);
            } else {
                locacaoDAO.atualizarLocacao(locacao);
            }

            response.sendRedirect("locacoes");
        } catch (SQLException e) {
            throw new ServletException("Erro ao adicionar ou editar locação.", e);
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
