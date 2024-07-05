
CREATE TABLE usuario_processo_sistema (
  id bigint NOT NULL AUTO_INCREMENT,
  usuario_id bigint NOT NULL,
  sistema_id bigint NOT NULL,
  numero_processo varchar(25) NOT NULL,
  PRIMARY KEY (id),  
  CONSTRAINT FK_USUARIO_PROCESSO_SISTEMA__USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
  CONSTRAINT FK_USUARIO_PROCESSO_SISTEMA__SISTEMA_ID FOREIGN KEY (sistema_id) REFERENCES sistemas (id)
) ;