CREATE TABLE IF NOT EXISTS
  `perfil_recurso` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `perfil_id` TINYINT(1) UNSIGNED NOT NULL,
  `recurso_nome` VARCHAR(150) NOT NULL,
  `ativo` TINYINT(1) NOT NULL DEFAULT 1,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`perfil_id`,`recurso_nome`),
  CONSTRAINT `fk_perfil_recurso_on_recurso` FOREIGN KEY (`recurso_nome`) REFERENCES `recurso` (`nome`)
)
ENGINE=InnoDB;