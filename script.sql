create table cliente (
                         id bigserial not null,
                         limite numeric(38,2),
                         nome varchar(255),
                         saldo_inicial numeric(38,2),
                         primary key (id)
);

create table cliente_transacaos (
                                    cliente_id bigint not null,
                                    transacaos_id bigint not null
);

create table transacao (
                           id bigserial not null,
                           descricao varchar(255),
                           realizada_em timestamp(6),
                           tipo varchar(255),
                           valor numeric(38,2),
                           primary key (id)
);

alter table if exists cliente_transacaos
    drop constraint if exists UK_cli_transaao;

alter table if exists cliente_transacaos
    add constraint UK_cli_transaao unique (transacaos_id);

alter table if exists cliente_transacaos
    add constraint transacaoFK
        foreign key (transacaos_id)
            references transacao;

alter table if exists cliente_transacaos
    add constraint clienteFK
        foreign key (cliente_id)
            references cliente;

DO $$
BEGIN
INSERT INTO cliente (nome, limite, saldo_inicial) VALUES
                                                      ('Gustavo Fring', 100000, 2),
                                                      ('Walter White',80000, 0),
                                                      ('Jesse Pinkman', 1000000, 0),
                                                      ('Saul Goodman', 10000000, 0),
                                                      ('Hector Salamanca', 500000, 0);
END;
$$;
