CREATE TABLE IF NOT EXISTS
  `tipo_atendimento_agenda` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo_atendimento_id` TINYINT NOT NULL,
  `dia` ENUM('DOMINGO', 'SEGUNDA', 'TERCA', 'QUARTA', 'QUINTA', 'SEXTA', 'SABADO'),
  `hora` TIME NOT NULL,
  `vaga` TINYINT DEFAULT 1,
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY uk_tipo_atendimento_agenda(tipo_atendimento_id, dia, hora),
  CONSTRAINT `tipo_atendimento_agenda_on_tipo_atendimento` FOREIGN KEY (`tipo_atendimento_id`) REFERENCES `tipo_atendimento` (`id`)
)
ENGINE=InnoDB;