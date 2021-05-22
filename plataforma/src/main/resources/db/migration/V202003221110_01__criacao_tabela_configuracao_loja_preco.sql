CREATE TABLE IF NOT EXISTS
  `configuracao_loja_preco` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `padrao_despesa_fixa` DECIMAL (4,2)  NULL DEFAULT 0,
  `padrao_margem_lucro_servico` DECIMAL (8,2) default 0 ,
  `padrao_margem_lucro_produto` DECIMAL (8,2) default 0 ,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_configuracao_loja_preco_on_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`)
)
ENGINE=InnoDB;