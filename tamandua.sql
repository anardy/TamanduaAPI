CREATE DATABASE tamandua;

CREATE TABLE `status` (
  `codigo` smallint,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY(`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `status` VALUES ('1','ativo');
INSERT INTO `status` VALUES ('2','desativo');

CREATE TABLE `tipotransacao` (
  `codigo` int(2) NOT NULL,
  `nome` varchar(20),
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `tipotransacao` VALUES (1, 'saldo');
INSERT INTO `tipotransacao` VALUES (2, 'extrato');
INSERT INTO `tipotransacao` VALUES (3, 'transferencia');
INSERT INTO `tipotransacao` VALUES (4, 'pagamento');

CREATE TABLE `titulospagamento` (
  `codigo` int(2) NOT NULL,
  `nome` varchar(20),
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `titulospagamento` VALUES (1, 'gas');
INSERT INTO `titulospagamento` VALUES (2, 'energia');
INSERT INTO `titulospagamento` VALUES (3, 'celular');
INSERT INTO `titulospagamento` VALUES (4, 'agua');

CREATE TABLE `funcionario` (
  `cpf` varchar(15) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(10) NOT NULL,
  `senha` varchar(40) NOT NULL,
  `status` smallint,
  CONSTRAINT statusfuncionario_ifk FOREIGN KEY (status) REFERENCES status (codigo),
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `funcionario` VALUES ('321','Testando','user','5f6955d227a320c7f1f6c7da2a6d96a851a8118f',1);

CREATE TABLE `cliente` (
  `cpf` varchar(15) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `status` smallint,
  CONSTRAINT statuscliente_ifk FOREIGN KEY (status) REFERENCES status (codigo),
  PRIMARY KEY `cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `cliente` VALUES ('111.111.111-11', 'Andre', 'Rua teste', '123',1);

CREATE TABLE conta (
	cliente varchar(15) NOT NULL,
	nroconta int(5) NOT NULL AUTO_INCREMENT,
	saldo double DEFAULT '0',
	senha varchar(40) NOT NULL,
	`status` smallint,
	PRIMARY KEY (nroconta),
	KEY `cliente` (`cliente`),
	CONSTRAINT cliente_ifk FOREIGN KEY (cliente) REFERENCES cliente (cpf),
	CONSTRAINT status_ifk FOREIGN KEY (status) REFERENCES status (codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `conta` VALUES ('111.111.111-11', '1', '0', '7c4a8d09ca3762af61e59520943dc26494f8941b',1);

CREATE TABLE `transferencia` (
  `codigo` int(2) NOT NULL AUTO_INCREMENT,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valor` double NOT NULL,
  `nroconta_concedente` int(5) NOT NULL,
  `nroconta_beneficiado` int(5) NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `nroconta_concedente_ibfk` FOREIGN KEY (`nroconta_concedente`) REFERENCES `conta` (`nroconta`),
  CONSTRAINT `nroconta_beneficiado_ibfk` FOREIGN KEY (`nroconta_beneficiado`) REFERENCES `conta` (`nroconta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `pagamento` (
  `codigo` int(2) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valor` double NOT NULL,
  `titulospagamento` int(2) NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `titulospagamento_ibfk` FOREIGN KEY (`titulospagamento`) REFERENCES `titulospagamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `histtransacao` (
  `codigotransacao` int(2) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tipotransacao` int(2) NOT NULL,
  PRIMARY KEY (`codigotransacao`),
  CONSTRAINT `tipotransacaohist_ibfk` FOREIGN KEY (`tipotransacao`) REFERENCES `tipotransacao` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
