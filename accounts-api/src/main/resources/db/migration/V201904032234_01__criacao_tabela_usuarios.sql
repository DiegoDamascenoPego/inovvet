CREATE TABLE IF NOT EXISTS
  `usuario` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NULL,
  `account_non_expired` TINYINT(1) NOT NULL DEFAULT 1,
  `account_non_locked` TINYINT(1) NOT NULL DEFAULT 1,
  `credentials_non_expired` TINYINT(1) NOT NULL DEFAULT 1,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
   ENGINE=InnoDB;
 
CREATE TABLE IF NOT EXISTS `usuario_conta` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_id` INT(11) UNSIGNED NOT NULL,
  `conta_id` INT(11) UNSIGNED NOT NULL,
  `token` VARCHAR(255) NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '0 = INATIVO  1 = ATIVO',
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_usuario_conta_conta_id_usuario_id` ( `conta_id`,`usuario_id`),
  CONSTRAINT `fk_usuario_conta_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_conta_conta`
    FOREIGN KEY (`conta_id`)
    REFERENCES `conta` (`id`))
        ENGINE=InnoDB;
      
 CREATE TABLE IF NOT EXISTS `recurso` (
  `nome` VARCHAR(150) NOT NULL,
  `descricao` VARCHAR(150) NULL,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`nome`))
       ENGINE=InnoDB;
     
     
CREATE TABLE IF NOT EXISTS `perfil_acesso` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `recurso_nome` VARCHAR(150) NOT NULL,
  `perfil_id` TINYINT(1) UNSIGNED NOT NULL,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `recurso_nome`),
  CONSTRAINT `fk_usuario_contas_acesso_recurso`
    FOREIGN KEY (`recurso_nome`)
    REFERENCES `recurso` (`nome`)
    )
  ENGINE=InnoDB;