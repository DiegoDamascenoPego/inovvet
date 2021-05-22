CREATE TABLE IF NOT EXISTS
  `atendimento` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `cliente_id` INT(11) UNSIGNED NOT NULL,
  `animal_id` INT(11) UNSIGNED NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Pendente, Execucao, Finalizado, Atrasado, Cancelado' ,
  `data` DATETIME NOT NULL,   
  `observacao` VARCHAR(255) NULL, 
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_atendimento_on_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`),
   CONSTRAINT `fk_atendimento_on_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
   CONSTRAINT `fk_atendimento_on_animal` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`)
)
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS
  `atendimento_animal` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `atendimento_id` INT(11) UNSIGNED NOT NULL, 
  `animal_id` INT(11) UNSIGNED NOT NULL,
  `data` DATETIME NOT NULL,   
  `observacao` VARCHAR(255) NULL, 
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_atendimento_animal_on_atendimento` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimento` (`id`),
   CONSTRAINT `fk_atendimento_animal_on_animal` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`)
)
ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS
  `atendimento_detalhe` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `atendimento_id` INT(11) UNSIGNED NOT NULL, 
  `produto_id` INT(11) UNSIGNED NULL,
  `servico_id` SMALLINT(6) UNSIGNED NULL,
  `valor_bruto` DECIMAL (10,2) DEFAULT 0,
  `desconto` DECIMAL (10,2) GENERATED ALWAYS  AS  (valor_bruto - valor) VIRTUAL,
  `valor` DECIMAL  (10,2) DEFAULT 0,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_atendimento_detalhe_on_atendimento` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimento` (`id`),
   CONSTRAINT `fk_atendimento_detalhe_on_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
   CONSTRAINT `fk_atendimento_detalhe_on_servico` FOREIGN KEY (`servico_id`) REFERENCES `servico` (`id`)
)
ENGINE=INNODB;