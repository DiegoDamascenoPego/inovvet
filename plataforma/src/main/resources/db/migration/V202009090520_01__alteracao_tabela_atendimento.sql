ALTER TABLE `atendimento` ADD COLUMN `telefone` varchar(50) DEFAULT '' AFTER `status`;

ALTER TABLE `atendimento` ADD COLUMN `cep` varchar(10) DEFAULT NULL AFTER `telefone`;
ALTER TABLE `atendimento` ADD COLUMN `rua` varchar(150) DEFAULT NULL AFTER `cep`;
ALTER TABLE `atendimento` ADD COLUMN `numero` varchar(50) DEFAULT NULL AFTER `rua`;
ALTER TABLE `atendimento` ADD COLUMN `bairro` varchar(100) DEFAULT NULL AFTER `numero`;
ALTER TABLE `atendimento` ADD COLUMN `complemento` varchar(100) DEFAULT NULL AFTER `bairro`;
ALTER TABLE `atendimento` ADD COLUMN `cidade_id` INT(11) UNSIGNED DEFAULT NULL AFTER `complemento`;


