package br.com.rinha.backend2.extrato;

import br.com.rinha.backend2.transacao.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ExtratoClienteController {

    private final TransacaoRepository transacaoRepository;

    public ExtratoClienteController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("/clientes/{id}/extrato")
    public SaldoResponse busca(@PathVariable("id") Long clienteId) {

        var transacoesCliente = transacaoRepository
                .findAllByCliente(clienteId);

        if (transacoesCliente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var cliente = transacoesCliente.getFirst().getCliente();

        return new SaldoResponse(cliente, transacoesCliente);

    }
}
