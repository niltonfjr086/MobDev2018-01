-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Pousada
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Pousada` ;

-- -----------------------------------------------------
-- Schema Pousada
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Pousada` DEFAULT CHARACTER SET utf8 ;
USE `Pousada` ;

-- -----------------------------------------------------
-- Table `Pousada`.`Servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Servico` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(150) NOT NULL,
  `tipo_servico` VARCHAR(150) NULL,
  `cod_barras` VARCHAR(20) NOT NULL,
  `valor_unitario` DECIMAL(9,2) NOT NULL,
  `isEnable` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cod_barras_UNIQUE` (`cod_barras` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(150) NULL,
  `tipo_produto` VARCHAR(150) NULL,
  `cod_barras` VARCHAR(20) NULL,
  `valor_unitario` DECIMAL(9,2) NULL,
  `estoque_minimo` INT NULL,
  `estoque_maximo` INT NULL,
  `isEnable` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Produto_Entrada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Produto_Entrada` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idProduto` BIGINT NOT NULL,
  `qtde` INT NULL,
  `data_entrada` DATETIME NULL,
  `valor_custoUnitario` DECIMAL(9,2) NULL,
  `valor_custoTotal` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkProduto_Entrada` (`idProduto` ASC),
  CONSTRAINT `fkProduto_Entrada`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Pousada`.`Produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Apartamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Apartamento` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nomenclatura` VARCHAR(45) NULL,
  `capacidade` INT NULL,
  `categoria` INT NULL,
  `observacoes` VARCHAR(10000) NULL,
  `isReservado` TINYINT(1) NULL,
  `isEnable` TINYINT(1) NULL,
  `valor_economico` DECIMAL(9,2) NULL,
  `valor_basico` DECIMAL(9,2) NULL,
  `valor_completo` DECIMAL(9,2) NULL,
  `valor_premium` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Estadia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Estadia` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idApartamento` BIGINT NULL,
  `observacoes` VARCHAR(10000) NULL,
  `diarias_consumidas` INT NULL,
  `diarias_reservadas` INT NULL,
  `diarias_pagas` INT NULL,
  `qtde_hospede` INT NULL,
  `total_diarias_consumidas` DECIMAL(9,2) NULL,
  `total_produto` DECIMAL(9,2) NULL,
  `total_servico` DECIMAL(9,2) NULL,
  `consumo_pago` DECIMAL(9,2) NULL,
  `total_absoluto` DECIMAL(9,2) NULL,
  `data_entrada` DATETIME NULL,
  `data_saida` DATETIME NULL,
  `isAtivo` TINYINT(1) NULL,
  `multiplicadorConta` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fkApartamento_idx` (`idApartamento` ASC),
  CONSTRAINT `fkApartamentoEstadia`
    FOREIGN KEY (`idApartamento`)
    REFERENCES `Pousada`.`Apartamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Produto_Saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Produto_Saida` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idProduto` BIGINT NOT NULL,
  `idEstadia` BIGINT NULL,
  `qtde` INT NULL,
  `data_saida` DATETIME NULL,
  `valor_vendaUnitario` DECIMAL(9,2) NULL,
  `valor_vendaTotal` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkProduto_Saida` (`idProduto` ASC),
  INDEX `fkEstadia_Produto` (`idEstadia` ASC),
  CONSTRAINT `fkProduto_Saida`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Pousada`.`Produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkEstadia_Produto`
    FOREIGN KEY (`idEstadia`)
    REFERENCES `Pousada`.`Estadia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Produto_Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Produto_Estoque` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idProduto` BIGINT NOT NULL,
  `qtde` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fkProduto_idx` (`idProduto` ASC),
  CONSTRAINT `fkProduto_Estoque`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Pousada`.`Produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Endereco` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(9) NULL,
  `rua` VARCHAR(255) NULL,
  `numero` VARCHAR(45) NULL,
  `bairro` VARCHAR(255) NULL,
  `cidade` VARCHAR(255) NULL,
  `uf` VARCHAR(2) NULL,
  `pais` VARCHAR(255) NULL,
  `complemento` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idEndereco` BIGINT NOT NULL,
  `nome` VARCHAR(80) NULL,
  `sobrenome` VARCHAR(150) NULL,
  `sexo` INT NULL,
  `cpf` VARCHAR(14) NULL,
  `identidade` VARCHAR(14) NULL,
  `telefone` VARCHAR(15) NULL,
  `celular` VARCHAR(15) NULL,
  `dt_nascimento` DATE NULL,
  `profissao` VARCHAR(150) NULL,
  `observacoes` VARCHAR(10000) NULL,
  `email` VARCHAR(100) NULL,
  `isEnable` TINYINT(1) NULL,
  `isHospedado` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkEndereco_idx` (`idEndereco` ASC),
  CONSTRAINT `fkEndereco`
    FOREIGN KEY (`idEndereco`)
    REFERENCES `Pousada`.`Endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Cliente_Estadia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Cliente_Estadia` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idCliente` BIGINT NOT NULL,
  `idEstadia` BIGINT NOT NULL,
  `isResponsavel` TINYINT(1) NULL,
  `tipo_valor` INT NULL,
  `tipo_cliente` INT NULL,
  `peso_cliente` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkCliente_idx` (`idCliente` ASC),
  INDEX `fkEstadia_idx` (`idEstadia` ASC),
  CONSTRAINT `fkCliente_Estadia`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Pousada`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkEstadia_Cliente`
    FOREIGN KEY (`idEstadia`)
    REFERENCES `Pousada`.`Estadia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Servico_Saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Servico_Saida` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idServico` BIGINT NOT NULL,
  `idEstadia` BIGINT NOT NULL,
  `qtde` INT NULL,
  `data_saida` DATETIME NULL,
  `valor_vendaUnitario` DECIMAL(9,2) NULL,
  `valor_vendaTotal` DECIMAL(9,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkServico_idx` (`idServico` ASC),
  INDEX `fkEstadia_idx` (`idEstadia` ASC),
  CONSTRAINT `fkServico_Saida`
    FOREIGN KEY (`idServico`)
    REFERENCES `Pousada`.`Servico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkEstadia_Servico`
    FOREIGN KEY (`idEstadia`)
    REFERENCES `Pousada`.`Estadia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Reserva` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idApartamento` BIGINT NULL,
  `observacoes` VARCHAR(10000) NULL,
  `diarias_reservadas` INT NULL,
  `diarias_pagas` INT NULL,
  `qtde_hospede` INT NULL,
  `data_agendamento` DATE NULL,
  `isAtivo` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkApartamento_idx` (`idApartamento` ASC),
  CONSTRAINT `fkApartamentoReserva`
    FOREIGN KEY (`idApartamento`)
    REFERENCES `Pousada`.`Apartamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Cliente_Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Cliente_Reserva` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idCliente` BIGINT NOT NULL,
  `idReserva` BIGINT NOT NULL,
  `isResponsavel` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fkCliente_idx` (`idCliente` ASC),
  INDEX `fkReserva_idx` (`idReserva` ASC),
  CONSTRAINT `fkCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Pousada`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkReserva`
    FOREIGN KEY (`idReserva`)
    REFERENCES `Pousada`.`Reserva` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Data_ReservaAp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Data_ReservaAp` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `idReserva` BIGINT NULL,
  `idEstadia` BIGINT NULL,
  `idApartamento` BIGINT NOT NULL,
  `data_reservada` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fkApartamento_idx` (`idApartamento` ASC),
  INDEX `fkReserva_idx` (`idReserva` ASC),
  INDEX `fkEstadia_idx` (`idEstadia` ASC),
  CONSTRAINT `fkApartamentoDataReserva`
    FOREIGN KEY (`idApartamento`)
    REFERENCES `Pousada`.`Apartamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkReservaDataReserva`
    FOREIGN KEY (`idReserva`)
    REFERENCES `Pousada`.`Reserva` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkEstadiaDataReserva`
    FOREIGN KEY (`idEstadia`)
    REFERENCES `Pousada`.`Estadia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Configuracao` (
  `idUnico` INT NOT NULL AUTO_INCREMENT,
  `isCriancaAtivo` TINYINT(1) NULL,
  `isAdolescenteAtivo` TINYINT(1) NULL,
  `isAdultoAtivo` TINYINT(1) NULL,
  `isIdosoAtivo` TINYINT(1) NULL,
  `faixaIdadeCrianca` INT NULL,
  `faixaIdadeAdolescente` INT NULL,
  `faixaIdadeAdulto` INT NULL,
  `faixaIdadeIdoso` INT NULL,
  `pesoCrianca` DECIMAL(9,2) NULL,
  `pesoAdolescente` DECIMAL(9,2) NULL,
  `pesoAdulto` DECIMAL(9,2) NULL,
  `pesoIdoso` DECIMAL(9,2) NULL,
  `isEconomicoAtivo` TINYINT(1) NULL,
  `isBasicoAtivo` TINYINT(1) NULL,
  `isCompletoAtivo` TINYINT(1) NULL,
  `isPremiumAtivo` TINYINT(1) NULL,
  `descricaoEconomico` VARCHAR(2500) NULL,
  `descricaoBasico` VARCHAR(2500) NULL,
  `descricaoCompleto` VARCHAR(2500) NULL,
  `descricaoPremium` VARCHAR(2500) NULL,
  `horarioCheckOut` TIME NULL,
  `horarioCheckIn` TIME NULL,
  PRIMARY KEY (`idUnico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pousada`.`Usuario`
-- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `Pousada`.`Usuario` (
--   `id` INT NOT NULL,
--   `nome` VARCHAR(45) NULL,
--   `sobrenome` VARCHAR(45) NULL,
--   `cpf` VARCHAR(45) NULL,
--   `dt_nascimento` VARCHAR(45) NULL,
--   `login` VARCHAR(45) NULL,
--   `senha` VARCHAR(45) NULL,
--   PRIMARY KEY (`id`))
-- ENGINE = InnoDB;
DROP TABLE IF EXISTS `Pousada`.`Usuario`;
-- -----------------------------------------------------
-- Table `Pousada`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`Usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(40) NOT NULL UNIQUE,
  `senha` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- SELECT * FROM Pousada.Usuario;
-- DESCRIBE Pousada.Usuario;
-- DROP TABLE IF EXISTS `Pousada`.`Usuario`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- ESSA PARTE FOI PARA TESTES INICIAIS ANDROID
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `Pousada`.`tb_categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`tb_categoria` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SELECT * FROM Pousada.tb_categoria;
DESCRIBE Pousada.tb_categoria;


-- -----------------------------------------------------
-- Table `Pousada`.`tb_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pousada`.`tb_produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
    `categoria_id` BIGINT NOT NULL,
	`valor` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fkApartamento_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `Pousada`.`tb_categoria` (`id`)
    ON DELETE NO ACTION)
ENGINE = InnoDB;

SELECT * FROM Pousada.tb_produto;
DESCRIBE Pousada.tb_produto;
