CREATE TABLE pessoa (
	codigo BIGINT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL, 
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(255), 
	numero VARCHAR(255),
	complemento VARCHAR(255),
	bairro VARCHAR(255),
	cep VARCHAR(255),
	cidade VARCHAR(255),
	estado VARCHAR(255)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo) VALUES ('Josielson', true);
INSERT INTO pessoa (nome, ativo) VALUES ('Henrique', 1);
INSERT INTO pessoa (nome, ativo) VALUES ('Coelhada', 1);
INSERT INTO pessoa (nome, ativo) VALUES ('Welber', 1);