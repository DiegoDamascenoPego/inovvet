INSERT IGNORE INTO recurso(NOME, DESCRICAO, MODULO_ID)VALUES('TIPOATENDIMENTO_CADASTRAR','Novo Registro',0);
INSERT IGNORE INTO recurso(NOME, DESCRICAO, MODULO_ID)VALUES('TIPOATENDIMENTO_CONSULTAR','Visualizar Registro',0);
INSERT IGNORE INTO recurso(NOME, DESCRICAO, MODULO_ID)VALUES('TIPOATENDIMENTO_EDITAR','Atualizar Registro',0);
INSERT IGNORE INTO recurso(NOME, DESCRICAO, MODULO_ID)VALUES('TIPOATENDIMENTO_REMOVER','Remover Registro',0);

INSERT IGNORE INTO perfil_recurso(perfil_ID, recurso_NOME,ATIVO)SELECT 0, NOME, 1 FROM recurso;
INSERT IGNORE INTO perfil_recurso(perfil_ID, recurso_NOME,ATIVO)SELECT 1, NOME, 0 FROM recurso;
UPDATE perfil_recurso  SET ATIVO = 1 WHERE perfil_ID = 1 AND recurso_NOME LIKE '%CONSULTAR%';
UPDATE perfil_recurso  SET ATIVO = 1 WHERE perfil_ID = 1 AND recurso_NOME LIKE '%SISTEMA_ACESSAR%';