package br.com.rinha.backend2.transacao;

import br.com.rinha.backend2.cliente.ClienteRepository;
import br.com.rinha.backend2.cliente.ClienteResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class NovaTransacaoController {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public NovaTransacaoController(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping("/clientes/{id}/transacoes")
    @Transactional
    public ResponseEntity<?> transfere(@PathVariable Long id, @RequestBody @Valid NovaTransaoRequest request) {


        if (!request.tipoValido()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (request.ehDecimal()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (id > 5L || id < 1L) {
            return ResponseEntity.notFound().build();
        }

        var cliente = clienteRepository.buscarPorId(id);

        if (request.ehDebito()) {

            cliente.debitaSaldo(request.valor());

            if (cliente.execedeuLimite()) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(422));
            }
        }

        if (request.ehCredito()) {

            cliente.creditaSaldo(request.valor());
        }
        transacaoRepository.save(new Transacao(request, cliente));

        ResponseEntity<ClienteResponse> ok = ResponseEntity.ok(new ClienteResponse(cliente));

        return ok;
    }
}

