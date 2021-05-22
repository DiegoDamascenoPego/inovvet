ALTER TABLE `conta`
	ADD COLUMN `cep` VARCHAR(45) NULL AFTER `ativo`,
	ADD COLUMN `rua` VARCHAR(150) NOT NULL AFTER `cep`,
	ADD COLUMN `numero` VARCHAR(20) NOT NULL AFTER `rua`,
	ADD COLUMN  `complemento` VARCHAR(150) NULL AFTER `numero`,
    ADD COLUMN  `bairro` VARCHAR(150) NOT NULL AFTER `complemento`,
    ADD COLUMN  `cidade_codigo` VARCHAR(20) NOT NULL AFTER `bairro`;
