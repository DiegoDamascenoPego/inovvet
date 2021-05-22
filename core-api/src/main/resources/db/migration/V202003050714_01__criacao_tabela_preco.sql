CREATE TABLE IF NOT EXISTS
  `preco` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `custo` DECIMAL (10,2) NOT NULL,
  `custo_medio` DECIMAL (10,2) DEFAULT 0,
  `despesa_fixa` DECIMAL (4,2) NOT NULL COMMENT 'São todos os gastos que não variam em função dos volumes produzidos.',
  `despesa_variavel` DECIMAL (4,2) NOT NULL COMMENT 'São gastos que variam proporcionalmente aos volumes produzidos.',
  `despesa_adicional` DECIMAL (10,2) NOT NULL COMMENT 'São gastos que para serem incorporados aos produtos ou aos serviços utilizam um critério de rateio, também são chamados de despesas (por não terem ligação direta com a produção).' ,
  `margem_lucro` DECIMAL (8,2) NOT NULL,
  `tipo_calculo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '0 = CUSTO 1 = CUSTOMEDIO',
  `tipo` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '0 = Varejo 1 = Atacado',
  `preco` DECIMAL (10,2)  NULL DEFAULT 0.00,
  `data_inicial` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_final` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_preco_on_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`)
)
ENGINE=InnoDB;