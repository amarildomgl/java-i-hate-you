
/**
 * Author:  amari
 * Created: 16/07/2023
 */

CREATE DATABASE ucanwallet;


CREATE TYPE sexo_enum AS ENUM ('MASCULINO', 'FEMININO');

CREATE TABLE pessoas (
    pk_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,      
    nome_pai VARCHAR(100) NULL,
    nome_mae VARCHAR(100) NULL,
    nif VARCHAR(50) NOT NULL,
    residencia VARCHAR(100) NULL,
    data_nascimento DATE NULL,    
    sexo sexo_enum NOT NULL DEFAULT 'MASCULINO'
);

CREATE TYPE nivel_acesso_enum AS ENUM ('CLIENTE', 'ADMINISTRADOR');

CREATE TABLE utilizadores (
    pk_usuario SERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    nivel_acesso nivel_acesso_enum NOT NULL DEFAULT 'CLIENTE',
    fk_pessoa INTEGER NOT NULL REFERENCES pessoas(pk_pessoa)
);


CREATE TYPE tipo_cliente_enum AS ENUM ('PARTICULAR', 'EMPRESA');

CREATE TABLE clientes (
    pk_cliente SERIAL PRIMARY KEY,
    nif VARCHAR(100) NOT NULL,
    tipo_cliente tipo_cliente_enum NOT NULL DEFAULT 'PARTICULAR',
    fk_utilizador INTEGER NOT NULL REFERENCES utilizadores(pk_usuario)
);


CREATE TYPE tipologia_conta_enum AS ENUM ('DEBITO', 'CREDITO');


CREATE TYPE estado_conta_enum AS ENUM ('ACTIVA', 'BLOQUEADA');

CREATE TABLE contas (
    fk_cliente INTEGER NOT NULL,
    numero UUID PRIMARY KEY, 
    chave_publica BYTEA NOT NULL,
    chave_privada BYTEA NOT NULL,
    saldo_contabilistico DOUBLE PRECISION NOT NULL, 
    saldo_disponivel DOUBLE PRECISION NOT NULL,
    tipologia_conta tipologia_conta_enum NOT NULL, 
    estado_conta estado_conta_enum NOT NULL 
);



CREATE TYPE tipo_movimento_enum AS ENUM ('CREDITO', 'DEBITO');

CREATE TYPE estado_transacao_enum AS ENUM ('EM_PROCESSAMENTO', 'VALIDADA', 'NAO_VALIDADA');

CREATE TABLE transacoes (
    pk_transacao SERIAL PRIMARY KEY,
    conta UUID NOT NULL,
    conta_destino UUID NULL,
    valor DOUBLE PRECISION  NOT NULL,
    data_transacao TIMESTAMP NOT NULL,
    tipo_movimento tipo_movimento_enum NOT NULL,
    estado_transacao estado_transacao_enum NOT NULL
);

CREATE TABLE movimentos (   
    descricao VARCHAR(255),
    conta UUID NOT NULL,
    valor DOUBLE PRECISION  NOT NULL,
    tipo_movimento tipo_movimento_enum NOT NULL,
);


