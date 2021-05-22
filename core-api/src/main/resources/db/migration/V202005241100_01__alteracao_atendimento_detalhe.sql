drop table IF EXISTS atendimento_detalhe;

CREATE TABLE IF NOT EXISTS
  `atendimento_detalhe` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `atendimento_id` INT(11) UNSIGNED NOT NULL, 
  `produto_id` INT(11) UNSIGNED NULL,
  `valor_bruto` DECIMAL (10,2) DEFAULT 0,
  `desconto` DECIMAL (10,2) GENERATED ALWAYS  AS  (valor_bruto - valor) VIRTUAL,
  `valor` DECIMAL  (10,2) DEFAULT 0,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_atendimento_detalhe_on_atendimento` FOREIGN KEY (`atendimento_id`) REFERENCES `atendimento` (`id`),
   CONSTRAINT `fk_atendimento_detalhe_on_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
)
ENGINE=INNODB;