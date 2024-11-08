package aluguelcarro.servlet;

import aluguelcarro.model.dao.VeiculoDAO;
import aluguelcarro.model.dto.Veiculo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/veiculos")
public class VeiculoServlet extends HttpServlet {
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
        VeiculoDAO veiculoDAO = new VeiculoDAO(conn);

        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Veiculo veiculo = veiculoDAO.buscarVeiculoPorId(id);
                request.setAttribute("veiculo", veiculo);
                request.getRequestDispatcher("editarVeiculo.jsp").forward(request, response);

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                veiculoDAO.excluirVeiculo(id);
                response.sendRedirect("veiculos");

            } else {
                request.setAttribute("veiculos", veiculoDAO.listarVeiculos());
                request.getRequestDispatcher("veiculos.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao processar ação.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VeiculoDAO veiculoDAO = new VeiculoDAO(conn);

        try {
            String id = request.getParameter("id");
            String modelo = request.getParameter("modelo");
            String marca = request.getParameter("marca");
            double precoDiaria = Double.parseDouble(request.getParameter("precoDiaria"));

            Veiculo veiculo = new Veiculo(id == null ? 0 : Integer.parseInt(id), modelo, marca, precoDiaria);

            if (id == null || id.isEmpty()) {
                veiculoDAO.inserirVeiculo(veiculo);
            } else {
                veiculoDAO.atualizarVeiculo(veiculo);
            }

            response.sendRedirect("veiculos");
        } catch (SQLException e) {
            throw new ServletException("Erro ao adicionar ou editar veiculo.", e);
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
