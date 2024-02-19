create table cliente (
                         id bigserial not null,
                         limite numeric(38),
                         nome varchar(50),
                         saldo_inicial numeric(38),
                         version bigint,
                         primary key (id)
);

create table transacao (
                           id bigserial not null,
                           descricao varchar(10),
                           realizada_em timestamp(6),
                           tipo varchar(1),
                           valor numeric(38),
                           cliente_id bigint,
                           primary key (id)
);

alter table if exists transacao
    add constraint FKcliente
        foreign key (cliente_id)
            references cliente;

DO $$
BEGIN
INSERT INTO cliente (nome, limite, saldo_inicial,version) VALUES
                                                      ('Gustavo Fring', 100000, 0,0),
                                                      ('Walter White',80000, 0,0),
                                                      ('Jesse Pinkman', 1000000, 0,0),
                                                      ('Saul Goodman', 10000000, 0,0),
                                                      ('Hector Salamanca', 500000, 0,0);
END;
$$;
