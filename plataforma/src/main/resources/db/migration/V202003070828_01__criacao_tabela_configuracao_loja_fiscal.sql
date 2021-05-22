CREATE TABLE IF NOT EXISTS
  `configuracao_loja_fiscal` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `regime_empresa` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = MEI 1 = Simples 2 = Lucro Presumido 3 = Lucro real',
  `aliquota_issqn` DECIMAL (4,2) NULL DEFAULT 0,
  `aliquota_pis` DECIMAL (4,2)  NULL DEFAULT 0,
  `aliquota_cofins` DECIMAL (4,2)  NULL DEFAULT 0,
  `aliquota_social` DECIMAL (4,2)  NULL DEFAULT 0,
  `aliquota_irpj` DECIMAL (4,2)  NULL DEFAULT 0,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_configuracao_loja_fiscal_on_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`)
)
ENGINE=InnoDB;