SET FOREIGN_KEY_CHECKS=0;

drop table if exists servico;
drop table if exists produto_preco;
drop table if exists preco_servico;
drop table if exists preco;


CREATE TABLE IF NOT EXISTS
  `produto_preco` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `produto_id` INT(11) UNSIGNED NOT NULL,
  `custo` DECIMAL (10,2) NOT NULL,
  `despesa_adicional` DECIMAL (10,2) NULL DEFAULT 0.00 COMMENT 'São gastos que para serem incorporados aos produtos ou aos serviços utilizam um critério de rateio, também são chamados de despesas (por não terem ligação direta com a produção).' ,
  `despesa_fixa` DECIMAL (4,2) NULL DEFAULT 0.00 COMMENT 'São todos os gastos que não variam em função dos volumes produzidos.',
  `despesa_variavel` DECIMAL (4,2) NULL DEFAULT 0.00 COMMENT 'São gastos que variam proporcionalmente aos volumes produzidos.',
  `margem_lucro` DECIMAL (8,2) NOT NULL,
  `preco` DECIMAL (10,2)  NULL DEFAULT 0.00,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_produto_preco_on_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`),
   CONSTRAINT `fk_servico_preco_on_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
)
ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS=1;