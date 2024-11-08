<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Veículos</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header style="display: flex; justify-content: flex-start; padding: 10px; border-bottom: 1px solid #000;">
        <a href="http://localhost:8080/newProjeto" class="form-submit"><</a>
    </header>
    <h1>Cadastro de Veículos</h1>
    
    <form action="veiculos" method="post" class="form-container">
        <label class="form-label">Marca:</label>
        <input type="text" name="marca" class="form-input" required>
        
        <label class="form-label">Modelo:</label>
        <input type="text" name="modelo" class="form-input" required>
        
        <label class="form-label">Preço Diária:</label>
        <input type="number" step="0.01" name="precoDiaria" class="form-input" required>
        
        <input type="submit" value="Adicionar Veículo" class="form-submit">
    </form>

    <h2>Lista de Veículos</h2>
    <table class="table-container">
        <tr>
            <th>ID</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Preço por Dia</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="veiculo" items="${veiculos}">
            <tr>
                <td>${veiculo.id}</td>
                <td>${veiculo.marca}</td>
                <td>${veiculo.modelo}</td>
                <td>${veiculo.precoDiaria}</td>
                <td>
                    <a href="veiculos?action=edit&id=${veiculo.id}">Editar</a> | 
                    <a href="veiculos?action=delete&id=${veiculo.id}" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <footer>
        <p>Desenvolvido por <a href="https://github.io/carrosvelozes" target="_blank">Leonardo Moraes</a> e <a href="https://github.io/lkaazz" target="_blank">Lucas Carvalho</a></p>
    </footer>
</body>
</html>
