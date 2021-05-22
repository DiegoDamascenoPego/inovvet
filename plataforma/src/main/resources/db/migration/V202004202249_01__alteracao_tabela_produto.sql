SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS produto;


CREATE TABLE IF NOT EXISTS
  `produto` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(70) NOT NULL,
  `descricao` VARCHAR(120) NOT NULL,
  `tipo_produto_id` TINYINT(1) UNSIGNED NULL,
  `unidade_id` TINYINT(1) UNSIGNED NULL,
  `produto_classificacao_id` SMALLINT(6) UNSIGNED NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_produto_on_produto_classificacao` FOREIGN KEY (`produto_classificacao_id`) REFERENCES `produto_classificacao` (`id`),
   CONSTRAINT `fk_produto_on_produto_tipo_produto` FOREIGN KEY (`tipo_produto_id`) REFERENCES `tipo_produto` (`id`),
   CONSTRAINT `fk_produto_on_produto_unidade` FOREIGN KEY (`unidade_id`) REFERENCES `unidade` (`id`)
)
ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS=1;