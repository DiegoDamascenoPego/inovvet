CREATE TABLE uf(
  sigla char(2) NOT NULL,
  codigo tinyint(1) NOT NULL,
  nome varchar(45) DEFAULT NULL,
  PRIMARY KEY (sigla)
) ENGINE=InnoDB;