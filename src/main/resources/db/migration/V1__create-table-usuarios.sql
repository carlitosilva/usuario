CREATE TABLE usuarios (
  id bigint NOT NULL AUTO_INCREMENT,
  cpf varchar(14) DEFAULT NULL,
  login varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  senha varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);