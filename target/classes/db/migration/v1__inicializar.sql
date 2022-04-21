
create schema if not exists testDb;

CREATE TABLE CONTA
( idConta INT(11) NOT NULL AUTO_INCREMENT,
  idPessoa INT(11) NOT NULL,
  saldo DECIMAL(10,2),
  limiteSaqueDiario DECIMAL(10,2),
  flagAtivo BIT default 'TRUE' NOT NULL,
  tipoConta INT(11) NOT NULL,
  dataCriacao date,
  CONSTRAINT conta_pk PRIMARY KEY (idConta)
);

CREATE TABLE Pessoa
( idPessoa INT(11) NOT NULL AUTO_INCREMENT,
  nome varchar(200) NOT NULL,
  cpf char(11) NOT NULL,
  data_nascimento date,
  CONSTRAINT pessoa_pk PRIMARY KEY (idPessoa),
  CONSTRAINT CK_CPF check (LENGTH(cpf)=11)
);

CREATE TABLE Transacao
( idTransacao INT(11) NOT NULL AUTO_INCREMENT,
  idConta INT(11) NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  dataTransacao date,
  CONSTRAINT transacao_pk PRIMARY KEY (idTransacao)
);
