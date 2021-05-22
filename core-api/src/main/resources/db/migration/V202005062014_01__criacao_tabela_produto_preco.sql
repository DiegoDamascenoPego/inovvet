CREATE TABLE IF NOT EXISTS `produto_preco` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `produto_id`  INT(11) UNSIGNED NOT NULL,
  `preco_id` INT(11) UNSIGNED NOT NULL,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_produto_servico_preco_id_produto_id` (`produto_id`, `preco_id`),
  CONSTRAINT `fk_produto_servico_preco` FOREIGN KEY (`preco_id`) REFERENCES `preco` (`id`),
  CONSTRAINT `fk_produto_servico_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)) ENGINE=InnoDB;