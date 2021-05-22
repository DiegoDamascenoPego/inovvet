CREATE TABLE IF NOT EXISTS `preco_servico` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `preco_id` INT(11) UNSIGNED NOT NULL,
  `servico_id` SMALLINT(6) UNSIGNED NOT NULL,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_preco_servico_preco_id_servico_id` ( `preco_id`,`servico_id`),
  CONSTRAINT `fk_preco` FOREIGN KEY (`preco_id`) REFERENCES `preco` (`id`),
  CONSTRAINT `fk_servico` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`)) ENGINE=InnoDB;