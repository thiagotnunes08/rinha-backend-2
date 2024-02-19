package br.com.rinha.backend2.transacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
    List<Transacao> findAllByClienteId(Long clienteId);
}
