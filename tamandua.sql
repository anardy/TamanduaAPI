-- MySQL dump 10.13  Distrib 5.5.22, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: tamandua
-- ------------------------------------------------------
-- Server version	5.5.22-0ubuntu1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `status`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `codigo` smallint,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY(`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('1','ativo');
INSERT INTO `status` VALUES ('2','desativo');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financeiro`
--

DROP TABLE IF EXISTS `financeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `financeiro` (
	`codigo` int(2) NOT NULL,
	`montante_reais` double DEFAULT '0',
	`montante_tamanduas` double DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financeiro`
--

LOCK TABLES `financeiro` WRITE;
/*!40000 ALTER TABLE `financeiro` DISABLE KEYS */;
INSERT INTO `financeiro` VALUES (1,'10000', '10000');
/*!40000 ALTER TABLE `financeiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `cpf` varchar(15) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `login` varchar(10) NOT NULL,
  `senha` varchar(40) NOT NULL,
  `status` smallint,
  `funcao` enum('gerente','atendente') NOT NULL,
  CONSTRAINT statusfuncionario_ifk FOREIGN KEY (status) REFERENCES status (codigo),
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES ('321','Testando','user','5f6955d227a320c7f1f6c7da2a6d96a851a8118f',1,'atendente');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cpf` varchar(15) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `status` smallint,
  CONSTRAINT statuscliente_ifk FOREIGN KEY (status) REFERENCES status (codigo),
  PRIMARY KEY `cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('111.111.111-11','Cacilds','Rua sem nome','232323',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;


-- Criar tabela conta
DROP TABLE IF EXISTS `conta`;
CREATE TABLE conta (
	cpf varchar(15) NOT NULL,
	nroconta int(5) NOT NULL AUTO_INCREMENT,
	saldo double DEFAULT '0',
	senha varchar(40) NOT NULL,
	`status` smallint,
	PRIMARY KEY (nroconta),
	KEY `cpf` (`cpf`),
	CONSTRAINT nroconta_ifk FOREIGN KEY (cpf) REFERENCES cliente (cpf),
	CONSTRAINT statusconta_ifk FOREIGN KEY (status) REFERENCES status (codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `conta` VALUES ('111.111.111-11', '', '', '7c4a8d09ca3762af61e59520943dc26494f8941b',1);

--
-- Table structure for table `deposito`
--

DROP TABLE IF EXISTS `deposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deposito` (
  `codigo` varchar(40) NOT NULL,
  `valor` double NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nroconta` int(5) NOT NULL,
  `codigo_funcionario` varchar(15) NOT NULL,
  tipo enum('cliente','filiado'),
  PRIMARY KEY (`codigo`),
  KEY `nroconta` (`nroconta`),
  KEY `codigo_funcionario` (`codigo_funcionario`), 
  CONSTRAINT `deposito_ibfk_2` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`cpf`),
  CONSTRAINT `deposito_ibfk_1` FOREIGN KEY (`nroconta`) REFERENCES `conta` (`nroconta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprestimo` (
  `codigo` varchar(40) NOT NULL,
  `valor` double NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `codigo_funcionario` varchar(15) NOT NULL,
  `nroconta` int(5) NOT NULL,
  `juros` double NOT NULL,
  `nroparcela` enum('1','2','3','4','5','6'),
  PRIMARY KEY (`codigo`),
  KEY `codigo_funcionario` (`codigo_funcionario`),
  KEY `nroconta` (`nroconta`),
  CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`cpf`),
  CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`nroconta`) REFERENCES `conta` (`nroconta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cambio`
--

DROP TABLE IF EXISTS `cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cambio` (
  `codigo` varchar(40) NOT NULL,
  `tipo` enum('T/R', 'R/T'),
  `valor_tamandua` double NOT NULL,
  `valor_real` double NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `codigo_funcionario` varchar(15) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_funcionario` (`codigo_funcionario`), 
  CONSTRAINT `fundos_ibfk_1` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `saque`
--

DROP TABLE IF EXISTS `saque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saque` (
  `codigo` varchar(40) NOT NULL,
  `valor` double NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nroconta` int(5) NOT NULL,
  `codigo_funcionario` varchar(15) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `nroconta` (`nroconta`),
  KEY `codigo_funcionario` (`codigo_funcionario`), 
  CONSTRAINT `saque_ibfk_2` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`cpf`),
  CONSTRAINT `saque_ibfk_1` FOREIGN KEY (`nroconta`) REFERENCES `conta` (`nroconta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transferencia`
--

DROP TABLE IF EXISTS `transferencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferencia` (
  `codigo` varchar(40) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valor` double NOT NULL,
  `cliente_concedente` int(5) NOT NULL,
  `cliente_beneficiado` int(5) NOT NULL,
  `codigo_funcionario` varchar(15) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `cliente_concedente` (`cliente_concedente`),
  KEY `cliente_beneficiado` (`cliente_beneficiado`),
  KEY `codigo_funcionario` (`codigo_funcionario`), 
  CONSTRAINT `transferencia_ibfk_3` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`cpf`),
  CONSTRAINT `transferencia_ibfk_1` FOREIGN KEY (`cliente_concedente`) REFERENCES `conta` (`nroconta`),
  CONSTRAINT `transferencia_ibfk_2` FOREIGN KEY (`cliente_beneficiado`) REFERENCES `conta` (`nroconta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencia`
--

LOCK TABLES `transferencia` WRITE;
/*!40000 ALTER TABLE `transferencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- Tabela de Token
DROP TABLE IF EXISTS tokens;
CREATE TABLE tokens (
	token varchar(40) NOT NULL,
	hora int NOT NULL,
	PRIMARY KEY (`hora`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


LOCK TABLES tokens WRITE;
/*!40000 ALTER TABLE tokens DISABLE KEYS */;
INSERT INTO tokens VALUES ("13f20098aaeb6ce9cec13aae85c789e040a7efc0", 0);
INSERT INTO tokens VALUES ("e3a25d2fbea8d5c4f228b7c73b3ce54492b6faf5", 1);
INSERT INTO tokens VALUES ("a2f7fcb5afeb7983ffbb6ce3d1a7e91edf321350", 2);
INSERT INTO tokens VALUES ("2ddb786aae2a2ad6506d1119ac86f1241a35b251", 3);
INSERT INTO tokens VALUES ("e81a02a9d6405977f63a769d9f6ec8ed9d924325", 4);
INSERT INTO tokens VALUES ("59c478b464b5eac6effae8314f22ade7bfe80dc1", 5);
INSERT INTO tokens VALUES ("eb8fcf5ac5f40a19b06906a1676c7a4eab0ee942", 6);
INSERT INTO tokens VALUES ("35b151ba67e31e4331bde23a3e73743300c44c1b", 7);
INSERT INTO tokens VALUES ("034af408780d34c3f635c81e7075d56239ecc3c5", 8);
INSERT INTO tokens VALUES ("f04cc316d72ba737f35309ed8e4cd7100a7660bd", 9);
INSERT INTO tokens VALUES ("bbe28d2184614fa1bb55a6abf6177468cd82f560", 10);
INSERT INTO tokens VALUES ("1247a068a0b0f3793b05f6843b88f830cc5701fb", 11);
INSERT INTO tokens VALUES ("5274691909eaffbee23bc8a055965b2c16d4de21", 12);
INSERT INTO tokens VALUES ("0c230254aaa669ac34c0376c9a833e0675559dc9", 13);
INSERT INTO tokens VALUES ("42a50539b0df7bfc3827103beefe9b1fd918f22b", 14);
INSERT INTO tokens VALUES ("00869fafc2c5623a4c84d438e219876790c56f86", 15);
INSERT INTO tokens VALUES ("77ffa3e084b0057a6c3c2fad3fdb6b62d901f822", 16);
INSERT INTO tokens VALUES ("00869fafc2c5623a4c84d438e219876790c56f86", 17);
INSERT INTO tokens VALUES ("79ad3bc00c549cbde6fe945f1b4fb9b2bce85be5", 18);
INSERT INTO tokens VALUES ("8b9b43e964efcc55d17200f911f59c4e18782d91", 19);
INSERT INTO tokens VALUES ("107c97c451f84c4b8630a2bfefea1857a6e12dc0", 20);
INSERT INTO tokens VALUES ("2655429f4ed44e6c365781b9cde40c4e5aa3a9d3", 21);
INSERT INTO tokens VALUES ("cd175c7f622eb6adce7173c231154ff10df80e77", 22);
INSERT INTO tokens VALUES ("d80ef31457a7f758f4854fe3a860dea714b47405", 23);
/*!40000 ALTER TABLE tokens ENABLE KEYS */;
UNLOCK TABLES;


		/*Conta conta = new Conta();
		 * System.out.println(conta.hash_senha("tomate"));
		System.out.println(conta.hash_senha("alface"));
		System.out.println(conta.hash_senha("jupiter"));
		System.out.println(conta.hash_senha("bola"));
		System.out.println(conta.hash_senha("corvus"));
		System.out.println(conta.hash_senha("vento"));
		System.out.println(conta.hash_senha("paraiso"));
		System.out.println(conta.hash_senha("truco"));
		System.out.println(conta.hash_senha("suco"));
		System.out.println(conta.hash_senha("mesa"));
		System.out.println(conta.hash_senha("noel"));
		System.out.println(conta.hash_senha("cebola"));
		System.out.println(conta.hash_senha("coruja"));
		System.out.println(conta.hash_senha("rato"));
		System.out.println(conta.hash_senha("boca"));
		System.out.println(conta.hash_senha("pagode"));
		System.out.println(conta.hash_senha("chuva"));
		System.out.println(conta.hash_senha("pagode"));
		System.out.println(conta.hash_senha("gruta"));
		System.out.println(conta.hash_senha("dedo"));
		System.out.println(conta.hash_senha("urso"));
		System.out.println(conta.hash_senha("rioclaro"));
		System.out.println(conta.hash_senha("tatu"));
		System.out.println(conta.hash_senha("curling"));*/