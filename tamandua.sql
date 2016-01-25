CREATE DATABASE tamandua;

-- Tabela de t_status (Estado)
-- Usada para o login do sistema, para saber se esta ativada ou não.

CREATE TABLE `t_status` (
  `codigo` smallint,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY(`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_status` VALUES ('1','ativo');
INSERT INTO `t_status` VALUES ('2','desativo');

-- Tabela t_tipoFuncionario (Estado)
-- O sistema tem vários tipos de funcionários, está tabela controla os tipos de funcionários oferecidos pelo sistema

CREATE TABLE `t_tipoFuncionario` (
  `codigo` smallint,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY(`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_tipoFuncionario` VALUES ('1','gerente');
INSERT INTO `t_tipoFuncionario` VALUES ('2','funcionario');
INSERT INTO `t_tipoFuncionario` VALUES ('3','adminsistema');

CREATE TABLE `t_menuFuncionario` (
  `codigo` int(2) NOT NULL,
  `nome` varchar(30),
  `link` varchar(20),
  `tipoFunc` smallint,
  PRIMARY KEY (`codigo`),
  CONSTRAINT menutipoFuncionario_ifk FOREIGN KEY (tipoFunc) REFERENCES t_tipoFuncionario (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_menuFuncionario` VALUES (1, 'Cadastrar Correntista', 'cadCorrentista', 1);
INSERT INTO `t_menuFuncionario` VALUES (2, 'Cadastrar Correntista', 'cadCorrentista', 2);
INSERT INTO `t_menuFuncionario` VALUES (3, 'Gerar Conta', 'geraConta', 1);
INSERT INTO `t_menuFuncionario` VALUES (4, 'Cadastrar Funcionario', 'cadFuncionario', 3);
INSERT INTO `t_menuFuncionario` VALUES (5, 'Saldo', 'saldo', 1);
INSERT INTO `t_menuFuncionario` VALUES (6, 'Extrato', 'extrato', 2);
INSERT INTO `t_menuFuncionario` VALUES (7, 'Saldo', 'saldo', 1);
INSERT INTO `t_menuFuncionario` VALUES (8, 'Saldo', 'saldo', 2);
INSERT INTO `t_menuFuncionario` VALUES (9, 'Extrato', 'extrato', 1);
INSERT INTO `t_menuFuncionario` VALUES (10, 'Extrato', 'extrato', 2);
INSERT INTO `t_menuFuncionario` VALUES (11, 'Pagamento', 'pagamento', 1);
INSERT INTO `t_menuFuncionario` VALUES (12, 'Pagamento', 'pagamento', 2);
INSERT INTO `t_menuFuncionario` VALUES (13, 'Logout', 'Logout', 1);
INSERT INTO `t_menuFuncionario` VALUES (14, 'Logout', 'Logout', 2);
INSERT INTO `t_menuFuncionario` VALUES (15, 'Logout', 'Logout', 3);

-- Tabela de t_correntista
-- Registro dos correntista

CREATE TABLE `t_correntista` (
  `cpf` varchar(15) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_correntista` VALUES ('111.111.111-11', 'Rua teste', '1212-3434');

-- Tabela de t_funcionario
-- Registros dos funcionários do banco

CREATE TABLE `t_funcionario` (
  `cpf` varchar(15) NOT NULL,
  `senha` varchar(40) NOT NULL,
  `tipo` smallint NOT NULL,
  CONSTRAINT tipofuncionario_ifk FOREIGN KEY (tipo) REFERENCES t_tipoFuncionario (codigo),
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_funcionario` VALUES ('321','123',1);

-- Tabela t_acesso
-- Tabela de controle de acesso ao sistema, onde reune os correntistas e funcionários

CREATE TABLE `t_acesso` (
  `cpf` varchar(15) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `status` smallint,
  PRIMARY KEY(`cpf`),
  CONSTRAINT statuscesso_ifk FOREIGN KEY (status) REFERENCES t_status (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_acesso` VALUES ('321','Jose',1);
INSERT INTO `t_acesso` VALUES ('111.111.111-11', 'Andre', 1);

-- Tabela t_conta
-- Registros de todas as contas do banco

CREATE TABLE `t_conta` (
  `nroconta` int(5) NOT NULL AUTO_INCREMENT,
  `correntista` varchar(15) NOT NULL,
  `saldo` double NOT NULL DEFAULT 0,
  `senha` double NOT NULL DEFAULT 0,
  `dataconta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` smallint,
  PRIMARY KEY (nroconta),
  CONSTRAINT contacorrentista_ifk FOREIGN KEY (correntista) REFERENCES t_correntista (cpf),
  CONSTRAINT contastatus_ifk FOREIGN KEY (status) REFERENCES t_status (codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_conta` VALUES ('1', '111.111.111-11', 0, '123456', 1);


--

-- Tabela t_TipoTransacao (Estado)
-- Contém os tipos de transacao oferecidos pelo sistema

CREATE TABLE `t_tipoTransacao` (
  `codigo` int(2) NOT NULL,
  `nome` varchar(20),
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_tipoTransacao` VALUES (1, 'extrato');
INSERT INTO `t_tipoTransacao` VALUES (2, 'transferencia');
INSERT INTO `t_tipoTransacao` VALUES (3, 'pagamento');

-- Tabela t_TipoTransacao (Estado)
-- Contém os tipos de transacao oferecidos pelo sistema

CREATE TABLE `t_fator` (
  `codigo` int(2) NOT NULL,
  `nome` varchar(20),
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `t_fator` VALUES (1, 'credito');
INSERT INTO `t_fator` VALUES (2, 'debito');

CREATE TABLE `t_transferencia` (
  `codigo` int(2) NOT NULL AUTO_INCREMENT,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valor` double NOT NULL,
  `nroconta_concedente` int(5) NOT NULL,
  `nroconta_beneficiado` int(5) NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `nroconta_concedente_ibfk` FOREIGN KEY (`nroconta_concedente`) REFERENCES `t_conta` (`nroconta`),
  CONSTRAINT `nroconta_beneficiado_ibfk` FOREIGN KEY (`nroconta_beneficiado`) REFERENCES `t_conta` (`nroconta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `t_histtransacao` (
  `codigohist` int(2) NOT NULL AUTO_INCREMENT,
  `codigotransacao` int(2) NOT NULL,
  `nroconta` int(5) NOT NULL,
  `tipotransacao` int(2) NOT NULL,
  `valor` double NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fator` int(2) NOT NULL,
  PRIMARY KEY (`codigohist`),
  CONSTRAINT `tipotransacaohist_ibfk` FOREIGN KEY (`tipotransacao`) REFERENCES `t_tipoTransacao` (`codigo`),
  CONSTRAINT `nrocontahist_ibfk` FOREIGN KEY (`nroconta`) REFERENCES `t_conta` (`nroconta`),
  CONSTRAINT `fatorhist_ibfk` FOREIGN KEY (`fator`) REFERENCES `t_fator` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
