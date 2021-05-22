CREATE TABLE `tipo_atendimento` (
	`id` TINYINT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(50) NOT NULL,
	`tempo` SMALLINT(6) DEFAULT '0',
	`ativo` TINYINT(1) NOT NULL DEFAULT '1',
	`data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB;

CREATE TABLE `tipo_atendimento_produto` (
	`tipo_atendimento_id` TINYINT NOT NULL,
	`produto_id` INT(11) UNSIGNED NOT NULL,
	PRIMARY KEY (`tipo_atendimento_id`, `produto_id`),
	CONSTRAINT `fk_tipo_atendimento_produto_on_tipo_atendimento` FOREIGN KEY (`tipo_atendimento_id`) REFERENCES `tipo_atendimento` (`id`),
	CONSTRAINT `fk_tipo_atendimento_produto_on_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
)
ENGINE=InnoDB;

