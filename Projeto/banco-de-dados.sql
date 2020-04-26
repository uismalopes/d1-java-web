CREATE DATABASE portal_realnews DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE portal_realnews;

CREATE TABLE noticia (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(512),
	titulo VARCHAR(126),
	texto TEXT
) DEFAULT CHARSET = utf8;

CREATE TABLE comentario (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(126),
	texto VARCHAR(512),
	fk_noticia_id INT NOT NULL,
	FOREIGN KEY (fk_noticia_id)
	REFERENCES noticia(id) on update cascade on delete cascade
) DEFAULT CHARSET = utf8;
