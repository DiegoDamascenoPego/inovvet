CREATE TABLE IF NOT EXISTS
  `cliente_endereco` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cliente_id` INT(11) UNSIGNED NOT NULL,
  `cidade_id` INT(11) UNSIGNED NOT NULL,  
  `tipo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT 'Residencial, Entrega, Comercial',
  `cep` VARCHAR(10) NULL,
  `rua` VARCHAR(150) NULL,
  `numero` VARCHAR(50) NULL,
  `bairro` VARCHAR(100) NULL,
  `complemento` VARCHAR(100), 
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cliente_endereco_on_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`))
   ENGINE=InnoDB;