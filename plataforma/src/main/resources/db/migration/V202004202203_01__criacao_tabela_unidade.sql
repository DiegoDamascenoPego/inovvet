CREATE TABLE IF NOT EXISTS
  `unidade` (
  `id` TINYINT(1) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `descricao` VARCHAR(50) NOT NULL,
  `unidade_padrao` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0 = UNIDADE 1 = KG = 2 = METRO',  
  `ativo` TINYINT(1) NOT NULL DEFAULT 1,  
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  unique key(nome)
)
ENGINE=InnoDB;

INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Ampola","Ampola",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Balde","Balde",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Bandej","Bandeja",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Barra","Barra",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Bisnag","Bisnaga",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Bloco","Bloco",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Bobina","Bobina",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Bombear","Bombona",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cápsulas","Cápsula",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Carrinho","Cartela",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cento","Cento",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cj","Conjunto",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cm","Centímetro",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cm2","Centimetro Quadrado",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx","Caixa",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx2","Caixa Com 2 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx3","Caixa Com 3 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx5","Caixa Com 5 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx10","Caixa Com 10 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx15","Caixa Com 15 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx20","Caixa Com 20 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx25","Caixa Com 25 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx50","Caixa Com 50 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Cx100","Caixa Com 100 Unidades",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Disp","Exibição",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Duzia","Duzia",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Embal","Embalagem",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Fardo","Fardo",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Folha","Folha",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Frasco","Frasco",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Galao","Galão",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Gf","Garrafa",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Gramas","Gramas",1,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Jogo","Jogo",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Kg","Quilograma",1,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Kit","Kit",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Lata","Lata",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Litro","Litro",1,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("M","Metro",2,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("M2","Metro Quadrado",2,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("M3","Metro Cúbico",2,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Milhei","Milheiro",1,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Ml","Mililitro",1,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Mwh","Megawatt Hora",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Pacote","Pacote",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Palete","Palete",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Pares","Pares",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Pc","Peça",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Amigo","Amigo",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("K","Quilate",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Resma","Resma",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Rolo","Rolo",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Saco","Saco",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Sacola","Sacola",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Tambor","Tambor",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Tanque","Tanque",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Ton  ","Tonelada",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Tubo","Tubo",0,0);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Unid","Unidade",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Vasil","Vasilhame",0,1);
INSERT INTO unidade (nome, descricao, unidade_padrao, ativo)values("Vidro","Vidro",0,1);
