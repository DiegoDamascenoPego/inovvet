CREATE TABLE IF NOT EXISTS
  `tipo_produto` (
  `id` TINYINT(1) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `descricao` VARCHAR(50) NOT NULL,
  `permite_vender` TINYINT(1) NOT NULL DEFAULT 1,
  `controlar_estoque` TINYINT(1) NOT NULL DEFAULT 1,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1,  
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
ENGINE=InnoDB;

INSERT INTO tipo_produto (nome, descricao) VALUES ('Revenda', 'Mercadoria para Revenda');
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Máteria-Prima', 'Matéria-Prima', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Embalagem', 'Embalagem', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Processo', 'Produto em Processo', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Acabado', 'Produto Acabado', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Subproduto', 'Subproduto', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Intermediário', 'Produto Intermediário', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Uso e Consumo', 'Material de Uso e Consumo', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Ativo Imobilizado', 'Ativo Imobilizado', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Outros insumos', 'Outros insumos', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Outros', 'Outros', 0, 0);