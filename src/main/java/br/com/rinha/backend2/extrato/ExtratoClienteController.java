package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.transacao.TransacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExtratoClienteController {

    private final TransacaoRepository transacaoRepository;

    public ExtratoClienteController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("/clientes/{id}/extrato")
    @Transactional
    public ResponseEntity<?> busca(@PathVariable("id") Long clienteId) {

        var transacoesCliente = transacaoRepository
                .findAllByCliente(clienteId);

        if (clienteId > 5 || clienteId < 1){
            return ResponseEntity.notFound().build();
        }

        if (transacoesCliente.isEmpty()){
            return ResponseEntity.ok(List.of());
        }

        var cliente = transacoesCliente.getFirst().getCliente();

        return ResponseEntity.ok(new SaldoResponse(cliente, transacoesCliente));
    }
}
