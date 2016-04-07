# Banco Tamanduá API

API do Projeto Tamanduá

O Projeto Tamandua é um trabalho de faculdade. O principal objetivo de utilizar o trabalho como ideia é de realizar estudos em diversas tecnologias. Como resultados desses estudos este é uma API construída em Java.

Como o projeto foi dividido em duas partes o back-end([TamanudaAPI](https://github.com/anardy/TamanduaAPI)) e o front-end([Tamandua](https://github.com/anardy/Tamandua)), deu a liberdade de contruir a API em diversas tecnologias. Portanto, o próximo passo é desenvolver a API em NodeJS.

Futuramente, a API também será desenvolvida em outras tecnologias como: Ruby, Python e .NET.

# Próximo passo do Projeto

Contrução de um container no Docker com o ambiente todo configurado. Enquanto este container não é criado, por favor siga os passos abaixos para rodar a aplicação.

Escrever os testes unitários.

# Arquitura

O principal objetivo da arquitura da aplicação é não utilizar nenhum framework externo a especificação do Java Enterprise Edition. Segue abaixo a lista com todas as especificações utilizadas para desenvolver a API da aplicação [TamanduaAPI]().

* JEE v7
* JPA
* EJB
* JAX-RS

# Requisitos para execução do Projeto

* [WildFly](http://wildfly.org/)
* [MySQL](http://www.mysql.com/-mysql)
* [Maven](https://maven.apache.org/)
* [Travis](https://travis-ci.org)

# Clone Projeto

```
$ git clone git://github.com/anardy/TamanduaAPI.git
```

# Banco de Dados

1. Acesso o MySQL

```sql
> myslq create database tamandua
```

2. Importe o SQL

```sql
> mysql -u root -p tamandua < tamandua.sql
```

# Configurando o WildFly

1. Dentro do WildFly, crie um dataSource como o nome: TamanduaDS 

# Testando a API

```
> curl http://localhost:8080/tamandua-api/v1/conta/saldo/1
``` 