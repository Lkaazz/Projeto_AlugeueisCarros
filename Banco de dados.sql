DROP SCHEMA IF EXISTS aluguel_carros;
CREATE SCHEMA IF NOT EXISTS aluguel_carros;
USE aluguel_carros;

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE Veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    precoDiaria DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Locacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clienteId INT NOT NULL,
    veiculoId INT NOT NULL,
    dataInicio DATE NOT NULL,
    dataFim DATE NOT NULL,
    FOREIGN KEY (clienteId) REFERENCES Cliente(id),
    FOREIGN KEY (veiculoId) REFERENCES Veiculo(id)
);

INSERT INTO Cliente (nome, email, telefone) VALUES 
('João Silva', 'joao.silva@email.com', '11987654321'),
('Maria Oliveira', 'maria.oliveira@email.com', '11965432198'),
('Carlos Pereira', 'carlos.pereira@email.com', '11974325678'),
('Ana Souza', 'ana.souza@email.com', '11983654721'),
('Lucas Lima', 'lucas.lima@email.com', '11992765432'),
('Mariana Santos', 'mariana.santos@email.com', '11962847391'),
('Pedro Gonçalves', 'pedro.goncalves@email.com', '11951827654'),
('Fernanda Costa', 'fernanda.costa@email.com', '11943982671'),
('Gabriel Alves', 'gabriel.alves@email.com', '11930495826'),
('Juliana Rocha', 'juliana.rocha@email.com', '11982947561');

INSERT INTO Veiculo (modelo, marca, precoDiaria) VALUES 
('Civic', 'Honda', 150.00),
('Corolla', 'Toyota', 160.00),
('Gol', 'Volkswagen', 100.00),
('Uno', 'Fiat', 90.00),
('Ka', 'Ford', 110.00),
('Onix', 'Chevrolet', 120.00),
('HB20', 'Hyundai', 130.00),
('Ranger', 'Ford', 200.00),
('Tiguan', 'Volkswagen', 250.00),
('Compass', 'Jeep', 300.00);

INSERT INTO Locacao (clienteId, veiculoId, dataInicio, dataFim) VALUES 
(1, 1, '2024-11-01', '2024-11-05'),
(2, 3, '2024-10-20', '2024-10-22'),
(3, 5, '2024-11-03', '2024-11-06'),
(4, 2, '2024-10-15', '2024-10-18'),
(5, 7, '2024-11-02', '2024-11-04');
