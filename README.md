# TamanduaWS

API do Projeto Tamandua (https://github.com/anardy/Tamandua)

# Requisitos para execução do Projeto

* [WildFly](http://wildfly.org/)
* [MySQL](http://www.mysql.com/-mysql)

# Clone Projeto

```
$ git clone git://github.com/anardy/TamanduaWS.git
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

# Configurando Log

Altere o caminho do arquivo WebContent/classes/log4j.xml

```xml
<param name="File" value="SeuCaminho\tamandua.log"/>
```

Obs: Caso rode no Windows use duas barras `SeuCaminho\\tamandua.log`

# Configurando o WildFly

1. DataSource
