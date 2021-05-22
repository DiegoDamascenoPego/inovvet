CREATE TABLE IF NOT EXISTS
  `arquivo` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `tamanho` DECIMAL (20,8) NOT NULL,
  `chave` VARCHAR(255) NOT NULL,  
  `url` VARCHAR(255) NOT NULL,
  `tipo` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'IMAGEM, BOLETO, RECEITA, NOTAFISCAL' ,
  `modulo` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'CADASTRO, FINANCEIRO, FISCAL, CLINICA', 
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB;