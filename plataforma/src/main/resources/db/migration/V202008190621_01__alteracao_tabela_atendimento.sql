SET FOREIGN_KEY_CHECKS=0;

drop table IF EXISTS atendimento;


CREATE TABLE IF NOT EXISTS
  `atendimento` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo_atendimento_id` TINYINT NOT NULL,
  `loja_id` INT(11) UNSIGNED NOT NULL,
  `cliente_id` INT(11) UNSIGNED NOT NULL,
  `animal_id` INT(11) UNSIGNED NOT NULL,
  `usuario_id` INT(11) UNSIGNED NOT NULL,
  `atendente_id` INT(11) UNSIGNED DEFAULT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Pendente, Execucao, Finalizado, Atrasado, Cancelado' ,
  `data` DATETIME NOT NULL,   
  `observacao` VARCHAR(255) NULL, 
  `data_cadastro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
   CONSTRAINT `fk_atendimento_on_loja` FOREIGN KEY (`loja_id`) REFERENCES `loja` (`id`),
   CONSTRAINT `fk_atendimento_on_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
   CONSTRAINT `fk_atendimento_on_animal` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`),
   CONSTRAINT `fk_tipo_atendimento_on_tipo_atendimento` FOREIGN KEY (`tipo_atendimento_id`) REFERENCES `tipo_atendimento` (`id`),
   CONSTRAINT `fk_tipo_atendimento_on_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
   CONSTRAINT `fk_tipo_atendimento_on_atendente` FOREIGN KEY (`atendente_id`) REFERENCES `usuario` (`id`)
   
)
ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS=1;