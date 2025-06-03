-- Beatriz Vaz Pedroso Santos Cobral
-- Felipe Silva Loschi

SET SQL_SAFE_UPDATES = 0;
DROP DATABASE IF EXISTS Padrinhos_Magicos;
CREATE DATABASE IF NOT EXISTS Padrinhos_Magicos;
USE Padrinhos_Magicos;

-- Tabela: Crianca
CREATE TABLE IF NOT EXISTS Crianca (
    idCrianca INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nomeCrianca VARCHAR(45),
    idadeCrianca INT,
    sexoCrianca VARCHAR(45),
    temPadrinho BOOLEAN,
    enderecoCrianca VARCHAR(45)
);

-- Tabela: Varinha
CREATE TABLE IF NOT EXISTS Varinha (
    idSerial INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    varinhaCor VARCHAR(45),
    statusVarinha VARCHAR(45)
);

-- Tabela: Padrinhos
CREATE TABLE IF NOT EXISTS Padrinhos (
    idPadrinhos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nomePadrinho VARCHAR(45),
    tipoPadrinho VARCHAR(45),
    Varinha_idSerial INT,
    Crianca_idCrianca INT,
    FOREIGN KEY (Varinha_idSerial) REFERENCES Varinha(idSerial) ON DELETE CASCADE,
    FOREIGN KEY (Crianca_idCrianca) REFERENCES Crianca(idCrianca) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Tabela: Desejos
CREATE TABLE IF NOT EXISTS Desejos (
    idDesejos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(50),
    statusDesejo VARCHAR(45)
);

-- Tabela: CriancaFazDesejos
CREATE TABLE IF NOT EXISTS CriancaFazDesejos (
    Crianca_idCrianca INT,
    Desejos_idDesejos INT,
    PRIMARY KEY (Crianca_idCrianca, Desejos_idDesejos),
    FOREIGN KEY (Crianca_idCrianca) REFERENCES Crianca(idCrianca) ON DELETE CASCADE,
    FOREIGN KEY (Desejos_idDesejos) REFERENCES Desejos(idDesejos) ON DELETE CASCADE
);

-- Tabela: Magia 
CREATE TABLE IF NOT EXISTS Magia (
    idMagia INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nomeMagia VARCHAR(45),
    descricaoMagia VARCHAR(45),
    Padrinhos_idPadrinhos INT,
    FOREIGN KEY (Padrinhos_idPadrinhos) REFERENCES Padrinhos(idPadrinhos) ON DELETE CASCADE
);
-- ---------------------------------------- TRIGGER ----------------------------------------------
/*
*	Optamos por usar um trigger para impedir que uma criança tenha mais de um padrinhos do mesmo
*tipo, pensando no desenho que, no máximo, o Timmy Turner tinha dois padrinhos, mas um era o
*Cosmo e a outra a Wanda. Pensamos em manter essa mesma consistência, no máximo um padrinho de
*cada tipo.
*/
DELIMITER $$
CREATE TRIGGER verificaPadrinho
AFTER INSERT ON Padrinhos
FOR EACH ROW
BEGIN
    IF (
        SELECT COUNT(*) 
        FROM Padrinhos
        WHERE Crianca_idCrianca = NEW.Crianca_idCrianca
        AND tipoPadrinho = NEW.tipoPadrinho
    ) > 1 THEN
        DELETE FROM Padrinhos WHERE idPadrinhos = NEW.idPadrinhos;
    END IF;
END$$
DELIMITER ;
-- ---------------------------------- Inserções -------------------------------------------------
-- Inserção para tabela Criança
INSERT INTO Crianca (nomeCrianca, idadeCrianca, sexoCrianca, temPadrinho, enderecoCrianca) VALUES
('Timmy Turner', 10, 'Masculino', 1, 'Rua dos Desejos, 123'),
('Chloe Carmichael', 10, 'Feminino', 1, 'Av. Das Fadas, 45'),
('A.J.', 10, 'Masculino', 0, 'Rua do Laboratório, 77'),
('Chester McBadbat', 10, 'Masculino', 0, 'Beco das Meias, 88'),
('Tootie', 9, 'Feminino', 1, 'Rua da Vizinhança, 5'),
('Veronica', 11, 'Feminino', 1, 'Alameda dos Sonhos, 202'),
('Jimmy Neutron', 10, 'Masculino', 1, 'Rua da Ciência, 33'),
('Cindy Vortex', 10, 'Feminino', 0, 'Avenida Inteligente, 77'),
('Sheen Estevez', 9, 'Masculino', 1, 'Travessa dos Super-Heróis, 99');

-- Inserção para tabela Varinha
INSERT INTO Varinha (varinhaCor, statusVarinha) VALUES
('Rosa', 'Funcionando'),
('Azul', 'Funcionando'),
('Verde', 'Em manutenção'),
('Dourada', 'Funcionando'),
('Roxa','Quebrada'),
('Arco-íris', 'Funcionando'),
('Prata', 'Em manutenção'),
('Negra', 'Funcionando'),
('Branca', 'Quebrada');

-- Inserção para tabela Padrinho
INSERT INTO Padrinhos (nomePadrinho, tipoPadrinho, Varinha_idSerial, Crianca_idCrianca) VALUES

('Cosmo', 'Padrinho', 2, 1),
('Wanda', 'Madrinha', 1, 1),
('Juandíssimo', 'Padrinho', 4, 2),
('Norm', 'Padrinho', 5, 5),
('ChloePadrinho', 'Madrinha', 3, 2),
('Sparky', 'Padrinho', 6, 6),
('Vicky', 'Madrinha', 7, 7),
('Professor Calamitous', 'Padrinho', 8, 8),
('Ultra Lord', 'Padrinho', 9, 9);

-- Inserção para tabela Desejos
INSERT INTO Desejos (descricao, statusDesejo) VALUES
('Quero um lanche infinito', 'Concedido'),
('Quero virar um super-herói', 'Concedido'),
('Quero férias eternas', 'Negado'),
('Quero voar', 'Concedido'),
('Quero um castelo','Pendente'),
('Quero ser o mais inteligente da escola', 'Pendente'),
('Quero um robô ajudante', 'Concedido'),
('Quero entender as meninas', 'Negado'),
('Quero ter superpoderes', 'Concedido'),
('Quero um cachorro falante', 'Pendente');

-- Inserção para tabela CriançaFazDesejos
INSERT INTO CriancaFazDesejos (Crianca_idCrianca, Desejos_idDesejos) VALUES
(1,1),
(1,2),
(2,5),
(5,4),
(1,3),
(6,6),
(7,7),
(7,8),
(8,9),
(9,10),
(6,4);

-- Inserção para tabela Magia
INSERT INTO Magia (nomeMagia, descricaoMagia, Padrinhos_idPadrinhos) VALUES
('Transmutação', 'Transformar objetos em outros',1),
('Invisibilidade', 'Ficar invisível por 1 hora',2),
('Clone', 'Criar cópias do usuário',3),
('Controle do tempo', 'Avançar ou voltar no tempo',4),
('Levitação', 'Fazer objetos flutuarem',5),
('Super Inteligência', 'Aumenta QI temporariamente',6),
('Tecnocriação', 'Criar dispositivos tecnológicos',7),
('Manipulação Emocional', 'Controlar emoções alheias',8),
('Força Cósmica', 'Poderes sobre-humanos',9),
('Comunicação Animal', 'Falar com animais',6);
-- Checando as inserções:
SELECT * FROM Crianca;
SELECT * FROM Varinha;
SELECT * FROM Padrinhos;
SELECT * FROM Desejos;
SELECT * FROM Magia;
SELECT * FROM CriancaFazDesejos;
-- -------------------------- Atualizações -------------------------------------
-- Atualizações para a tabela Criança
UPDATE Crianca SET temPadrinho = 0 WHERE idCrianca = 2;
UPDATE Crianca SET temPadrinho = 1 WHERE idCrianca = 3;

-- Atualizações para a tabela Varinha
UPDATE Varinha SET statusVarinha = 'Funcionando' WHERE idSerial = 3;
UPDATE Varinha SET varinhaCor = 'Prateada' WHERE idSerial = 5;

-- Atualizações para a tabela Padrinhos
UPDATE Padrinhos SET tipoPadrinho = 'Madrinha' WHERE idPadrinhos = 4;
UPDATE Padrinhos SET nomePadrinho = 'Chloe Madrinha' WHERE idPadrinhos = 5;

-- Atualizações para a tabela Desejos
UPDATE Desejos SET statusDesejo = 'Concedido' WHERE idDesejos = 5;
UPDATE Desejos SET descricao = 'Quero um castelo de chocolate' WHERE idDesejos = 5;

-- Atualizações para a tabela Magia
UPDATE Magia SET descricaoMagia = 'Transformar objetos em outros por 24h' WHERE idMagia = 1;
UPDATE Magia SET nomeMagia = 'Super Levitação' WHERE idMagia = 5;

-- ------------------------------------ Exclusões ------------------------------------------
-- Exclusão para tabela Criança (irá automaticamente excluir registros relacionados em Padrinhos e CriancaFazDesejos)
DELETE FROM Crianca WHERE nomeCrianca = "Chester McBadbat";
DELETE FROM Crianca WHERE nomeCrianca = "Tootie";

-- Exclusões para tabela Varinha (irá automaticamente excluir padrinhos associados)
DELETE FROM Varinha WHERE varinhaCor = "Azul";
DELETE FROM Varinha WHERE varinhaCor = "Rosa";

-- Exclusões para tabela Padrinho (irá automaticamente excluir magias associadas)
DELETE FROM Padrinhos WHERE nomePadrinho = "Norm";
DELETE FROM Padrinhos WHERE nomePadrinho = "ChloePadrinho";

-- Exclusões para a tabela Desejos (irá automaticamente excluir relações em CriancaFazDesejos)
DELETE FROM Desejos WHERE descricao = "Quero voar";
DELETE FROM Desejos WHERE descricao = "Quero ter superpoderes";

-- Exclusões para a tabela Magia
DELETE FROM Magia WHERE nomeMagia = "Controle do tempo";
DELETE FROM Magia WHERE nomeMagia = "Super Levitação";

-- Exclusões para a tabela CriancaFazDesejos
DELETE FROM CriancaFazDesejos WHERE Crianca_idCrianca = 1 AND Desejos_idDesejos = 3;
DELETE FROM CriancaFazDesejos WHERE Crianca_idCrianca = 2 AND Desejos_idDesejos = 5;
-- Checando os deletes:
SELECT * FROM Crianca;
SELECT * FROM Varinha;
SELECT * FROM Padrinhos;
SELECT * FROM Desejos;
SELECT * FROM Magia;
SELECT * FROM CriancaFazDesejos;

-- ---------------------------------------- JOINs ----------------------------------------------
-- JOIN para Padrinhos, Criança, Criança Faz Desejos e Desejos
SELECT C.nomeCrianca, P.nomePadrinho, D.descricao FROM Padrinhos AS P JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca JOIN 
CriancaFazDesejos AS CD ON C.idCrianca = CD.Crianca_idCrianca JOIN Desejos AS D ON CD.Desejos_idDesejos = D.idDesejos;
-- JOIN para Criança, Criança Faz Desejos e Desejos
SELECT C.nomeCrianca, D.descricao, D.statusDesejo FROM Crianca AS C JOIN CriancaFazDesejos AS CD
ON C.idCrianca = CD.Crianca_idCrianca JOIN Desejos AS D ON CD.Desejos_idDesejos = D.idDesejos;
-- JOIN para Padrinhos, Magia e Criança
SELECT P.nomePadrinho, C.nomeCrianca,  M.nomeMagia, M.descricaoMagia FROM Magia AS M JOIN Padrinhos AS P ON M.Padrinhos_idPadrinhos = P.idPadrinhos
JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca;
-- Checando as alterações

-- ---------------------------------------- VIEW ---------------------------------------------- 
/*
*	Decidimos por usar uma view para criar um resumo geral das crianças, o que nos facilita
*ter um entendimento melhor de como elas estão para caso precisarmos fazer alguma análise de
*dados das crianças.
*/
CREATE VIEW resumoCrianca AS 
	SELECT
		C.nomeCrianca 'Nome da Criança',
        COUNT(DISTINCT CD.Desejos_idDesejos) AS 'Quantidade de Desejos' ,
        P.nomePadrinho 'Nome do Padrinho',
        COUNT(DISTINCT M.idMagia) AS 'Quantidade de Magias'
	FROM Crianca AS C
    LEFT JOIN Padrinhos AS P ON C.idCrianca = P.Crianca_idCrianca
	LEFT JOIN CriancaFazDesejos AS CD ON C.idCrianca = CD.Crianca_idCrianca
	LEFT JOIN Magia AS M ON P.idPadrinhos = M.Padrinhos_idPadrinhos
	GROUP BY C.idCrianca, P.idPadrinhos;
    
SELECT * FROM resumoCrianca;