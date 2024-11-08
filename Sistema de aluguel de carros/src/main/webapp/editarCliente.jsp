<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Cliente</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<header style="display: flex; justify-content: flex-start; padding: 10px; border-bottom: 1px solid #000;">
        <a href="http://localhost:8080/newProjeto" class="form-submit"><</a>
    </header>
    <h1>Editar Cliente</h1>

    <form action="clientes" method="post" class="form-container">
        <input type="hidden" name="id" value="${cliente.id}">

        <label class="form-label">Nome:</label>
        <input type="text" name="nome" value="${cliente.nome}" class="form-input" required>

        <label class="form-label">Email:</label>
        <input type="email" name="email" value="${cliente.email}" class="form-input" required>

        <label class="form-label">Telefone:</label>
        <input type="text" name="telefone" value="${cliente.telefone}" class="form-input" required>

        <input type="submit" value="Atualizar Cliente" class="form-submit">
    </form>

    <footer>
        <p>Desenvolvido por <a href="https://github.io/carrosvelozes" target="_blank">Leonardo Moraes</a> e <a href="https://github.io/lkaazz" target="_blank">Lucas Carvalho</a></p>
    </footer>
</body>
</html>
