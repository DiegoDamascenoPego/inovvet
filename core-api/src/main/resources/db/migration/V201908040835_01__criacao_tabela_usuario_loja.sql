CREATE TABLE IF NOT EXISTS `usuario_loja` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_id` INT(11) UNSIGNED NOT NULL,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '0 = INATIVO  1 = ATIVO',
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_usuario_loja_loja_id_usuario_id` ( `loja_id`,`usuario_id`),
  CONSTRAINT `fk_usuario_loja_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_usuario_loja_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`)) ENGINE=InnoDB;