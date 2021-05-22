ALTER TABLE `loja` ADD COLUMN `cidade_codigo` VARCHAR(20) NULL DEFAULT NULL AFTER `bairro`;

ALTER TABLE `cliente_endereco` ADD COLUMN `cidade_codigo` VARCHAR(20) NULL DEFAULT NULL AFTER `cliente_id`;

ALTER TABLE `pessoa_endereco` ADD COLUMN `cidade_codigo` VARCHAR(20) NULL DEFAULT NULL AFTER `pessoa_id`;

ALTER TABLE `atendimento` ADD COLUMN `cidade_codigo` VARCHAR(20) NULL DEFAULT NULL AFTER `complemento`;

UPDATE  loja l  INNER JOIN cidade c ON c.id = l.cidade_id SET l.cidade_codigo = c.codigo;

UPDATE  cliente_endereco cl INNER JOIN cidade c ON c.id = cl.cidade_id SET cl.cidade_codigo = c.codigo;

UPDATE  pessoa_endereco p  INNER JOIN cidade c ON c.id = p.cidade_id SET p.cidade_codigo = c.codigo;

UPDATE  atendimento a  INNER JOIN cidade c ON c.id = a.cidade_id SET a.cidade_codigo = c.codigo;


ALTER TABLE `loja` DROP COLUMN `cidade_id`;

ALTER TABLE `cliente_endereco` DROP COLUMN `cidade_id`;

ALTER TABLE `pessoa_endereco` DROP COLUMN `cidade_id`;

ALTER TABLE `atendimento` DROP COLUMN `cidade_id`;