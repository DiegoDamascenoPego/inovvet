SET FOREIGN_KEY_CHECKS=0;
Truncate tipo_produto; 

INSERT INTO tipo_produto (nome, descricao) VALUES ('Revenda', 'Mercadoria para Revenda');
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Máteria-Prima', 'Matéria-Prima', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Embalagem', 'Embalagem', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Processo', 'Produto em Processo', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Acabado', 'Produto Acabado', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Subproduto', 'Subproduto', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Intermediário', 'Produto Intermediário', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Uso e Consumo', 'Material de Uso e Consumo', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Ativo Imobilizado', 'Ativo Imobilizado', 0, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Serviço', 'Serviço', 1, 1);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Outros insumos', 'Outros insumos', 0, 0);
INSERT INTO tipo_produto (nome, descricao, permite_vender, ativo) VALUES ('Outros', 'Outros', 0, 0);

SET FOREIGN_KEY_CHECKS=1;