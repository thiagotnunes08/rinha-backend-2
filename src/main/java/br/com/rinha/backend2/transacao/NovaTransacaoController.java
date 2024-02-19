package br.com.rinha.backend2.transacao;
import br.com.rinha.backend2.cliente.ClienteRepository;
import br.com.rinha.backend2.cliente.ClienteResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ClienteResponse transfere(@PathVariable Long id, @RequestBody @Valid NovaTransaoRequest request) {

        if (!request.tipoValido()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (request.ehDecimal()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var possivelCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (request.ehDebito() && possivelCliente.naoTemLimite(request.valor())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        possivelCliente.atualizaSaldo(request.valor());

        transacaoRepository.save(new Transacao(request,possivelCliente));

        return new ClienteResponse(possivelCliente);
    }
}

