CREATE TABLE IF NOT EXISTS
  `cliente_telefone` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) UNSIGNED NOT NULL,
  `tipo` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Celular, Residencial, Comercial',
  `numero` VARCHAR(20) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cliente_telefone_on_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
)
ENGINE=InnoDB;