# Sistema de Aluguel de Carros

**Autores:** 
Leonardo Moraes - CP3015777 
Lucas Carvalho - CP3020363

![Screenshot da Tela do Sistema](https://i.imgur.com/FKCMO5n.jpeg)

## Descrição
O Sistema de Aluguel de Carros é uma aplicação web desenvolvida para gerenciar o processo de aluguel de veículos, abrangendo funcionalidades como cadastro de veículos, registro de locações, devoluções e histórico de clientes. A aplicação foi construída seguindo a arquitetura **MVC (Model-View-Controller)**, o que proporciona uma separação clara entre a lógica de apresentação, controle e acesso aos dados, facilitando a manutenção e escalabilidade do projeto.

## Tecnologias Utilizadas
- **Java**: Utilizado para o desenvolvimento da lógica de negócios e backend.
- **JSP e JSTL**: Para construção das páginas dinâmicas que compõem a interface do usuário.
- **Servlets**: Responsáveis por processar as requisições HTTP e gerenciar o fluxo de dados entre a camada de visualização e o modelo.
- **DTO (Data Transfer Objects)**: Estruturas simples para transporte de dados entre as camadas da aplicação.
- **JDBC (Java Database Connectivity)**: Utilizado para a comunicação com o banco de dados.
- **MySQL**: Banco de dados relacional para armazenamento das informações sobre veículos, clientes e locações.

## Arquitetura e Padrões de Projeto
O sistema foi projetado utilizando a arquitetura **MVC**, que separa a aplicação nas seguintes camadas:
- **Model**: Contém as classes de negócio, responsáveis pela representação e manipulação dos dados.
- **View**: Páginas JSP que exibem as informações e capturam interações dos usuários.
- **Controller**: Servlets que recebem as requisições, invocam os métodos do modelo e redirecionam as respostas para a visualização.

## Funcionalidades Implementadas
- **Cadastro de Veículos**: Inserção de novos veículos com informações detalhadas.
- **Cadastro de Clientes**: Registro de clientes com dados de identificação e contato.
- **Locação de Veículos**: Aluguel de veículos com controle de período e valores.
- **Devolução de Veículos**: Registro da devolução de veículos, incluindo status e data.
- **Histórico de Locação**: Registro de locações passadas de cada cliente e veículo.

## Estrutura do Banco de Dados
O banco de dados **MySQL** contém as tabelas principais:
- **Veículos**: ID, modelo, marca, ano, preço e outros detalhes.
- **Clientes**: ID, nome, telefone, e-mail, etc.
- **Locações**: ID da locação, ID do cliente, ID do veículo, datas de início e devolução, valor.

## Desafios e Soluções
- **CRUD com JDBC**: Para garantir a segurança das operações de CRUD e prevenir SQL Injection, foram utilizados **Prepared Statements** para consultas parametrizadas.
- **Gerenciamento de Sessões**: A manutenção do estado da sessão e a navegação adequada entre as páginas exigiram uma abordagem cuidadosa, incluindo a utilização de DTOs e controle de sessões.

## Conclusão
O Sistema de Aluguel de Carros é uma solução robusta para o gerenciamento de locações de veículos. Sua arquitetura organizada e a utilização de tecnologias conhecidas tornam a aplicação eficiente e fácil de manter. Os desafios enfrentados durante o desenvolvimento ajudaram a consolidar boas práticas de programação e segurança no acesso a dados.
