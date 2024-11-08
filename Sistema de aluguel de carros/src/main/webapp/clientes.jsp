<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <header style="display: flex; justify-content: flex-start; padding: 10px; border-bottom: 1px solid #000;">
        <a href="http://localhost:8080/newProjeto" class="form-submit"><</a>
    </header>

    <h1>Cadastro de Clientes</h1>

    <form action="clientes" method="post" class="form-container">
        <label class="form-label">Nome:</label>
        <input type="text" name="nome" class="form-input" required>

        <label class="form-label">Email:</label>
        <input type="email" name="email" class="form-input" required>

        <label class="form-label">Telefone:</label>
        <input type="text" name="telefone" class="form-input" required>

        <input type="submit" value="Adicionar Cliente" class="form-submit">
    </form>

    <h2>Lista de Clientes</h2>
    <table class="table-container">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="cliente" items="${clientes}">
            <tr>
                <td>${cliente.id}</td>
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefone}</td>
                <td>
                    <a href="clientes?action=edit&id=${cliente.id}">Editar</a> |
                    <a href="clientes?action=delete&id=${cliente.id}" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <footer>
        <p>Desenvolvido por <a href="https://github.io/carrosvelozes" target="_blank">Leonardo Moraes</a> e <a href="https://github.io/lkaazz" target="_blank">Lucas Carvalho</a></p>
    </footer>
</body>
</html>
