<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Locação</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
</head>
<body>
<header style="display: flex; justify-content: flex-start; padding: 10px; border-bottom: 1px solid #000;">
        <a href="http://localhost:8080/newProjeto" class="form-submit"><</a>
    </header>
    <h1>Editar Locação</h1>

    <form action="locacoes" method="post">
        <input type="hidden" name="id" value="${locacao.id}">

        <label>Cliente:</label>
        <select name="clienteId">
            <c:forEach var="cliente" items="${clientes}">
                <option value="${cliente.id}" ${cliente.id == locacao.cliente.id ? 'selected' : ''}>${cliente.nome}</option>
            </c:forEach>
        </select>

        <label>Veículo:</label>
        <select name="veiculoId">
            <c:forEach var="veiculo" items="${veiculos}">
                <option value="${veiculo.id}" ${veiculo.id == locacao.veiculo.id ? 'selected' : ''}>${veiculo.modelo}</option>
            </c:forEach>
        </select>

        <label>Data Início:</label>
        <input type="date" name="dataInicio" value="${locacao.dataInicio}" required>

        <label>Data Fim:</label>
        <input type="date" name="dataFim" value="${locacao.dataFim}" required>

        <input type="submit" value="Salvar">
    </form>

    <footer>
        <p>Desenvolvido por <a href="https://github.io/carrosvelozes" target="_blank">Leonardo Moraes</a> e <a href="https://github.io/lkaazz" target="_blank">Lucas Carvalho</a></p>
    </footer>
</body>
</html>
