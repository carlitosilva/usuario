CREATE TABLE endereco_usuario (
  usuario_id bigint NOT NULL,
  bairro varchar(60) NOT NULL,
  cep varchar(10) NOT NULL,
  cidade varchar(100) NOT NULL,
  numero varchar(20) DEFAULT NULL,
  rua varchar(100) NOT NULL,
  uf varchar(60) NOT NULL,
  PRIMARY KEY (usuario_id),
  CONSTRAINT FK_ENDERECO_USUARIO__USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
) ;