package br.com.rinha.backend2.transacao;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

    @Query(value = "select * from transacao t where cliente_id = ?1 order by realizada_em desc limit 10 FOR SHARE",nativeQuery = true)
    List<Transacao> findAllByCliente(Long clienteId);
}
