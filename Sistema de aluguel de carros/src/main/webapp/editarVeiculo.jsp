<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Veículo</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header style="display: flex; justify-content: flex-start; padding: 10px; border-bottom: 1px solid #000;">
        <a href="http://localhost:8080/newProjeto" class="form-submit"><</a>
    </header>
    <h1>Editar Veículo</h1>

    <form action="veiculos" method="post" class="form-container">
        <input type="hidden" name="id" value="${veiculo.id}">

        <label class="form-label">Marca:</label>
        <input type="text" name="marca" value="${veiculo.marca}" class="form-input" required>

        <label class="form-label">Modelo:</label>
        <input type="text" name="modelo" value="${veiculo.modelo}" class="form-input" required>

        <label class="form-label">Preço Diária:</label>
        <input type="number" step="0.01" name="precoDiaria" value="${veiculo.precoDiaria}" class="form-input" required>

        <input type="submit" value="Atualizar Veículo" class="form-submit">
    </form>

    <footer>
        <p>Desenvolvido por <a href="https://github.io/carrosvelozes" target="_blank">Leonardo Moraes</a> e <a href="https://github.io/lkaazz" target="_blank">Lucas Carvalho</a></p>
    </footer>
</body>
</html>
