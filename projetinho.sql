-- Beatriz Vaz Pedroso Santos Cobral
-- Felipe Silva Loschi

SET SQL_SAFE_UPDATES = 0;

DROP DATABASE IF EXISTS Padrinhos_Magicos;
CREATE DATABASE IF NOT EXISTS Padrinhos_Magicos;
USE Padrinhos_Magicos;

-- Tabela: AntiFada
CREATE TABLE IF NOT EXISTS AntiFada (
                                        idFada INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                        nomeFada VARCHAR(45),
    tipoFada VARCHAR(45),
    Varinha_idSerial INT
    );

-- Tabela: Crianca
CREATE TABLE IF NOT EXISTS Crianca (
                                       idCrianca INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                       nomeCrianca VARCHAR(45),
    idadeCrianca INT,
    sexoCrianca VARCHAR(45),
    temPadrinho BOOLEAN,
    enderecoCrianca VARCHAR(45)
    );

-- Tabela: Desejos
CREATE TABLE IF NOT EXISTS Desejos (
                                       idDesejos INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                       descricao VARCHAR(50),
    statusDesejo VARCHAR(20)
    );

-- Tabela: CriancaFazDesejos
CREATE TABLE IF NOT EXISTS CriancaFazDesejos (
                                                 Crianca_idCrianca INT,
                                                 Desejos_idDesejos INT,
                                                 PRIMARY KEY (Crianca_idCrianca, Desejos_idDesejos),
    FOREIGN KEY (Crianca_idCrianca) REFERENCES Crianca(idCrianca) ON DELETE CASCADE,
    FOREIGN KEY (Desejos_idDesejos) REFERENCES Desejos(idDesejos) ON DELETE CASCADE
    );

