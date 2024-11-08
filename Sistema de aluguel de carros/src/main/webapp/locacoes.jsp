<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Locações</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header style="display: flex; justify-content: flex-start; padding: 10px; border-bottom: 1px solid #000;">
        <a href="http://localhost:8080/newProjeto" class="form-submit"><</a>
    </header>
    <h1>Cadastro de Locações</h1>

    <form action="locacoes" method="post" class="form-container">
        <label class="form-label">Cliente:</label>
        <select name="clienteId" class="form-input" required>
            <c:forEach var="cliente" items="${clientes}">
                <option value="${cliente.id}">${cliente.nome}</option>
            </c:forEach>
        </select>

        <label class="form-label">Veículo:</label>
        <select name="veiculoId" class="form-input" required>
            <c:forEach var="veiculo" items="${veiculos}">
                <option value="${veiculo.id}">${veiculo.modelo}</option>
            </c:forEach>
        </select>

        <label class="form-label">Data Início:</label>
        <input type="date" name="dataInicio" class="form-input" required>

        <label class="form-label">Data Fim:</label>
        <input type="date" name="dataFim" class="form-input" required>

        <input type="submit" value="Adicionar Locação" class="form-submit">
    </form>

    <h2>Lista de Locações</h2>
    <table class="table-container">
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Veículo</th>
            <th>Data Início</th>
            <th>Data Fim</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="locacao" items="${locacoes}">
            <tr>
                <td>${locacao.id}</td>
                <td>${locacao.cliente.nome}</td>
                <td>${locacao.veiculo.modelo}</td>
                <td>${locacao.dataInicio}</td>
                <td>${locacao.dataFim}</td>
                <td>
                    <a href="locacoes?action=delete&id=${locacao.id}" onclick="return confirm('Tem certeza que deseja excluir esta locação?');">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <footer>
        <p>Desenvolvido por <a href="https://github.io/carrosvelozes" target="_blank">Leonardo Moraes</a> e <a href="https://github.io/lkaazz" target="_blank">Lucas Carvalho</a></p>
    </footer>
</body>
</html>