-- Tabela: GeneralFada
CREATE TABLE IF NOT EXISTS GeneralFada (
                                           idFada INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                           nomeFada VARCHAR(45),
    tipoFada VARCHAR(45),
    Varinha_idSerial INT
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

-- Tabela: Magia
CREATE TABLE IF NOT EXISTS Magia (
                                     idMagia INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                     nomeMagia VARCHAR(45),
    descricaoMagia TEXT,
    Padrinhos_idPadrinhos INT,
    FOREIGN KEY (Padrinhos_idPadrinhos) REFERENCES Padrinhos(idPadrinhos) ON DELETE CASCADE
    );

-- TRIGGER: Verifica se uma criança já tem padrinho do mesmo tipo
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

-- -------------------------- INSERÇÕES --------------------------

-- Crianças
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

-- Varinhas
INSERT INTO Varinha (varinhaCor, statusVarinha) VALUES
                                                    ('Rosa', 'Funcionando'),
                                                    ('Azul', 'Funcionando'),
                                                    ('Verde', 'Em manutenção'),
                                                    ('Dourada', 'Funcionando'),
                                                    ('Roxa', 'Quebrada'),
                                                    ('Arco-íris', 'Funcionando'),
                                                    ('Prata', 'Em manutenção'),
                                                    ('Negra', 'Funcionando'),
                                                    ('Branca', 'Quebrada');

-- Padrinhos
INSERT INTO Padrinhos (idPadrinhos, nomePadrinho, tipoPadrinho, Varinha_idSerial, Crianca_idCrianca) VALUES
                                                                                                         (3, 'Cosmo', 'Padrinho', 2, 1),
                                                                                                         (4, 'Wanda', 'Madrinha', 1, 1),
                                                                                                         (5, 'Juandíssimo', 'Padrinho', 4, 2),
                                                                                                         (6, 'Norm', 'Padrinho', 5, 5),
                                                                                                         (7, 'ChloePadrinho', 'Madrinha', 3, 2),
                                                                                                         (8, 'Sparky', 'Padrinho', 6, 6),
                                                                                                         (9, 'Vicky', 'Madrinha', 7, 7),
                                                                                                         (10, 'Professor Calamitous', 'Padrinho', 8, 8),
                                                                                                         (11, 'Ultra Lord', 'Padrinho', 9, 9);

-- Desejos
INSERT INTO Desejos (descricao, statusDesejo) VALUES
                                                  ('Quero um lanche infinito', 'Concedido'),
                                                  ('Quero virar um super-herói', 'Concedido'),
                                                  ('Quero férias eternas', 'Negado'),
                                                  ('Quero voar', 'Concedido'),
                                                  ('Quero um castelo', 'Pendente'),
                                                  ('Quero ser o mais inteligente da escola', 'Pendente'),
                                                  ('Quero um robô ajudante', 'Concedido'),
                                                  ('Quero entender as meninas', 'Negado'),
                                                  ('Quero ter superpoderes', 'Concedido'),
                                                  ('Quero um cachorro falante', 'Pendente');

-- Relação criança-desejos
INSERT INTO CriancaFazDesejos (Crianca_idCrianca, Desejos_idDesejos) VALUES
                                                                         (1,1), (1,2), (2,5), (5,4), (1,3),
                                                                         (6,6), (7,7), (7,8), (8,9), (9,10), (6,4);

-- Magias
INSERT INTO Magia (nomeMagia, descricaoMagia, Padrinhos_idPadrinhos) VALUES
-- MAGIAS DE COMBATE COM VARINHA
('Rajada Estelar: Chuva de Mil Varinhas', 'O padrinho multiplica sua varinha em milhares de projéteis brilhantes que perseguem o inimigo automaticamente, cada uma explodindo com energia mágica pura ao impacto', 1),
('Varinha Dimensional: Corte do Vazio', 'A varinha se estende através de múltiplas dimensões, permitindo ataques que ignoram completamente defesas físicas e mágicas, cortando diretamente a essência do oponente', 2),
('Transformação Suprema: Varinha Dragão', 'A varinha se transforma em um dragão mágico gigante que obedece aos comandos do padrinho, cuspindo fogo arco-íris devastador e investindo com força cósmica', 3),
-- MAGIAS DE INVOCAÇÃO DE BATALHA
('Convocação: Exército de Padrinhos Ancestrais', 'Invoca os espíritos de padrinhos mágicos lendários que lutaram em guerras épicas, cada um trazendo suas técnicas únicas e poder acumulado de séculos', 4),
('Portal da Dimensão Mágica: Invasão Fada', 'Abre portais em massa de onde emergem enxames de fadas guerreiras armadas com mini-varinhas explosivas, criando um campo de batalha caótico e colorido', 5),
('Materialização: Arsenal Mágico Infinito', 'Faz aparecer instantaneamente qualquer arma mágica imaginável - desde espadas que cortam através do tempo até escudos que refletem maldições de volta ao atacante', 6),
-- MAGIAS DE TRANSFORMAÇÃO DE COMBATE
('Metamorfose Titânica: Padrinho Colossal', 'O padrinho cresce até proporções gigantescas mantendo sua velocidade original, com cada movimento criando ondas de choque mágicas que devastam o campo de batalha', 7),
('Forma Elemental: Senhor das Estrelas', 'O corpo do padrinho se transforma em energia estelar pura, permitindo teletransporte instantâneo e ataques que queimam com o calor de supernovas', 8),
-- MAGIAS DE CONTROLE DE CAMPO
('Domínio Absoluto: Reino do Padrinho', 'Cria uma área dimensional onde apenas o padrinho pode usar magia, enquanto inimigos têm seus poderes selados e ficam sujeitos às leis mágicas absurdas', 9),
('Alteração da Realidade: Mundo Invertido', 'Inverte todas as leis da física e magia em um raio de 1km, fazendo ataques inimigos curarem o padrinho e defesas se tornarem pontos fracos', 6),
-- MAGIAS DE PROTEÇÃO EXTREMA
('Escudo Prisma: Reflexão Infinita', 'Cria um campo de força que não apenas bloqueia ataques, mas os multiplica e reflete de volta ao atacante com força amplificada exponencialmente', 1),
('Regeneração Mágica: Renascimento Instantâneo', 'Permite ao padrinho se regenerar instantaneamente de qualquer ferimento, incluindo desintegração completa, voltando com poder aumentado a cada "morte"', 2),
-- MAGIAS DE ATAQUE DEVASTADOR
('Explosão Cósmica: Big Bang Controlado', 'Canaliza a força de criação do universo em um único ponto, liberando uma explosão que reescreve a realidade local conforme a vontade do padrinho', 3),
('Aniquilação Temporal: Apagar da Existência', 'Ataque que não apenas destrói o alvo no presente, mas apaga sua existência de todas as linhas temporais, como se nunca tivesse existido', 4),
-- MAGIAS DE UTILIDADE TÁTICA
('Clonagem Dimensional: Múltiplos Eus', 'Cria clones do padrinho vindos de dimensões paralelas, cada um com personalidades e especialidades diferentes, mas todos compartilhando a mesma consciência', 5),
('Manipulação Temporal: Controle Total', 'Permite acelerar, desacelerar, parar ou reverter o tempo em áreas específicas, criando vantagens táticas impossíveis e combos devastadores', 6);


-- -------------------------- ATUALIZAÇÕES --------------------------

UPDATE Crianca SET temPadrinho = 0 WHERE idCrianca = 2;
UPDATE Crianca SET temPadrinho = 1 WHERE idCrianca = 3;

UPDATE Varinha SET statusVarinha = 'Funcionando' WHERE idSerial = 3;
UPDATE Varinha SET varinhaCor = 'Prateada' WHERE idSerial = 5;

UPDATE Padrinhos SET tipoPadrinho = 'Madrinha' WHERE idPadrinhos = 6;
UPDATE Padrinhos SET nomePadrinho = 'Chloe Madrinha' WHERE idPadrinhos = 7;

UPDATE Desejos SET statusDesejo = 'Concedido' WHERE idDesejos = 5;
UPDATE Desejos SET descricao = 'Quero um castelo de chocolate' WHERE idDesejos = 5;

UPDATE Magia SET descricaoMagia = 'Transformar objetos em outros por 24h' WHERE idMagia = 1;
UPDATE Magia SET nomeMagia = 'Super Levitação' WHERE idMagia = 5;

-- -------------------------- EXCLUSÕES --------------------------

DELETE FROM Crianca WHERE nomeCrianca = 'Chester McBadbat';
DELETE FROM Crianca WHERE nomeCrianca = 'Tootie';

DELETE FROM Varinha WHERE varinhaCor = 'Azul';
DELETE FROM Varinha WHERE varinhaCor = 'Rosa';

DELETE FROM Padrinhos WHERE nomePadrinho = 'Norm';
DELETE FROM Padrinhos WHERE nomePadrinho = 'ChloePadrinho';

DELETE FROM Desejos WHERE descricao = 'Quero voar';
DELETE FROM Desejos WHERE descricao = 'Quero ter superpoderes';

DELETE FROM Magia WHERE nomeMagia = 'Controle do tempo';
DELETE FROM Magia WHERE nomeMagia = 'Super Levitação';

DELETE FROM CriancaFazDesejos WHERE Crianca_idCrianca = 1 AND Desejos_idDesejos = 3;
DELETE FROM CriancaFazDesejos WHERE Crianca_idCrianca = 2 AND Desejos_idDesejos = 5;

-- -------------------------- JOINs --------------------------

-- JOIN: Padrinhos + Criança + Desejos
SELECT C.nomeCrianca, P.nomePadrinho, D.descricao
FROM Padrinhos AS P
         JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca
         JOIN CriancaFazDesejos AS CD ON C.idCrianca = CD.Crianca_idCrianca
         JOIN Desejos AS D ON CD.Desejos_idDesejos = D.idDesejos;

-- JOIN: Criança + Desejos
SELECT C.nomeCrianca, D.descricao, D.statusDesejo
FROM Crianca AS C
         JOIN CriancaFazDesejos AS CD ON C.idCrianca = CD.Crianca_idCrianca
         JOIN Desejos AS D ON CD.Desejos_idDesejos = D.idDesejos;

-- JOIN: Magia + Padrinhos + Criança
SELECT P.nomePadrinho, C.nomeCrianca, M.nomeMagia, M.descricaoMagia
FROM Magia AS M
         JOIN Padrinhos AS P ON M.Padrinhos_idPadrinhos = P.idPadrinhos
         JOIN Crianca AS C ON P.Crianca_idCrianca = C.idCrianca;

-- -------------------------- VIEW --------------------------

CREATE VIEW resumoCrianca AS
SELECT
    C.nomeCrianca AS 'Nome da Criança',
        COUNT(DISTINCT CD.Desejos_idDesejos) AS 'Quantidade de Desejos',
        P.nomePadrinho AS 'Nome do Padrinho',
        COUNT(DISTINCT M.idMagia) AS 'Quantidade de Magias'
FROM Crianca AS C
         LEFT JOIN Padrinhos AS P ON C.idCrianca = P.Crianca_idCrianca
         LEFT JOIN CriancaFazDesejos AS CD ON C.idCrianca = CD.Crianca_idCrianca
         LEFT JOIN Magia AS M ON P.idPadrinhos = M.Padrinhos_idPadrinhos
GROUP BY C.idCrianca, P.idPadrinhos;

SELECT * FROM resumoCrianca;
select * from Crianca;
select * from AntiFada;
select * from Magia;
select * from Padrinhos;